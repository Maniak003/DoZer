################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
S_SRCS += \
../Core/Startup/startup_stm32g031f8px.s 

OBJS += \
./Core/Startup/startup_stm32g031f8px.o 

S_DEPS += \
./Core/Startup/startup_stm32g031f8px.d 


# Each subdirectory must supply rules for building sources it contributes
Core/Startup/startup_stm32g031f8px.o: ../Core/Startup/startup_stm32g031f8px.s
	arm-none-eabi-gcc -mcpu=cortex-m0plus -c -x assembler-with-cpp -MMD -MP -MF"Core/Startup/startup_stm32g031f8px.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@" "$<"

