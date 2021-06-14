/* USER CODE BEGIN Header */
/**
  ******************************************************************************
  * @file    stm32l4xx_it.c
  * @brief   Interrupt Service Routines.
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
#include "stm32l4xx_it.h"
/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */
/* USER CODE END Includes */

/* Private typedef -----------------------------------------------------------*/
/* USER CODE BEGIN TD */

/* USER CODE END TD */

/* Private define ------------------------------------------------------------*/
/* USER CODE BEGIN PD */

/* USER CODE END PD */

/* Private macro -------------------------------------------------------------*/
/* USER CODE BEGIN PM */

/* USER CODE END PM */

/* Private variables ---------------------------------------------------------*/
/* USER CODE BEGIN PV */

/* USER CODE END PV */

/* Private function prototypes -----------------------------------------------*/
/* USER CODE BEGIN PFP */

/* USER CODE END PFP */

/* Private user code ---------------------------------------------------------*/
/* USER CODE BEGIN 0 */

/* USER CODE END 0 */

/* External variables --------------------------------------------------------*/
extern ADC_HandleTypeDef hadc1;
extern ADC_HandleTypeDef hadc2;
extern LPTIM_HandleTypeDef hlptim2;
extern TIM_HandleTypeDef htim6;
extern TIM_HandleTypeDef htim15;
extern TIM_HandleTypeDef htim16;
extern DMA_HandleTypeDef hdma_usart1_rx;
/* USER CODE BEGIN EV */
/* USER CODE END EV */

/******************************************************************************/
/*           Cortex-M4 Processor Interruption and Exception Handlers          */
/******************************************************************************/
/**
  * @brief This function handles Non maskable interrupt.
  */
void NMI_Handler(void)
{
  /* USER CODE BEGIN NonMaskableInt_IRQn 0 */

  /* USER CODE END NonMaskableInt_IRQn 0 */
  /* USER CODE BEGIN NonMaskableInt_IRQn 1 */

  /* USER CODE END NonMaskableInt_IRQn 1 */
}

/**
  * @brief This function handles Hard fault interrupt.
  */
void HardFault_Handler(void)
{
  /* USER CODE BEGIN HardFault_IRQn 0 */

  /* USER CODE END HardFault_IRQn 0 */
  while (1)
  {
    /* USER CODE BEGIN W1_HardFault_IRQn 0 */
    /* USER CODE END W1_HardFault_IRQn 0 */
  }
}

/**
  * @brief This function handles Memory management fault.
  */
void MemManage_Handler(void)
{
  /* USER CODE BEGIN MemoryManagement_IRQn 0 */

  /* USER CODE END MemoryManagement_IRQn 0 */
  while (1)
  {
    /* USER CODE BEGIN W1_MemoryManagement_IRQn 0 */
    /* USER CODE END W1_MemoryManagement_IRQn 0 */
  }
}

/**
  * @brief This function handles Prefetch fault, memory access fault.
  */
void BusFault_Handler(void)
{
  /* USER CODE BEGIN BusFault_IRQn 0 */

  /* USER CODE END BusFault_IRQn 0 */
  while (1)
  {
    /* USER CODE BEGIN W1_BusFault_IRQn 0 */
    /* USER CODE END W1_BusFault_IRQn 0 */
  }
}

/**
  * @brief This function handles Undefined instruction or illegal state.
  */
void UsageFault_Handler(void)
{
  /* USER CODE BEGIN UsageFault_IRQn 0 */

  /* USER CODE END UsageFault_IRQn 0 */
  while (1)
  {
    /* USER CODE BEGIN W1_UsageFault_IRQn 0 */
    /* USER CODE END W1_UsageFault_IRQn 0 */
  }
}

/**
  * @brief This function handles System service call via SWI instruction.
  */
void SVC_Handler(void)
{
  /* USER CODE BEGIN SVCall_IRQn 0 */

  /* USER CODE END SVCall_IRQn 0 */
  /* USER CODE BEGIN SVCall_IRQn 1 */

  /* USER CODE END SVCall_IRQn 1 */
}

/**
  * @brief This function handles Debug monitor.
  */
void DebugMon_Handler(void)
{
  /* USER CODE BEGIN DebugMonitor_IRQn 0 */

  /* USER CODE END DebugMonitor_IRQn 0 */
  /* USER CODE BEGIN DebugMonitor_IRQn 1 */

  /* USER CODE END DebugMonitor_IRQn 1 */
}

/**
  * @brief This function handles Pendable request for system service.
  */
void PendSV_Handler(void)
{
  /* USER CODE BEGIN PendSV_IRQn 0 */

  /* USER CODE END PendSV_IRQn 0 */
  /* USER CODE BEGIN PendSV_IRQn 1 */

  /* USER CODE END PendSV_IRQn 1 */
}

/**
  * @brief This function handles System tick timer.
  */
void SysTick_Handler(void)
{
  /* USER CODE BEGIN SysTick_IRQn 0 */

  /* USER CODE END SysTick_IRQn 0 */
  HAL_IncTick();
  /* USER CODE BEGIN SysTick_IRQn 1 */

  /* USER CODE END SysTick_IRQn 1 */
}

/******************************************************************************/
/* STM32L4xx Peripheral Interrupt Handlers                                    */
/* Add here the Interrupt Handlers for the used peripherals.                  */
/* For the available peripheral interrupt handler names,                      */
/* please refer to the startup file (startup_stm32l4xx.s).                    */
/******************************************************************************/

/**
  * @brief This function handles DMA1 channel5 global interrupt.
  */
void DMA1_Channel5_IRQHandler(void)
{
  /* USER CODE BEGIN DMA1_Channel5_IRQn 0 */

  /* USER CODE END DMA1_Channel5_IRQn 0 */
  HAL_DMA_IRQHandler(&hdma_usart1_rx);
  /* USER CODE BEGIN DMA1_Channel5_IRQn 1 */

  /* USER CODE END DMA1_Channel5_IRQn 1 */
}

/**
  * @brief This function handles ADC1 and ADC2 interrupts.
  */
void ADC1_2_IRQHandler(void)
{
  /* USER CODE BEGIN ADC1_2_IRQn 0 */
	uint16_t batResult;
	uint32_t nowInterval;
	if( __HAL_ADC_GET_FLAG(&hadc1, ADC_ISR_EOC) != RESET) {
	  adcResult = HAL_ADC_GetValue(&hadc1);
	  if (adcResult > 0) {
		  adcResult = adcResult & 0x0FFF;
		  adcResult = adcResult >> 2;
		  adcResult = adcResult + 6; // Reserved additional parameter in send buffer ( 12 bytes )
		  if (spectrData[adcResult] < 0xFFFF) // Check overflow in channel.
			  spectrData[adcResult]++;
		  counterCC++;
		  counterALL++;

		  /* intervals for radiation level */
		  nowInterval = HAL_GetTick();
		  if (oldInterval > 0) {
			  radBuffer[indexBuffer++] = nowInterval - oldInterval;
			  if (indexBuffer > radBufferSize - 1) { // Buffer size = radBufferSize.
				  indexBuffer = 0;
			  }
		  }
		  oldInterval = nowInterval;

		  if ((cfgData & 64) > 0) { // Check config data for LED activity
			  HAL_GPIO_WritePin(GPIOB, LED_PIN, GPIO_PIN_SET); // LED on.
			  HAL_TIM_Base_Start_IT(&htim15); // Start timer for turn off LED.
		  }
	  }
	}
	// Battery voltage.
	if( __HAL_ADC_GET_FLAG(&hadc2, ADC_ISR_EOC) != RESET) {
		HAL_GPIO_WritePin(GPIOA, COM_PIN, GPIO_PIN_SET);
		batResult = HAL_ADC_GetValue(&hadc2);
		if ( batResult > 0 ) {
			spectrData[4] = (spectrData[4] & 0xFF00) | (batResult & 0x00FF);
		}
	}
  /* USER CODE END ADC1_2_IRQn 0 */
  HAL_ADC_IRQHandler(&hadc1);
  HAL_ADC_IRQHandler(&hadc2);
  /* USER CODE BEGIN ADC1_2_IRQn 1 */

  /* USER CODE END ADC1_2_IRQn 1 */
}

/**
  * @brief This function handles TIM1 break interrupt and TIM15 global interrupt.
  */
void TIM1_BRK_TIM15_IRQHandler(void)
{
  /* USER CODE BEGIN TIM1_BRK_TIM15_IRQn 0 */
	HAL_GPIO_WritePin(GPIOB, LED_PIN, GPIO_PIN_RESET); // LED off.
	//HAL_TIM_OC_Stop(&htim2, TIM_CHANNEL_4);

  /* USER CODE END TIM1_BRK_TIM15_IRQn 0 */
  HAL_TIM_IRQHandler(&htim15);
  /* USER CODE BEGIN TIM1_BRK_TIM15_IRQn 1 */
  HAL_TIM_Base_Stop_IT(&htim15);
  /* USER CODE END TIM1_BRK_TIM15_IRQn 1 */
}

/**
  * @brief This function handles TIM1 update interrupt and TIM16 global interrupt.
  */
void TIM1_UP_TIM16_IRQHandler(void)
{
  /* USER CODE BEGIN TIM1_UP_TIM16_IRQn 0 */
	HAL_TIM_OC_Stop(&htim2, TIM_CHANNEL_4);

  /* USER CODE END TIM1_UP_TIM16_IRQn 0 */
  HAL_TIM_IRQHandler(&htim16);
  /* USER CODE BEGIN TIM1_UP_TIM16_IRQn 1 */
  HAL_TIM_Base_Stop_IT(&htim16);
  /* USER CODE END TIM1_UP_TIM16_IRQn 1 */
}

/**
  * @brief This function handles TIM6 global interrupt.
  */
void TIM6_IRQHandler(void)
{
  /* USER CODE BEGIN TIM6_IRQn 0 */

  /* USER CODE END TIM6_IRQn 0 */
  HAL_TIM_IRQHandler(&htim6);
  /* USER CODE BEGIN TIM6_IRQn 1 */
  if ((cfgData & 0x15) > 0 ){  // Sound on.
	  switch (alarmLevel) {
	  case 0:
		  alarmCount = 0;
		  break;
	  case 1:
		  if (alarmCount-- <= 0) {
			  alarmCount = 4;
		  } else {
			  if (alarmCount > 2) {
				  if ((cfgData & 0x1) != 0) { // Check enabled flag sound level 1
					  HAL_TIM_OC_Start(&htim2, TIM_CHANNEL_4);
					  HAL_TIM_Base_Start_IT(&htim16); // Start timer for turn off Buzzer
				  }
			  }
		  }
		  break;
	  case 2:
			  if (alarmCount-- <= 0) {
				  alarmCount = 5;
			  } else {
				  if (alarmCount > 2) {
					  if ((cfgData & 0x4) != 0) {  // Check enabled flag sound level 2
						  HAL_TIM_OC_Start(&htim2, TIM_CHANNEL_4);
						  HAL_TIM_Base_Start_IT(&htim16); // Start timer for turn off Buzzer
					  }
				  }
			  }
		  break;
	  case 3:
			  if (alarmCount-- <= 0) {
				  alarmCount = 6;
			  } else {
				  if (alarmCount > 2) {
					  if ((cfgData & 0x10) != 0) { // Check enabled flag sound level 3
						  HAL_TIM_OC_Start(&htim2, TIM_CHANNEL_4);
						  HAL_TIM_Base_Start_IT(&htim16); // Start timer for turn off Buzzer
					  }
				  }
			  }
		  break;
	  }
  }

  /* USER CODE END TIM6_IRQn 1 */
}

/**
  * @brief This function handles LPTIM2 global interrupt.
  */
void LPTIM2_IRQHandler(void)
{
  /* USER CODE BEGIN LPTIM2_IRQn 0 */
  uint32_t realCount;
  //uint8_t s[100];

  /* USER CODE END LPTIM2_IRQn 0 */
  HAL_LPTIM_IRQHandler(&hlptim2);
  /* USER CODE BEGIN LPTIM2_IRQn 1 */
  avgRadInterval = 0;
  realCount = 0;
  for ( int i = 0; i < radBufferSize; i++) {
	  if (radBuffer[i] > 0) {
		  realCount++;
		  avgRadInterval = avgRadInterval + radBuffer[i];
	  }
  }
  if (realCount > 0) {
	  avgRadInterval = avgRadInterval / realCount;
	  if (avgRadInterval < Thr3) {
			  alarmLevel = 3;
	  } else {
		  if (avgRadInterval < Thr2) {
				  alarmLevel = 2;
		  } else {
			  if (avgRadInterval < Thr1) {
					  alarmLevel = 1;
			  } else {
				  alarmLevel = 0;  // Disable alarm sound
			  }
		  }
	  }
  }
  //sprintf(s, "Avg: %d, Cnt: %d, alarm: %d\r\n", avgRadInterval, realCount, alarmLevel);
  //HAL_UART_Transmit(&huart1, s, strlen((char *)s), 1000);
  /* USER CODE END LPTIM2_IRQn 1 */
}

/* USER CODE BEGIN 1 */

/* USER CODE END 1 */
/************************ (C) COPYRIGHT STMicroelectronics *****END OF FILE****/
