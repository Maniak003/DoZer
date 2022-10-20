/* USER CODE BEGIN Header */

//#include <FlashPROM.h>
/**
  ******************************************************************************
  * @file           : main.h
  * @brief          : Header for main.c file.
  *                   This file contains the common defines of the application.
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
//#define DISPLAY_ENABLE
#define INIT_TIME  2000
#define TRANSMIT_DALAY 20
#define SLEEPDALAY 1000
//#define LED_PULSE_ENABLE
#define VIBRO_PIN GPIO_PIN_1
#define LED_PIN GPIO_PIN_3
#define COM_PIN GPIO_PIN_6
#define SCK_DAC GPIO_PIN_4
#define SDI_DAC GPIO_PIN_8
#define CS_DAC GPIO_PIN_12
#define BUZZER GPIO_PIN_3
#define ANALOG_INPUT
#define sizeCommand 20
#define radBufferSize 50
#define reservDataSize 6
#define batteryMeasureInterval 20000
#define logSize 200
/* USER CODE END Header */

/* Define to prevent recursive inclusion -------------------------------------*/
#ifndef __MAIN_H
#define __MAIN_H

#ifdef __cplusplus
extern "C" {
#endif

/* Includes ------------------------------------------------------------------*/
#include "stm32l4xx_hal.h"

/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */

/* USER CODE END Includes */

/* Exported types ------------------------------------------------------------*/
/* USER CODE BEGIN ET */

/* USER CODE END ET */

/* Exported constants --------------------------------------------------------*/
/* USER CODE BEGIN EC */
HAL_StatusTypeDef	flash_ok;
TIM_HandleTypeDef htim2;
UART_HandleTypeDef huart1;
char counterPP[20];
uint16_t cfgLevel1, cfgLevel2, cfgLevel3, cfgData, alarmLevel, alarmCount, oldAlarmLevel, temperatureKoeff1, temperatureKoeff2;
uint32_t counterCCAlarm, counterCC, counterALL, sleepDelay, alarmTime, oldTimeAll, oldInterval, avgRadInterval, Thr1, Thr2, Thr3, batteryInterval;
uint16_t adc2Result, adc1Result[2];
uint16_t spectrData[4096 + reservDataSize][2];
uint16_t spectrCRC;
uint8_t btCommand[sizeCommand];
uint8_t indexBuffer;
uint32_t radBuffer[radBufferSize];
uint8_t	resolution, specterHistory;

typedef struct {
	uint32_t timeData;
	uint8_t eventType;
	uint32_t event_data;
} logData;

logData logDat[logSize];

uint8_t logIndex, logRecords;


//float cfgKoefRh;

/* USER CODE END EC */

/* Exported macro ------------------------------------------------------------*/
/* USER CODE BEGIN EM */

/* USER CODE END EM */

void HAL_TIM_MspPostInit(TIM_HandleTypeDef *htim);

/* Exported functions prototypes ---------------------------------------------*/
void Error_Handler(void);

/* USER CODE BEGIN EFP */

/* USER CODE END EFP */

/* Private defines -----------------------------------------------------------*/
#define Vibro_Pin GPIO_PIN_1
#define Vibro_GPIO_Port GPIOA
#define COM_Pin GPIO_PIN_6
#define COM_GPIO_Port GPIOA
#define BAT_LEVEL_Pin GPIO_PIN_7
#define BAT_LEVEL_GPIO_Port GPIOA
#define SiPM_Pin GPIO_PIN_1
#define SiPM_GPIO_Port GPIOB
#define BT_TX_Pin GPIO_PIN_9
#define BT_TX_GPIO_Port GPIOA
#define BT_RX_Pin GPIO_PIN_10
#define BT_RX_GPIO_Port GPIOA
#define StartADC_Pin GPIO_PIN_11
#define StartADC_GPIO_Port GPIOA
#define CS_Pin GPIO_PIN_12
#define CS_GPIO_Port GPIOA
#define BT_Status_Pin GPIO_PIN_15
#define BT_Status_GPIO_Port GPIOA
#define LED_Pin GPIO_PIN_3
#define LED_GPIO_Port GPIOB
#define SC_Pin GPIO_PIN_4
#define SC_GPIO_Port GPIOB
/* USER CODE BEGIN Private defines */

/* USER CODE END Private defines */

#ifdef __cplusplus
}
#endif

#endif /* __MAIN_H */

/************************ (C) COPYRIGHT STMicroelectronics *****END OF FILE****/
