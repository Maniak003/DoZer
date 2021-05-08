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
#define LED_PIN GPIO_PIN_3
#define COM_PIN GPIO_PIN_6
#define BUZZER GPIO_PIN_3
#define ANALOG_INPUT
#define sizeCommand 10
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
uint32_t cfgData, counterCC, counterALL, sleepDelay, oldTimeAll;
uint16_t adcResult;
uint16_t spectrData[2050];
uint16_t spectrCRC;
uint8_t btCommand[sizeCommand];

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
/* USER CODE BEGIN Private defines */

/* USER CODE END Private defines */

#ifdef __cplusplus
}
#endif

#endif /* __MAIN_H */

/************************ (C) COPYRIGHT STMicroelectronics *****END OF FILE****/
