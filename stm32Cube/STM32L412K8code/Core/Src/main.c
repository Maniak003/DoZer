/* USER CODE BEGIN Header */
/**
  ******************************************************************************
  * @file           : main.c
  * @brief          : Main program body
  ******************************************************************************
  * @attention
  *
  * <h2><center>&copy; Copyright (c) 2021 STMicroelectronics.
  * All rights reserved.</center></h2>
  *
  * This software component is licensed by ST under BSD 3-Clause license,
  * the "License"; You may not use this file except in compliance with the
  * License. You may obtain a copy of the License at:
  *                        opensource.org/licenses/BSD-3-Clause
  *
  ******************************************************************************
  */
/* USER CODE END Header */
/* Includes ------------------------------------------------------------------*/
#include "main.h"

/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */
#ifdef DISPLAY_ENABLE
#include "ssd1306.h"
#include "ssd1306_fonts.h"
#endif
#include <stdio.h>

/* USER CODE END Includes */

/* Private typedef -----------------------------------------------------------*/
/* USER CODE BEGIN PTD */

/* USER CODE END PTD */

/* Private define ------------------------------------------------------------*/
/* USER CODE BEGIN PD */
/* USER CODE END PD */

/* Private macro -------------------------------------------------------------*/
/* USER CODE BEGIN PM */

/* USER CODE END PM */

/* Private variables ---------------------------------------------------------*/
ADC_HandleTypeDef hadc1;
ADC_HandleTypeDef hadc2;

TIM_HandleTypeDef htim2;
TIM_HandleTypeDef htim6;
TIM_HandleTypeDef htim15;
TIM_HandleTypeDef htim16;

UART_HandleTypeDef huart1;
DMA_HandleTypeDef hdma_usart1_rx;

/* USER CODE BEGIN PV */
char counterPP[20];
_Bool initFlag = 1, sleepFlag = 1, initUART = 1;
uint32_t counterCC = 0, counterALL = 0, sleepDelay;
uint16_t adcResult = 0;
uint16_t spectrData[2050] = {0};
uint16_t spectrCRC;

void bebe(void) {
	HAL_TIM_OC_Start(&htim2, TIM_CHANNEL_4); // Start timer for turn off Buzzer
	HAL_Delay(200);
	HAL_TIM_OC_Stop(&htim2, TIM_CHANNEL_4);
	HAL_Delay(200);
	HAL_TIM_OC_Start(&htim2, TIM_CHANNEL_4); // Start timer for turn off Buzzer
	HAL_Delay(200);
	HAL_TIM_OC_Stop(&htim2, TIM_CHANNEL_4);
}

/*
 * read/write config data from/to flash
 */
void rwFlash(uint8_t rwFlag) {
	uint32_t pageAdr = 0x800F800; // Begin of 31 page, last page flash for STM32L412K8.
	uint32_t magicKey;
	uint64_t dataForSave;
	magicKey = *(__IO uint32_t*) pageAdr;
	if ((magicKey != 0x1234) || (rwFlag == 1)) { // rwFlag == 1 for wrtite data to flash
		magicKey = 0x1234;
		if (rwFlag == 0) { // For first init
			cfgData = 0;
			cfgLevel1 = 0;
			cfgLevel2 = 0;
			cfgLevel3 = 0;
		}
		FLASH_EraseInitTypeDef EraseInitStruct;
		uint32_t PAGEError = 0;
		EraseInitStruct.TypeErase   = FLASH_TYPEERASE_PAGES;
		EraseInitStruct.Page = 31; // Page size for STM32L412K8 is 2KB
		EraseInitStruct.NbPages     = 1;

		flash_ok = HAL_ERROR;
		// Unlock flash
		while(flash_ok != HAL_OK) {
		  flash_ok = HAL_FLASH_Unlock();
		}
		if (HAL_FLASHEx_Erase(&EraseInitStruct, &PAGEError) == HAL_OK) {
			dataForSave = (uint64_t) (magicKey | (((uint64_t) cfgData << 32) & 0xFFFFFFFF00000000) | (((uint64_t) cfgLevel1 << 48) & 0xFFFF000000000000));
			flash_ok = HAL_ERROR;
			while(flash_ok != HAL_OK){
				flash_ok = HAL_FLASH_Program(FLASH_TYPEPROGRAM_DOUBLEWORD, pageAdr, dataForSave); // Write  magic key into Flash
			}
		}
		// Lock flash
		flash_ok = HAL_ERROR;
		while(flash_ok != HAL_OK){
			flash_ok = HAL_FLASH_Lock();
		}
	} else {
		cfgData = *(__IO uint16_t*) (pageAdr + 4);
		cfgLevel1 = *(__IO uint16_t*) (pageAdr + 6);
		cfgLevel2 = *(__IO uint16_t*) (pageAdr + 8);
		cfgLevel3 = *(__IO uint16_t*) (pageAdr + 10);
	}
}

/* USER CODE END PV */

/* Private function prototypes -----------------------------------------------*/
void SystemClock_Config(void);
static void MX_GPIO_Init(void);
static void MX_DMA_Init(void);
static void MX_ADC1_Init(void);
static void MX_USART1_UART_Init(void);
static void MX_TIM15_Init(void);
static void MX_ADC2_Init(void);
static void MX_TIM2_Init(void);
static void MX_TIM16_Init(void);
static void MX_TIM6_Init(void);
/* USER CODE BEGIN PFP */

/* USER CODE END PFP */

/* Private user code ---------------------------------------------------------*/
/* USER CODE BEGIN 0 */

/* USER CODE END 0 */

/**
  * @brief  The application entry point.
  * @retval int
  */
int main(void)
{
  /* USER CODE BEGIN 1 */

  /* USER CODE END 1 */

  /* MCU Configuration--------------------------------------------------------*/

  /* Reset of all peripherals, Initializes the Flash interface and the Systick. */
  HAL_Init();

  /* USER CODE BEGIN Init */

  /* USER CODE END Init */

  /* Configure the system clock */
  SystemClock_Config();

  /* USER CODE BEGIN SysInit */

  /* USER CODE END SysInit */

  /* Initialize all configured peripherals */
  MX_GPIO_Init();
  MX_DMA_Init();
  MX_ADC1_Init();
  MX_USART1_UART_Init();
  MX_TIM15_Init();
  MX_ADC2_Init();
  MX_TIM2_Init();
  MX_TIM16_Init();
  MX_TIM6_Init();
  /* USER CODE BEGIN 2 */
  /* USER CODE END 2 */

  /* Infinite loop */
  /* USER CODE BEGIN WHILE */
  int j = 0;
  uint8_t btCommand[sizeCommand];
  uint8_t prefix[3] = {'<', 'B', '>'};
  uint8_t lowSpectr, highSpectr;
  #ifdef DISPLAY_ENABLE
  ssd1306_Init();
  #endif
  //uint16_t tmpData;
  uint32_t initDelay, oldTime = HAL_GetTick();
  initDelay = oldTime;
  oldTimeAll = oldTime;
  sleepFlag = oldTime;
  counterCC = 0;

  rwFlash(0); // Read config from flash.

  HAL_GPIO_WritePin(GPIOB, LED_PIN, GPIO_PIN_SET); // LED on.
  HAL_GPIO_WritePin(GPIOA, COM_PIN, GPIO_PIN_SET); // Com pin disable
  __HAL_TIM_CLEAR_FLAG(&htim15, TIM_SR_UIF); // Clear flags
  HAL_TIM_Base_Start_IT(&htim15); // Start timer for turn off LED
  //
  HAL_TIM_OC_Start(&htim2, TIM_CHANNEL_4);
  __HAL_TIM_CLEAR_FLAG(&htim16, TIM_SR_UIF); // Clear flags
  HAL_TIM_Base_Start_IT(&htim16); // Start timer for turn off Buzzer

  /* Test alarm */
  alarmLevel = 1;
  alarmCount = 0;
  HAL_TIM_Base_Start_IT(&htim6); // Alarm timer.

  while (1)
  {
	  //HAL_TIM_Base_Start(&htim2);
	#ifdef DISPLAY_ENABLE
	  sprintf(counterPP, "CPS:%lu CNT:%lu", counterCC / ((HAL_GetTick() - oldTime) / 1000), counterALL);
	  counterCC = 0;
	  oldTime = HAL_GetTick();
	  ssd1306_SetCursor(0, 0);
	  ssd1306_WriteString(counterPP, Font_6x8, 0x01);
	#endif
	  int ttt, max = 1;
	  for ( int i = 2; i < 2050; i++) {
		  ttt = spectrData[i];
		  if ((float) ttt > max)
			  max = ttt;
	  }
	#ifdef DISPLAY_ENABLE
	  sprintf(counterPP, "AVG:%lu MAX:%d", counterALL / ((HAL_GetTick() - oldTimeAll) / 1000), max);
	  ssd1306_SetCursor(0, 8);
	  ssd1306_WriteString(counterPP, Font_6x8, 0x01);

	  sprintf(counterPP, "TM:%lu",(HAL_GetTick() - oldTimeAll) / 1000);
	  ssd1306_SetCursor(0, 16);
	  ssd1306_WriteString(counterPP, Font_6x8, 0x01);
	#endif
	/*
	  //
	  // Гистограмма для LCD 64x128.
	  //
	  int k = 0;
	  for ( int i = 0; i < 119; i++) {
		  sptDat =(float) ((spectrData[k++] + spectrData[k++] + spectrData[k++]) * 64 / max);
		  //sptDat = (float) (log2f((float) spectrData[i]) * 64 / max);
		  ssd1306_Line(0, i + 9, 64, i + 9, 0x00);
		  ssd1306_Line(0, i + 9, sptDat, i + 9, 0x01);
	  }
	  */
	#ifdef DISPLAY_ENABLE
	  ssd1306_UpdateScreen();
	#endif
	  // Delay after on.
	  if (initFlag && (HAL_GetTick() - initDelay > INIT_TIME)) {
		  initFlag = 0;
		  HAL_ADC_Start_IT(&hadc1);  // Init ADC.
		  oldTimeAll = HAL_GetTick();
	  }
	#ifdef DISPLAY_ENABLE
	  ssd1306_SetCursor(0, 24);
	#endif
	  /* Status JDY-19, BT connected ? */
	  if ( HAL_GPIO_ReadPin(GPIOA, GPIO_PIN_15) == 1 ) { // BT State active ?
	#ifdef DISPLAY_ENABLE
		  ssd1306_WriteString("BT: connect   ", Font_6x8, 0x01);
	#endif
		  /* Init uart after sleep */
		  if (initUART) {
			  HAL_UART_Init(&huart1);
			  HAL_UART_Receive_DMA(&huart1, btCommand, sizeCommand);
			  initUART = 0;
		  }

		  if (hdma_usart1_rx.State == HAL_DMA_STATE_READY) {
			  HAL_UART_Receive_DMA(&huart1, btCommand, sizeCommand);
			  if (btCommand[0] == '<' && btCommand[2] == '>') {
				  uint16_t CS = 0;
				  for (int i = 0; i < 18; i++) {
					  CS = CS + btCommand[i];
				  }
				  if (((CS & 0xFF) == btCommand[18]) && (((CS >> 8) & 0xFF) == btCommand[19])) {
					  //HAL_TIM_OC_Start(&htim2, TIM_CHANNEL_4);
					  //HAL_TIM_Base_Start_IT(&htim16); // Start timer for turn off Buzzer
					  if (btCommand[1] == '1')  { // Clear statistics
						  for (int i = 0; i < 2050; i++) {
							  spectrData[i] = 0;
						  }
						  oldTimeAll = HAL_GetTick();
						  counterALL = 0;
					  } else if (btCommand[1] == '2') { // Write config data
						  bebe();
						  cfgData = ((btCommand[4] << 8) & 0xFF00) | btCommand[3];
						  cfgLevel1 = ((btCommand[6] << 8) & 0xFF00) | btCommand[5];
						  cfgLevel2 = ((btCommand[8] << 8) & 0xFF00) | btCommand[7];
						  cfgLevel3 = ((btCommand[10] << 8) & 0xFF00) | btCommand[9];
						  rwFlash(1); // Write to flash
					  }
				  }
			  }
		  }

		  j = 0;
		  // Transmit data over BT.
		  HAL_UART_Transmit(&huart1, prefix, 3, 1000); // Start sequence.
		  spectrData[0] = (uint16_t) ((HAL_GetTick() - oldTimeAll) / 1000); // Specter collection time.
		  spectrData[1] = (uint16_t) (((HAL_GetTick() - oldTimeAll) / 1000) >> 16);
		  spectrData[2] = (uint16_t) (counterALL & 0xFFFF);
		  spectrData[3] = (uint16_t) (counterALL >> 16);
		  spectrCRC = 0;
		  HAL_Delay(TRANSMIT_DALAY);  // Increase time delay if transmit error.
		  for ( int i = 0; i < 1042; i++) {
			  lowSpectr = spectrData[i] & 0xFF;
			  highSpectr = (spectrData[i] & 0xFF00) >> 8;
			  spectrCRC = spectrCRC + lowSpectr + highSpectr;
			  HAL_UART_Transmit(&huart1, &highSpectr, 1, 1000);
			  HAL_UART_Transmit(&huart1, &lowSpectr, 1, 1000);
			  if ( j++ >= 9) {
				  HAL_Delay(TRANSMIT_DALAY);  // Increase time delay if transmit error.
				  j = 0;
			  }
		  }
		  /* Transmit CRC */
		  HAL_Delay(TRANSMIT_DALAY);
		  lowSpectr = spectrCRC & 0xFF;
		  highSpectr = (spectrCRC & 0xFF00) >> 8;
		  HAL_UART_Transmit(&huart1, &highSpectr, 1, 1000);
		  HAL_UART_Transmit(&huart1, &lowSpectr, 1, 1000);
		  sleepDelay = HAL_GetTick();
		  sleepFlag = 1;
		  /* Measure battery voltage */
		  HAL_GPIO_WritePin(GPIOA, COM_PIN, GPIO_PIN_RESET);
		  HAL_ADC_Start(&hadc2);
	  } else {
	#ifdef DISPLAY_ENABLE
		  HAL_Delay(500);
		  ssd1306_WriteString("BT: disconnect", Font_6x8, 0x01);
	#endif
		  // BT sleep control
		  if (sleepFlag && (HAL_GetTick() - sleepDelay > SLEEPDALAY)) {
			  sleepFlag = 0;
			  HAL_UART_Transmit(&huart1, (uint8_t*) "AT+SLEEP\n", 9, 1000);    //For JDY-10
			  HAL_Delay(200);
			  HAL_UART_Transmit(&huart1, (uint8_t*) "AT+SLEEP\r\n", 10, 1000); //For JDY-19
			  HAL_GPIO_WritePin(GPIOB, LED_PIN, GPIO_PIN_SET); // LED on.
			  HAL_TIM_Base_Start_IT(&htim15); // Start timer for turn off LED.
			  HAL_UART_DeInit(&huart1);
			  initUART = 1;
		  }
	  }
	  HAL_Delay(500);
    /* USER CODE END WHILE */

    /* USER CODE BEGIN 3 */
  }
  /* USER CODE END 3 */
}

/**
  * @brief System Clock Configuration
  * @retval None
  */
void SystemClock_Config(void)
{
  RCC_OscInitTypeDef RCC_OscInitStruct = {0};
  RCC_ClkInitTypeDef RCC_ClkInitStruct = {0};
  RCC_PeriphCLKInitTypeDef PeriphClkInit = {0};

  /** Configure the main internal regulator output voltage
  */
  if (HAL_PWREx_ControlVoltageScaling(PWR_REGULATOR_VOLTAGE_SCALE1) != HAL_OK)
  {
    Error_Handler();
  }
  /** Initializes the RCC Oscillators according to the specified parameters
  * in the RCC_OscInitTypeDef structure.
  */
  RCC_OscInitStruct.OscillatorType = RCC_OSCILLATORTYPE_MSI;
  RCC_OscInitStruct.MSIState = RCC_MSI_ON;
  RCC_OscInitStruct.MSICalibrationValue = 0;
  RCC_OscInitStruct.MSIClockRange = RCC_MSIRANGE_7;
  RCC_OscInitStruct.PLL.PLLState = RCC_PLL_NONE;
  if (HAL_RCC_OscConfig(&RCC_OscInitStruct) != HAL_OK)
  {
    Error_Handler();
  }
  /** Initializes the CPU, AHB and APB buses clocks
  */
  RCC_ClkInitStruct.ClockType = RCC_CLOCKTYPE_HCLK|RCC_CLOCKTYPE_SYSCLK
                              |RCC_CLOCKTYPE_PCLK1|RCC_CLOCKTYPE_PCLK2;
  RCC_ClkInitStruct.SYSCLKSource = RCC_SYSCLKSOURCE_MSI;
  RCC_ClkInitStruct.AHBCLKDivider = RCC_SYSCLK_DIV16;
  RCC_ClkInitStruct.APB1CLKDivider = RCC_HCLK_DIV1;
  RCC_ClkInitStruct.APB2CLKDivider = RCC_HCLK_DIV1;

  if (HAL_RCC_ClockConfig(&RCC_ClkInitStruct, FLASH_LATENCY_0) != HAL_OK)
  {
    Error_Handler();
  }
  PeriphClkInit.PeriphClockSelection = RCC_PERIPHCLK_USART1|RCC_PERIPHCLK_ADC;
  PeriphClkInit.Usart1ClockSelection = RCC_USART1CLKSOURCE_SYSCLK;
  if (HAL_RCCEx_PeriphCLKConfig(&PeriphClkInit) != HAL_OK)
  {
    Error_Handler();
  }
}

/**
  * @brief ADC1 Initialization Function
  * @param None
  * @retval None
  */
static void MX_ADC1_Init(void)
{

  /* USER CODE BEGIN ADC1_Init 0 */

  /* USER CODE END ADC1_Init 0 */

  ADC_MultiModeTypeDef multimode = {0};
  ADC_ChannelConfTypeDef sConfig = {0};

  /* USER CODE BEGIN ADC1_Init 1 */

  /* USER CODE END ADC1_Init 1 */
  /** Common config
  */
  hadc1.Instance = ADC1;
  hadc1.Init.ClockPrescaler = ADC_CLOCK_ASYNC_DIV1;
  hadc1.Init.Resolution = ADC_RESOLUTION_12B;
  hadc1.Init.DataAlign = ADC_DATAALIGN_RIGHT;
  hadc1.Init.ScanConvMode = ADC_SCAN_DISABLE;
  hadc1.Init.EOCSelection = ADC_EOC_SINGLE_CONV;
  hadc1.Init.LowPowerAutoWait = DISABLE;
  hadc1.Init.ContinuousConvMode = DISABLE;
  hadc1.Init.NbrOfConversion = 1;
  hadc1.Init.DiscontinuousConvMode = DISABLE;
  hadc1.Init.ExternalTrigConv = ADC_EXTERNALTRIG_EXT_IT11;
  hadc1.Init.ExternalTrigConvEdge = ADC_EXTERNALTRIGCONVEDGE_RISING;
  hadc1.Init.DMAContinuousRequests = DISABLE;
  hadc1.Init.Overrun = ADC_OVR_DATA_PRESERVED;
  hadc1.Init.OversamplingMode = DISABLE;
  if (HAL_ADC_Init(&hadc1) != HAL_OK)
  {
    Error_Handler();
  }
  /** Configure the ADC multi-mode
  */
  multimode.Mode = ADC_MODE_INDEPENDENT;
  if (HAL_ADCEx_MultiModeConfigChannel(&hadc1, &multimode) != HAL_OK)
  {
    Error_Handler();
  }
  /** Configure Regular Channel
  */
  sConfig.Channel = ADC_CHANNEL_16;
  sConfig.Rank = ADC_REGULAR_RANK_1;
  sConfig.SamplingTime = ADC_SAMPLETIME_2CYCLES_5;
  sConfig.SingleDiff = ADC_SINGLE_ENDED;
  sConfig.OffsetNumber = ADC_OFFSET_1;
  sConfig.Offset = 1;
  if (HAL_ADC_ConfigChannel(&hadc1, &sConfig) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN ADC1_Init 2 */
  HAL_ADCEx_Calibration_Start(&hadc1, ADC_SINGLE_ENDED);

  /* USER CODE END ADC1_Init 2 */

}

/**
  * @brief ADC2 Initialization Function
  * @param None
  * @retval None
  */
static void MX_ADC2_Init(void)
{

  /* USER CODE BEGIN ADC2_Init 0 */

  /* USER CODE END ADC2_Init 0 */

  ADC_ChannelConfTypeDef sConfig = {0};

  /* USER CODE BEGIN ADC2_Init 1 */

  /* USER CODE END ADC2_Init 1 */
  /** Common config
  */
  hadc2.Instance = ADC2;
  hadc2.Init.ClockPrescaler = ADC_CLOCK_ASYNC_DIV1;
  hadc2.Init.Resolution = ADC_RESOLUTION_8B;
  hadc2.Init.DataAlign = ADC_DATAALIGN_RIGHT;
  hadc2.Init.ScanConvMode = ADC_SCAN_DISABLE;
  hadc2.Init.EOCSelection = ADC_EOC_SINGLE_CONV;
  hadc2.Init.LowPowerAutoWait = DISABLE;
  hadc2.Init.ContinuousConvMode = DISABLE;
  hadc2.Init.NbrOfConversion = 1;
  hadc2.Init.DiscontinuousConvMode = DISABLE;
  hadc2.Init.ExternalTrigConv = ADC_SOFTWARE_START;
  hadc2.Init.ExternalTrigConvEdge = ADC_EXTERNALTRIGCONVEDGE_NONE;
  hadc2.Init.DMAContinuousRequests = DISABLE;
  hadc2.Init.Overrun = ADC_OVR_DATA_PRESERVED;
  hadc2.Init.OversamplingMode = DISABLE;
  if (HAL_ADC_Init(&hadc2) != HAL_OK)
  {
    Error_Handler();
  }
  /** Configure Regular Channel
  */
  sConfig.Channel = ADC_CHANNEL_12;
  sConfig.Rank = ADC_REGULAR_RANK_1;
  sConfig.SamplingTime = ADC_SAMPLETIME_247CYCLES_5;
  sConfig.SingleDiff = ADC_SINGLE_ENDED;
  sConfig.OffsetNumber = ADC_OFFSET_NONE;
  sConfig.Offset = 3;
  if (HAL_ADC_ConfigChannel(&hadc2, &sConfig) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN ADC2_Init 2 */

  /* USER CODE END ADC2_Init 2 */

}

/**
  * @brief TIM2 Initialization Function
  * @param None
  * @retval None
  */
static void MX_TIM2_Init(void)
{

  /* USER CODE BEGIN TIM2_Init 0 */

  /* USER CODE END TIM2_Init 0 */

  TIM_ClockConfigTypeDef sClockSourceConfig = {0};
  TIM_MasterConfigTypeDef sMasterConfig = {0};
  TIM_OC_InitTypeDef sConfigOC = {0};

  /* USER CODE BEGIN TIM2_Init 1 */

  /* USER CODE END TIM2_Init 1 */
  htim2.Instance = TIM2;
  htim2.Init.Prescaler = 0;
  htim2.Init.CounterMode = TIM_COUNTERMODE_UP;
  htim2.Init.Period = 32;
  htim2.Init.ClockDivision = TIM_CLOCKDIVISION_DIV1;
  htim2.Init.AutoReloadPreload = TIM_AUTORELOAD_PRELOAD_ENABLE;
  if (HAL_TIM_Base_Init(&htim2) != HAL_OK)
  {
    Error_Handler();
  }
  sClockSourceConfig.ClockSource = TIM_CLOCKSOURCE_INTERNAL;
  if (HAL_TIM_ConfigClockSource(&htim2, &sClockSourceConfig) != HAL_OK)
  {
    Error_Handler();
  }
  if (HAL_TIM_OC_Init(&htim2) != HAL_OK)
  {
    Error_Handler();
  }
  sMasterConfig.MasterOutputTrigger = TIM_TRGO_UPDATE;
  sMasterConfig.MasterSlaveMode = TIM_MASTERSLAVEMODE_DISABLE;
  if (HAL_TIMEx_MasterConfigSynchronization(&htim2, &sMasterConfig) != HAL_OK)
  {
    Error_Handler();
  }
  sConfigOC.OCMode = TIM_OCMODE_TOGGLE;
  sConfigOC.Pulse = 0;
  sConfigOC.OCPolarity = TIM_OCPOLARITY_HIGH;
  sConfigOC.OCFastMode = TIM_OCFAST_DISABLE;
  if (HAL_TIM_OC_ConfigChannel(&htim2, &sConfigOC, TIM_CHANNEL_4) != HAL_OK)
  {
    Error_Handler();
  }
  __HAL_TIM_ENABLE_OCxPRELOAD(&htim2, TIM_CHANNEL_4);
  /* USER CODE BEGIN TIM2_Init 2 */

  /* USER CODE END TIM2_Init 2 */
  HAL_TIM_MspPostInit(&htim2);

}

/**
  * @brief TIM6 Initialization Function
  * @param None
  * @retval None
  */
static void MX_TIM6_Init(void)
{

  /* USER CODE BEGIN TIM6_Init 0 */

  /* USER CODE END TIM6_Init 0 */

  TIM_MasterConfigTypeDef sMasterConfig = {0};

  /* USER CODE BEGIN TIM6_Init 1 */

  /* USER CODE END TIM6_Init 1 */
  htim6.Instance = TIM6;
  htim6.Init.Prescaler = 2;
  htim6.Init.CounterMode = TIM_COUNTERMODE_UP;
  htim6.Init.Period = 20000;
  htim6.Init.AutoReloadPreload = TIM_AUTORELOAD_PRELOAD_ENABLE;
  if (HAL_TIM_Base_Init(&htim6) != HAL_OK)
  {
    Error_Handler();
  }
  sMasterConfig.MasterOutputTrigger = TIM_TRGO_RESET;
  sMasterConfig.MasterSlaveMode = TIM_MASTERSLAVEMODE_DISABLE;
  if (HAL_TIMEx_MasterConfigSynchronization(&htim6, &sMasterConfig) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN TIM6_Init 2 */

  /* USER CODE END TIM6_Init 2 */

}

/**
  * @brief TIM15 Initialization Function
  * @param None
  * @retval None
  */
static void MX_TIM15_Init(void)
{

  /* USER CODE BEGIN TIM15_Init 0 */

  /* USER CODE END TIM15_Init 0 */

  TIM_ClockConfigTypeDef sClockSourceConfig = {0};
  TIM_MasterConfigTypeDef sMasterConfig = {0};

  /* USER CODE BEGIN TIM15_Init 1 */

  /* USER CODE END TIM15_Init 1 */
  htim15.Instance = TIM15;
  htim15.Init.Prescaler = 0;
  htim15.Init.CounterMode = TIM_COUNTERMODE_UP;
  htim15.Init.Period = 100;
  htim15.Init.ClockDivision = TIM_CLOCKDIVISION_DIV1;
  htim15.Init.RepetitionCounter = 0;
  htim15.Init.AutoReloadPreload = TIM_AUTORELOAD_PRELOAD_ENABLE;
  if (HAL_TIM_Base_Init(&htim15) != HAL_OK)
  {
    Error_Handler();
  }
  sClockSourceConfig.ClockSource = TIM_CLOCKSOURCE_INTERNAL;
  if (HAL_TIM_ConfigClockSource(&htim15, &sClockSourceConfig) != HAL_OK)
  {
    Error_Handler();
  }
  if (HAL_TIM_OnePulse_Init(&htim15, TIM_OPMODE_SINGLE) != HAL_OK)
  {
    Error_Handler();
  }
  sMasterConfig.MasterOutputTrigger = TIM_TRGO_UPDATE;
  sMasterConfig.MasterSlaveMode = TIM_MASTERSLAVEMODE_DISABLE;
  if (HAL_TIMEx_MasterConfigSynchronization(&htim15, &sMasterConfig) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN TIM15_Init 2 */
  /* USER CODE END TIM15_Init 2 */

}

/**
  * @brief TIM16 Initialization Function
  * @param None
  * @retval None
  */
static void MX_TIM16_Init(void)
{

  /* USER CODE BEGIN TIM16_Init 0 */

  /* USER CODE END TIM16_Init 0 */

  /* USER CODE BEGIN TIM16_Init 1 */

  /* USER CODE END TIM16_Init 1 */
  htim16.Instance = TIM16;
  htim16.Init.Prescaler = 0;
  htim16.Init.CounterMode = TIM_COUNTERMODE_UP;
  htim16.Init.Period = 30000;
  htim16.Init.ClockDivision = TIM_CLOCKDIVISION_DIV1;
  htim16.Init.RepetitionCounter = 0;
  htim16.Init.AutoReloadPreload = TIM_AUTORELOAD_PRELOAD_ENABLE;
  if (HAL_TIM_Base_Init(&htim16) != HAL_OK)
  {
    Error_Handler();
  }
  if (HAL_TIM_OnePulse_Init(&htim16, TIM_OPMODE_SINGLE) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN TIM16_Init 2 */

  /* USER CODE END TIM16_Init 2 */

}

/**
  * @brief USART1 Initialization Function
  * @param None
  * @retval None
  */
static void MX_USART1_UART_Init(void)
{

  /* USER CODE BEGIN USART1_Init 0 */

  /* USER CODE END USART1_Init 0 */

  /* USER CODE BEGIN USART1_Init 1 */

  /* USER CODE END USART1_Init 1 */
  huart1.Instance = USART1;
  huart1.Init.BaudRate = 115200;
  huart1.Init.WordLength = UART_WORDLENGTH_8B;
  huart1.Init.StopBits = UART_STOPBITS_1;
  huart1.Init.Parity = UART_PARITY_NONE;
  huart1.Init.Mode = UART_MODE_TX_RX;
  huart1.Init.HwFlowCtl = UART_HWCONTROL_NONE;
  huart1.Init.OverSampling = UART_OVERSAMPLING_16;
  huart1.Init.OneBitSampling = UART_ONE_BIT_SAMPLE_DISABLE;
  huart1.AdvancedInit.AdvFeatureInit = UART_ADVFEATURE_DMADISABLEONERROR_INIT;
  huart1.AdvancedInit.DMADisableonRxError = UART_ADVFEATURE_DMA_DISABLEONRXERROR;
  if (HAL_UART_Init(&huart1) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN USART1_Init 2 */

  /* USER CODE END USART1_Init 2 */

}

/**
  * Enable DMA controller clock
  */
static void MX_DMA_Init(void)
{

  /* DMA controller clock enable */
  __HAL_RCC_DMA1_CLK_ENABLE();

  /* DMA interrupt init */
  /* DMA1_Channel5_IRQn interrupt configuration */
  HAL_NVIC_SetPriority(DMA1_Channel5_IRQn, 0, 0);
  HAL_NVIC_EnableIRQ(DMA1_Channel5_IRQn);

}

/**
  * @brief GPIO Initialization Function
  * @param None
  * @retval None
  */
static void MX_GPIO_Init(void)
{
  GPIO_InitTypeDef GPIO_InitStruct = {0};

  /* GPIO Ports Clock Enable */
  __HAL_RCC_GPIOA_CLK_ENABLE();
  __HAL_RCC_GPIOB_CLK_ENABLE();

  /*Configure GPIO pin Output Level */
  HAL_GPIO_WritePin(GPIOA, GPIO_PIN_6, GPIO_PIN_RESET);

  /*Configure GPIO pin Output Level */
  HAL_GPIO_WritePin(GPIOB, GPIO_PIN_3, GPIO_PIN_RESET);

  /*Configure GPIO pin : PA6 */
  GPIO_InitStruct.Pin = GPIO_PIN_6;
  GPIO_InitStruct.Mode = GPIO_MODE_OUTPUT_OD;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  GPIO_InitStruct.Speed = GPIO_SPEED_FREQ_LOW;
  HAL_GPIO_Init(GPIOA, &GPIO_InitStruct);

  /*Configure GPIO pin : PA11 */
  GPIO_InitStruct.Pin = GPIO_PIN_11;
  GPIO_InitStruct.Mode = GPIO_MODE_EVT_RISING;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  HAL_GPIO_Init(GPIOA, &GPIO_InitStruct);

  /*Configure GPIO pin : PA15 */
  GPIO_InitStruct.Pin = GPIO_PIN_15;
  GPIO_InitStruct.Mode = GPIO_MODE_INPUT;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  HAL_GPIO_Init(GPIOA, &GPIO_InitStruct);

  /*Configure GPIO pin : PB3 */
  GPIO_InitStruct.Pin = GPIO_PIN_3;
  GPIO_InitStruct.Mode = GPIO_MODE_OUTPUT_PP;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  GPIO_InitStruct.Speed = GPIO_SPEED_FREQ_LOW;
  HAL_GPIO_Init(GPIOB, &GPIO_InitStruct);

}

/* USER CODE BEGIN 4 */

/* USER CODE END 4 */

/**
  * @brief  This function is executed in case of error occurrence.
  * @retval None
  */
void Error_Handler(void)
{
  /* USER CODE BEGIN Error_Handler_Debug */
  /* User can add his own implementation to report the HAL error return state */

  /* USER CODE END Error_Handler_Debug */
}

#ifdef  USE_FULL_ASSERT
/**
  * @brief  Reports the name of the source file and the source line number
  *         where the assert_param error has occurred.
  * @param  file: pointer to the source file name
  * @param  line: assert_param error line source number
  * @retval None
  */
void assert_failed(uint8_t *file, uint32_t line)
{
  /* USER CODE BEGIN 6 */
  /* User can add his own implementation to report the file name and line number,
     tex: printf("Wrong parameters value: file %s on line %d\r\n", file, line) */
  /* USER CODE END 6 */
}
#endif /* USE_FULL_ASSERT */

/************************ (C) COPYRIGHT STMicroelectronics *****END OF FILE****/