EESchema Schematic File Version 4
EELAYER 30 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 1 1
Title ""
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L SC-STM32L412K8-rescue:STM32L412K8-My_Library-SC-STM32L412K8-rescue D6
U 1 1 60361EE9
P 7350 4450
F 0 "D6" H 7350 4500 50  0000 C CNN
F 1 "STM32L412K8" H 8150 3650 50  0000 C CNN
F 2 "Package_QFP:LQFP-32_7x7mm_P0.8mm" H 7500 5350 50  0001 C CNN
F 3 "" H 7350 4450 50  0001 C CNN
	1    7350 4450
	1    0    0    -1  
$EndComp
$Comp
L Device:R R3
U 1 1 60363E96
P 4850 2300
F 0 "R3" H 4650 2350 50  0000 L CNN
F 1 "13k" H 4650 2250 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 4780 2300 50  0001 C CNN
F 3 "~" H 4850 2300 50  0001 C CNN
	1    4850 2300
	1    0    0    -1  
$EndComp
$Comp
L Device:C C5
U 1 1 60364259
P 5000 2150
F 0 "C5" V 5150 2100 50  0000 C CNN
F 1 "82pF" V 5250 2150 50  0000 C CNN
F 2 "Capacitor_SMD:C_0603_1608Metric" H 5038 2000 50  0001 C CNN
F 3 "~" H 5000 2150 50  0001 C CNN
	1    5000 2150
	0    1    1    0   
$EndComp
$Comp
L Device:D D3
U 1 1 6036469D
P 5600 2900
F 0 "D3" H 5600 2684 50  0000 C CNN
F 1 "MBR0540" H 5600 2775 50  0000 C CNN
F 2 "Diode_SMD:D_SOD-123" H 5600 2900 50  0001 C CNN
F 3 "~" H 5600 2900 50  0001 C CNN
	1    5600 2900
	-1   0    0    1   
$EndComp
$Comp
L Device:L L2
U 1 1 603653A2
P 4550 1450
F 0 "L2" V 4740 1450 50  0000 C CNN
F 1 "22uH" V 4649 1450 50  0000 C CNN
F 2 "Inductor_SMD:L_1210_3225Metric" H 4550 1450 50  0001 C CNN
F 3 "~" H 4550 1450 50  0001 C CNN
	1    4550 1450
	0    -1   -1   0   
$EndComp
$Comp
L power:GND #PWR013
U 1 1 6036595A
P 7300 3100
F 0 "#PWR013" H 7300 2850 50  0001 C CNN
F 1 "GND" H 7305 2927 50  0001 C CNN
F 2 "" H 7300 3100 50  0001 C CNN
F 3 "" H 7300 3100 50  0001 C CNN
	1    7300 3100
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR06
U 1 1 60366A30
P 6150 5500
F 0 "#PWR06" H 6150 5250 50  0001 C CNN
F 1 "GND" H 6155 5327 50  0001 C CNN
F 2 "" H 6150 5500 50  0001 C CNN
F 3 "" H 6150 5500 50  0001 C CNN
	1    6150 5500
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR012
U 1 1 60366E3E
P 7300 5700
F 0 "#PWR012" H 7300 5450 50  0001 C CNN
F 1 "GND" H 7305 5527 50  0001 C CNN
F 2 "" H 7300 5700 50  0001 C CNN
F 3 "" H 7300 5700 50  0001 C CNN
	1    7300 5700
	1    0    0    -1  
$EndComp
$Comp
L Device:R R7
U 1 1 6036B072
P 5550 4700
F 0 "R7" H 5350 4800 50  0000 L CNN
F 1 "100k" H 5300 4700 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 5480 4700 50  0001 C CNN
F 3 "~" H 5550 4700 50  0001 C CNN
	1    5550 4700
	1    0    0    -1  
$EndComp
$Comp
L Device:R R8
U 1 1 6036B7B6
P 5550 5000
F 0 "R8" H 5620 5046 50  0000 L CNN
F 1 "220" H 5620 4955 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 5480 5000 50  0001 C CNN
F 3 "~" H 5550 5000 50  0001 C CNN
	1    5550 5000
	1    0    0    -1  
$EndComp
$Comp
L Device:R R9
U 1 1 6036BC49
P 5550 5350
F 0 "R9" H 5620 5396 50  0000 L CNN
F 1 "12k" H 5620 5305 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 5480 5350 50  0001 C CNN
F 3 "~" H 5550 5350 50  0001 C CNN
	1    5550 5350
	1    0    0    -1  
$EndComp
$Comp
L Device:C C6
U 1 1 6036D36F
P 5400 5150
F 0 "C6" V 5148 5150 50  0000 C CNN
F 1 "470pF" V 5239 5150 50  0000 C CNN
F 2 "Capacitor_SMD:C_0603_1608Metric" H 5438 5000 50  0001 C CNN
F 3 "~" H 5400 5150 50  0001 C CNN
	1    5400 5150
	0    1    1    0   
$EndComp
$Comp
L Device:R R2
U 1 1 6036DAFE
P 5250 5350
F 0 "R2" H 5320 5396 50  0000 L CNN
F 1 "2.2k" H 5320 5305 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 5180 5350 50  0001 C CNN
F 3 "~" H 5250 5350 50  0001 C CNN
	1    5250 5350
	1    0    0    -1  
$EndComp
$Comp
L Device:C C9
U 1 1 6036E064
P 5850 5350
F 0 "C9" H 5965 5396 50  0000 L CNN
F 1 "10nF" H 5965 5305 50  0000 L CNN
F 2 "Capacitor_SMD:C_0603_1608Metric" H 5888 5200 50  0001 C CNN
F 3 "~" H 5850 5350 50  0001 C CNN
	1    5850 5350
	1    0    0    -1  
$EndComp
$Comp
L Device:R R10
U 1 1 60373BCB
P 5700 4850
F 0 "R10" V 5493 4850 50  0000 C CNN
F 1 "4.7k" V 5584 4850 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 5630 4850 50  0001 C CNN
F 3 "~" H 5700 4850 50  0001 C CNN
	1    5700 4850
	0    1    1    0   
$EndComp
$Comp
L Device:C C12
U 1 1 603759D5
P 6300 4650
F 0 "C12" H 6415 4696 50  0000 L CNN
F 1 "22uF" H 6415 4605 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 6338 4500 50  0001 C CNN
F 3 "~" H 6300 4650 50  0001 C CNN
	1    6300 4650
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR07
U 1 1 60376345
P 6300 4800
F 0 "#PWR07" H 6300 4550 50  0001 C CNN
F 1 "GND" H 6305 4627 50  0001 C CNN
F 2 "" H 6300 4800 50  0001 C CNN
F 3 "" H 6300 4800 50  0001 C CNN
	1    6300 4800
	1    0    0    -1  
$EndComp
Connection ~ 6150 4500
Wire Wire Line
	6150 4500 6150 4800
Wire Wire Line
	5850 4850 5850 4950
Wire Wire Line
	5950 4950 5850 4950
Wire Wire Line
	7450 5600 7300 5600
Wire Wire Line
	7300 5600 7300 5700
Connection ~ 7300 5600
Wire Wire Line
	7400 3800 7300 3800
$Comp
L Device:L L4
U 1 1 6037B2E7
P 7500 3550
F 0 "L4" H 7553 3596 50  0000 L CNN
F 1 "47uH" H 7550 3500 50  0000 L CNN
F 2 "Inductor_SMD:L_0805_2012Metric" H 7500 3550 50  0001 C CNN
F 3 "~" H 7500 3550 50  0001 C CNN
	1    7500 3550
	1    0    0    -1  
$EndComp
$Comp
L Device:C C18
U 1 1 6037FBDB
P 7700 3750
F 0 "C18" V 7500 3900 50  0000 C CNN
F 1 "22uF" V 7600 3950 50  0000 C CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 7738 3600 50  0001 C CNN
F 3 "~" H 7700 3750 50  0001 C CNN
	1    7700 3750
	0    1    1    0   
$EndComp
$Comp
L power:GND #PWR015
U 1 1 60380B9C
P 8200 3750
F 0 "#PWR015" H 8200 3500 50  0001 C CNN
F 1 "GND" V 8205 3622 50  0001 R CNN
F 2 "" H 8200 3750 50  0001 C CNN
F 3 "" H 8200 3750 50  0001 C CNN
	1    8200 3750
	0    -1   -1   0   
$EndComp
$Comp
L Device:C C20
U 1 1 6038211C
P 8100 3900
F 0 "C20" H 8215 3946 50  0000 L CNN
F 1 "0.1uF" H 8215 3855 50  0000 L CNN
F 2 "Capacitor_SMD:C_0603_1608Metric" H 8138 3750 50  0001 C CNN
F 3 "~" H 8100 3900 50  0001 C CNN
	1    8100 3900
	1    0    0    -1  
$EndComp
Connection ~ 7300 3800
Wire Wire Line
	7950 4050 8100 4050
$Comp
L Device:C C17
U 1 1 6039B0E7
P 7100 3550
F 0 "C17" H 7100 3900 50  0000 L CNN
F 1 "22uF" H 7100 3800 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 7138 3400 50  0001 C CNN
F 3 "~" H 7100 3550 50  0001 C CNN
	1    7100 3550
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR010
U 1 1 6039D537
P 7100 3750
F 0 "#PWR010" H 7100 3500 50  0001 C CNN
F 1 "GND" H 7105 3577 50  0001 C CNN
F 2 "" H 7100 3750 50  0001 C CNN
F 3 "" H 7100 3750 50  0001 C CNN
	1    7100 3750
	1    0    0    -1  
$EndComp
$Comp
L Regulator_Linear:LP2985-3.0 U1
U 1 1 603A10D1
P 4750 4600
F 0 "U1" H 4750 4942 50  0000 C CNN
F 1 "LP2985-3.0" H 4750 4851 50  0000 C CNN
F 2 "Package_TO_SOT_SMD:SOT-23-5" H 4750 4925 50  0001 C CNN
F 3 "http://www.ti.com/lit/ds/symlink/lp2985.pdf" H 4750 4600 50  0001 C CNN
	1    4750 4600
	1    0    0    -1  
$EndComp
$Comp
L Device:C C4
U 1 1 603A214C
P 5200 4750
F 0 "C4" H 5200 5200 50  0000 L CNN
F 1 "0.1uF" H 5150 5100 50  0000 L CNN
F 2 "Capacitor_SMD:C_0603_1608Metric" H 5238 4600 50  0001 C CNN
F 3 "~" H 5200 4750 50  0001 C CNN
	1    5200 4750
	1    0    0    -1  
$EndComp
Wire Wire Line
	5150 4600 5200 4600
$Comp
L Device:C C2
U 1 1 603A63DB
P 4250 4750
F 0 "C2" H 4350 4500 50  0000 L CNN
F 1 "22uF" H 4350 4400 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 4288 4600 50  0001 C CNN
F 3 "~" H 4250 4750 50  0001 C CNN
	1    4250 4750
	1    0    0    -1  
$EndComp
Wire Wire Line
	4350 4500 4250 4500
Wire Wire Line
	4250 4500 4250 4600
Wire Wire Line
	4250 4900 4250 5500
Wire Wire Line
	4750 4900 4250 4900
Connection ~ 4750 4900
Connection ~ 4250 4900
Wire Wire Line
	4350 4600 4250 4600
Connection ~ 4250 4600
Text GLabel 7950 4300 2    50   Input ~ 0
AIn
Text GLabel 5150 5150 0    50   Input ~ 0
AIn
$Comp
L Device:C C16
U 1 1 603AB7C1
P 6850 3550
F 0 "C16" H 6550 3600 50  0000 L CNN
F 1 "22uF" H 6550 3500 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 6888 3400 50  0001 C CNN
F 3 "~" H 6850 3550 50  0001 C CNN
	1    6850 3550
	1    0    0    -1  
$EndComp
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue J5
U 1 1 603AE044
P 6750 5250
F 0 "J5" H 6692 5025 50  0001 C CNN
F 1 "SWDIO" H 6900 5250 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 6750 5250 50  0001 C CNN
F 3 "~" H 6750 5250 50  0001 C CNN
	1    6750 5250
	-1   0    0    1   
$EndComp
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue J6
U 1 1 603AE9C5
P 6750 5350
F 0 "J6" H 6692 5125 50  0001 C CNN
F 1 "SWCLK" H 6900 5350 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 6750 5350 50  0001 C CNN
F 3 "~" H 6750 5350 50  0001 C CNN
	1    6750 5350
	-1   0    0    1   
$EndComp
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue J3
U 1 1 603B523F
P 6750 4850
F 0 "J3" H 6692 4625 50  0001 C CNN
F 1 "TxD" H 6850 4850 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 6750 4850 50  0001 C CNN
F 3 "~" H 6750 4850 50  0001 C CNN
	1    6750 4850
	-1   0    0    1   
$EndComp
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue J4
U 1 1 603B58EC
P 6750 4950
F 0 "J4" H 6692 4725 50  0001 C CNN
F 1 "RxD" H 6850 4950 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 6750 4950 50  0001 C CNN
F 3 "~" H 6750 4950 50  0001 C CNN
	1    6750 4950
	-1   0    0    1   
$EndComp
$Comp
L Battery_Management:LTC4054ES5-4.2 U3
U 1 1 603B9C75
P 4800 3600
F 0 "U3" H 5150 3300 50  0000 L CNN
F 1 "LTC4054ES5" H 4850 3200 50  0000 L CNN
F 2 "Package_TO_SOT_SMD:TSOT-23-5" H 4800 3100 50  0001 C CNN
F 3 "http://cds.linear.com/docs/en/datasheet/405442xf.pdf" H 4800 3500 50  0001 C CNN
	1    4800 3600
	1    0    0    -1  
$EndComp
$Comp
L Device:R R4
U 1 1 603BA35A
P 4600 3250
F 0 "R4" V 4393 3250 50  0000 C CNN
F 1 "1k" V 4484 3250 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 4530 3250 50  0001 C CNN
F 3 "~" H 4600 3250 50  0001 C CNN
	1    4600 3250
	0    1    1    0   
$EndComp
$Comp
L Device:LED D1
U 1 1 603BB323
P 4300 3400
F 0 "D1" V 4350 3600 50  0000 R CNN
F 1 "LED" V 4250 3650 50  0000 R CNN
F 2 "LED_SMD:LED_0805_2012Metric" H 4300 3400 50  0001 C CNN
F 3 "~" H 4300 3400 50  0001 C CNN
	1    4300 3400
	0    -1   -1   0   
$EndComp
$Comp
L Device:R R1
U 1 1 603BC118
P 4300 3900
F 0 "R1" H 4370 3946 50  0000 L CNN
F 1 "20k" H 4370 3855 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 4230 3900 50  0001 C CNN
F 3 "~" H 4300 3900 50  0001 C CNN
	1    4300 3900
	1    0    0    -1  
$EndComp
$Comp
L Device:R R6
U 1 1 603BCA72
P 4800 3050
F 0 "R6" H 4870 3096 50  0000 L CNN
F 1 "1" H 4870 3005 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 4730 3050 50  0001 C CNN
F 3 "~" H 4800 3050 50  0001 C CNN
	1    4800 3050
	1    0    0    -1  
$EndComp
$Comp
L Device:Q_PMOS_GSD Q1
U 1 1 603BDB5F
P 5600 3250
F 0 "Q1" V 5500 3400 50  0000 C CNN
F 1 "IRLML5203" V 5852 3250 50  0000 C CNN
F 2 "Package_TO_SOT_SMD:SOT-23" H 5800 3350 50  0001 C CNN
F 3 "~" H 5600 3250 50  0001 C CNN
	1    5600 3250
	0    -1   -1   0   
$EndComp
$Comp
L Device:R R12
U 1 1 603BEF4A
P 5600 3900
F 0 "R12" H 5650 4150 50  0000 L CNN
F 1 "10k" H 5650 4050 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 5530 3900 50  0001 C CNN
F 3 "~" H 5600 3900 50  0001 C CNN
	1    5600 3900
	1    0    0    -1  
$EndComp
$Comp
L Device:C C10
U 1 1 603BF696
P 5350 3900
F 0 "C10" H 5400 4100 50  0000 L CNN
F 1 "22uF" H 5400 4000 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 5388 3750 50  0001 C CNN
F 3 "~" H 5350 3900 50  0001 C CNN
	1    5350 3900
	1    0    0    -1  
$EndComp
$Comp
L Device:C C13
U 1 1 603BFF56
P 5850 3900
F 0 "C13" H 5900 4100 50  0000 L CNN
F 1 "22uF" H 5900 4000 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 5888 3750 50  0001 C CNN
F 3 "~" H 5850 3900 50  0001 C CNN
	1    5850 3900
	1    0    0    -1  
$EndComp
$Comp
L Connector:USB_B_Micro J1
U 1 1 603CD9A3
P 3900 3100
F 0 "J1" H 4300 3200 50  0000 C CNN
F 1 "usb" H 4300 3100 50  0000 C CNN
F 2 "Connector_USB:USB_Micro-B_Molex_47346-0001" H 4050 3050 50  0001 C CNN
F 3 "~" H 4050 3050 50  0001 C CNN
	1    3900 3100
	1    0    0    -1  
$EndComp
Wire Wire Line
	3800 3500 3800 4050
Connection ~ 4300 4050
Wire Wire Line
	4300 4050 4800 4050
Wire Wire Line
	4800 4000 4800 4050
Connection ~ 4800 4050
Wire Wire Line
	4800 4050 5350 4050
Wire Wire Line
	5200 3600 5200 3150
Wire Wire Line
	4800 3200 4800 3250
Wire Wire Line
	4750 3250 4800 3250
Connection ~ 4800 3250
Wire Wire Line
	4800 3250 4800 3300
Wire Wire Line
	4450 3250 4300 3250
Wire Wire Line
	4300 3550 4300 3600
Wire Wire Line
	4300 3600 4400 3600
Wire Wire Line
	4400 3700 4300 3700
Wire Wire Line
	4300 3700 4300 3750
$Comp
L power:GND #PWR03
U 1 1 603F022D
P 3800 4050
F 0 "#PWR03" H 3800 3800 50  0001 C CNN
F 1 "GND" V 3805 3922 50  0001 R CNN
F 2 "" H 3800 4050 50  0001 C CNN
F 3 "" H 3800 4050 50  0001 C CNN
	1    3800 4050
	1    0    0    -1  
$EndComp
Connection ~ 3800 4050
$Comp
L Regulator_Switching:LM2733YMF U2
U 1 1 604040C9
P 4550 1850
F 0 "U2" H 4550 2217 50  0000 C CNN
F 1 "LM2733YMF" H 4550 2126 50  0000 C CNN
F 2 "Package_TO_SOT_SMD:SOT-23-5" H 4600 1600 50  0001 L CIN
F 3 "http://www.ti.com/lit/ds/symlink/lm2733.pdf" H 4550 1950 50  0001 C CNN
	1    4550 1850
	1    0    0    -1  
$EndComp
$Comp
L Device:D D2
U 1 1 6040F684
P 5000 1450
F 0 "D2" H 5000 1250 50  0000 C CNN
F 1 "MBR0540" H 5000 1350 50  0000 C CNN
F 2 "Diode_SMD:D_SOD-123" H 5000 1450 50  0001 C CNN
F 3 "~" H 5000 1450 50  0001 C CNN
	1    5000 1450
	-1   0    0    1   
$EndComp
$Comp
L Device:R R11
U 1 1 60429F72
P 5600 1450
F 0 "R11" V 5700 1450 50  0000 C CNN
F 1 "100" V 5800 1450 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 5530 1450 50  0001 C CNN
F 3 "~" H 5600 1450 50  0001 C CNN
	1    5600 1450
	0    1    1    0   
$EndComp
$Comp
L Device:C C7
U 1 1 60435680
P 5150 2300
F 0 "C7" H 5100 2200 50  0000 R CNN
F 1 "GRM32ER71H106K" H 5035 2345 50  0001 R CNN
F 2 "Capacitor_SMD:C_1210_3225Metric" H 5188 2150 50  0001 C CNN
F 3 "~" H 5150 2300 50  0001 C CNN
	1    5150 2300
	-1   0    0    1   
$EndComp
$Comp
L Device:C C3
U 1 1 604360AF
P 4250 2300
F 0 "C3" H 4135 2254 50  0000 R CNN
F 1 "22uF" H 4135 2345 50  0000 R CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 4288 2150 50  0001 C CNN
F 3 "~" H 4250 2300 50  0001 C CNN
	1    4250 2300
	-1   0    0    1   
$EndComp
$Comp
L Device:L L1
U 1 1 60436BF5
P 4050 1450
F 0 "L1" V 4000 1450 50  0000 C CNN
F 1 "10uH" V 3900 1450 50  0000 C CNN
F 2 "Inductor_SMD:L_0805_2012Metric" H 4050 1450 50  0001 C CNN
F 3 "~" H 4050 1450 50  0001 C CNN
	1    4050 1450
	0    -1   -1   0   
$EndComp
$Comp
L Device:C C1
U 1 1 60437474
P 3850 2300
F 0 "C1" H 3735 2254 50  0000 R CNN
F 1 "22uF" H 3735 2345 50  0000 R CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 3888 2150 50  0001 C CNN
F 3 "~" H 3850 2300 50  0001 C CNN
	1    3850 2300
	-1   0    0    1   
$EndComp
$Comp
L Device:L L3
U 1 1 60437DE2
P 5300 1450
F 0 "L3" V 5200 1450 50  0000 C CNN
F 1 "47uH" V 5100 1450 50  0000 C CNN
F 2 "Inductor_SMD:L_0805_2012Metric" H 5300 1450 50  0001 C CNN
F 3 "~" H 5300 1450 50  0001 C CNN
	1    5300 1450
	0    -1   -1   0   
$EndComp
$Comp
L Device:R R5
U 1 1 6043948F
P 5000 1950
F 0 "R5" V 4793 1950 50  0000 C CNN
F 1 "300k" V 4884 1950 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 4930 1950 50  0001 C CNN
F 3 "~" H 5000 1950 50  0001 C CNN
	1    5000 1950
	0    1    1    0   
$EndComp
$Comp
L Device:C C8
U 1 1 604432B8
P 5450 2300
F 0 "C8" H 5400 2200 50  0000 R CNN
F 1 "GRM32ER71H106K" H 5335 2345 50  0001 R CNN
F 2 "Capacitor_SMD:C_1210_3225Metric" H 5488 2150 50  0001 C CNN
F 3 "~" H 5450 2300 50  0001 C CNN
	1    5450 2300
	-1   0    0    1   
$EndComp
$Comp
L Device:C C11
U 1 1 6044654C
P 5750 2300
F 0 "C11" H 5700 2200 50  0000 R CNN
F 1 "GRM32ER71H106K" H 5635 2345 50  0001 R CNN
F 2 "Capacitor_SMD:C_1210_3225Metric" H 5788 2150 50  0001 C CNN
F 3 "~" H 5750 2300 50  0001 C CNN
	1    5750 2300
	-1   0    0    1   
$EndComp
$Comp
L Device:R R13
U 1 1 60446D66
P 5900 1450
F 0 "R13" V 6000 1450 50  0000 C CNN
F 1 "1k" V 6100 1450 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 5830 1450 50  0001 C CNN
F 3 "~" H 5900 1450 50  0001 C CNN
	1    5900 1450
	0    1    1    0   
$EndComp
$Comp
L Device:C C14
U 1 1 604473D0
P 6050 2300
F 0 "C14" H 6000 2200 50  0000 R CNN
F 1 "GRM32ER71H106K" H 5935 2345 50  0001 R CNN
F 2 "Capacitor_SMD:C_1210_3225Metric" H 6088 2150 50  0001 C CNN
F 3 "~" H 6050 2300 50  0001 C CNN
	1    6050 2300
	-1   0    0    1   
$EndComp
Connection ~ 4850 2450
Wire Wire Line
	4850 2450 5150 2450
Wire Wire Line
	4850 2150 4850 1950
Wire Wire Line
	4550 2150 4550 2450
Connection ~ 4550 2450
Wire Wire Line
	4550 2450 4850 2450
Wire Wire Line
	4250 1450 4250 1750
Connection ~ 4250 1750
Wire Wire Line
	4250 1750 4250 1950
Connection ~ 4250 2450
Wire Wire Line
	4250 2450 4550 2450
Wire Wire Line
	4250 2150 4250 1950
Connection ~ 4250 1950
Wire Wire Line
	3850 2450 4250 2450
Wire Wire Line
	3850 2150 3850 1450
Wire Wire Line
	3850 1450 3900 1450
Wire Wire Line
	4200 1450 4250 1450
Connection ~ 4250 1450
Wire Wire Line
	4250 1450 4400 1450
Wire Wire Line
	4700 1450 4850 1450
Wire Wire Line
	4850 1750 4850 1450
$Comp
L power:GND #PWR02
U 1 1 60484C98
P 3850 2450
F 0 "#PWR02" H 3850 2200 50  0001 C CNN
F 1 "GND" V 3855 2322 50  0001 R CNN
F 2 "" H 3850 2450 50  0001 C CNN
F 3 "" H 3850 2450 50  0001 C CNN
	1    3850 2450
	1    0    0    -1  
$EndComp
Connection ~ 3850 2450
$Comp
L Device:C C19
U 1 1 60487A06
P 7650 2950
F 0 "C19" H 7750 3000 50  0000 L CNN
F 1 "22uF" H 7750 2900 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 7688 2800 50  0001 C CNN
F 3 "~" H 7650 2950 50  0001 C CNN
	1    7650 2950
	1    0    0    -1  
$EndComp
Wire Wire Line
	7300 3100 7650 3100
Connection ~ 7300 3100
$Comp
L Device:C C15
U 1 1 604A052D
P 6950 2950
F 0 "C15" H 6650 3050 50  0000 L CNN
F 1 "22uF" H 6650 2950 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 6988 2800 50  0001 C CNN
F 3 "~" H 6950 2950 50  0001 C CNN
	1    6950 2950
	1    0    0    -1  
$EndComp
$Comp
L power:+BATT #PWR09
U 1 1 604DEE04
P 6950 2800
F 0 "#PWR09" H 6950 2650 50  0001 C CNN
F 1 "+BATT" H 6750 2900 50  0000 C CNN
F 2 "" H 6950 2800 50  0001 C CNN
F 3 "" H 6950 2800 50  0001 C CNN
	1    6950 2800
	1    0    0    -1  
$EndComp
$Comp
L power:+BATT #PWR04
U 1 1 604DF999
P 4250 4500
F 0 "#PWR04" H 4250 4350 50  0001 C CNN
F 1 "+BATT" H 4265 4673 50  0000 C CNN
F 2 "" H 4250 4500 50  0001 C CNN
F 3 "" H 4250 4500 50  0001 C CNN
	1    4250 4500
	1    0    0    -1  
$EndComp
Connection ~ 4250 4500
$Comp
L power:+BATT #PWR01
U 1 1 604E0AC3
P 3850 1450
F 0 "#PWR01" H 3850 1300 50  0001 C CNN
F 1 "+BATT" H 3950 1600 50  0000 C CNN
F 2 "" H 3850 1450 50  0001 C CNN
F 3 "" H 3850 1450 50  0001 C CNN
	1    3850 1450
	1    0    0    -1  
$EndComp
Connection ~ 3850 1450
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue J2
U 1 1 604E175B
P 6150 1450
F 0 "J2" H 6092 1225 50  0001 C CNN
F 1 "+29.6" H 6050 1600 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 6150 1450 50  0001 C CNN
F 3 "~" H 6150 1450 50  0001 C CNN
	1    6150 1450
	1    0    0    -1  
$EndComp
$Comp
L Switch:SW_DPDT_x2 SW1
U 1 1 604E38DC
P 6050 3150
F 0 "SW1" H 6050 3435 50  0000 C CNN
F 1 "power" H 6050 3344 50  0000 C CNN
F 2 "My-library:MySwithc" H 6050 3150 50  0001 C CNN
F 3 "" H 6050 3150 50  0001 C CNN
	1    6050 3150
	1    0    0    -1  
$EndComp
$Comp
L power:+BATT #PWR08
U 1 1 604F0C6D
P 6350 3250
F 0 "#PWR08" H 6350 3100 50  0001 C CNN
F 1 "+BATT" H 6450 3400 50  0000 C CNN
F 2 "" H 6350 3250 50  0001 C CNN
F 3 "" H 6350 3250 50  0001 C CNN
	1    6350 3250
	1    0    0    -1  
$EndComp
Wire Wire Line
	6350 3250 6250 3250
Wire Wire Line
	8200 3750 8100 3750
Connection ~ 8100 3750
Wire Wire Line
	8100 3750 7950 3750
Wire Wire Line
	7950 3950 7950 3750
Wire Wire Line
	6850 3400 7100 3400
Connection ~ 7300 3400
Wire Wire Line
	7300 3400 7300 3800
Connection ~ 7100 3400
Wire Wire Line
	7100 3400 7300 3400
Wire Wire Line
	7300 3400 7500 3400
Wire Wire Line
	6850 3700 6850 3750
Wire Wire Line
	6850 3750 7100 3750
Wire Wire Line
	7100 3700 7100 3750
Connection ~ 7100 3750
Wire Wire Line
	7500 3700 7500 3750
Wire Wire Line
	7550 3750 7500 3750
Connection ~ 7500 3750
Wire Wire Line
	7500 3750 7500 3800
Wire Wire Line
	7850 3750 7950 3750
Connection ~ 7950 3750
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue J7
U 1 1 60622710
P 5200 3700
F 0 "J7" H 5142 3475 50  0001 C CNN
F 1 "+BAT" V 5250 3700 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 5200 3700 50  0001 C CNN
F 3 "~" H 5200 3700 50  0001 C CNN
	1    5200 3700
	0    1    1    0   
$EndComp
Connection ~ 5200 3600
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue J8
U 1 1 606AB650
P 5250 5050
F 0 "J8" H 5192 4825 50  0001 C CNN
F 1 "SiPM-" V 5250 5200 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 5250 5050 50  0001 C CNN
F 3 "~" H 5250 5050 50  0001 C CNN
	1    5250 5050
	0    -1   -1   0   
$EndComp
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue J9
U 1 1 6072E770
P 6750 5450
F 0 "J9" H 6692 5225 50  0001 C CNN
F 1 "BT Status" H 6950 5450 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 6750 5450 50  0001 C CNN
F 3 "~" H 6750 5450 50  0001 C CNN
	1    6750 5450
	-1   0    0    1   
$EndComp
$Comp
L Device:LED D7
U 1 1 60732999
P 8250 4550
F 0 "D7" V 8300 4400 50  0000 R CNN
F 1 "LED" V 8200 4400 50  0000 R CNN
F 2 "LED_SMD:LED_0805_2012Metric" H 8250 4550 50  0001 C CNN
F 3 "~" H 8250 4550 50  0001 C CNN
	1    8250 4550
	0    -1   -1   0   
$EndComp
$Comp
L power:GND #PWR016
U 1 1 6073376C
P 8250 4700
F 0 "#PWR016" H 8250 4450 50  0001 C CNN
F 1 "GND" H 8255 4527 50  0001 C CNN
F 2 "" H 8250 4700 50  0001 C CNN
F 3 "" H 8250 4700 50  0001 C CNN
	1    8250 4700
	1    0    0    -1  
$EndComp
$Comp
L Device:R R14
U 1 1 60734691
P 8100 4400
F 0 "R14" V 7900 4600 50  0000 C CNN
F 1 "1k" V 8000 4600 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 8030 4400 50  0001 C CNN
F 3 "~" H 8100 4400 50  0001 C CNN
	1    8100 4400
	0    1    1    0   
$EndComp
Wire Wire Line
	4200 2900 4800 2900
Wire Wire Line
	3800 4050 4300 4050
Wire Wire Line
	5850 3750 5850 3150
Wire Wire Line
	5850 2900 5750 2900
Wire Wire Line
	5600 4050 5850 4050
Connection ~ 5850 3150
Wire Wire Line
	5850 3150 5850 2900
Wire Wire Line
	5800 3150 5850 3150
Wire Wire Line
	5600 3450 5600 3600
Wire Wire Line
	5850 2900 6250 2900
Wire Wire Line
	6250 2900 6250 3050
Connection ~ 5850 2900
Connection ~ 4850 2150
Connection ~ 4850 1950
Connection ~ 4850 1450
Connection ~ 5150 2450
Wire Wire Line
	5150 2150 5150 1950
Wire Wire Line
	5150 2450 5450 2450
Connection ~ 5150 2150
Connection ~ 5150 1950
Wire Wire Line
	5150 1950 5150 1450
Connection ~ 5150 1450
Connection ~ 5450 2450
Wire Wire Line
	5450 2150 5450 1450
Wire Wire Line
	5450 2450 5750 2450
Connection ~ 5450 1450
Connection ~ 5750 2450
Wire Wire Line
	5750 2150 5750 1450
Wire Wire Line
	5750 2450 6050 2450
Connection ~ 5750 1450
Wire Wire Line
	6050 2150 6050 1450
Connection ~ 6050 1450
Connection ~ 5350 4050
Wire Wire Line
	5350 4050 5600 4050
Connection ~ 5600 4050
Wire Wire Line
	5350 3750 5350 3600
Wire Wire Line
	5350 2900 5450 2900
Wire Wire Line
	5350 2900 4800 2900
Connection ~ 5350 2900
Connection ~ 4800 2900
Wire Wire Line
	5200 3150 5400 3150
Wire Wire Line
	5600 3600 5350 3600
Connection ~ 5600 3600
Wire Wire Line
	5600 3600 5600 3750
Connection ~ 5350 3600
Wire Wire Line
	5350 3600 5350 2900
$Comp
L SC-STM32L412K8-rescue:LMV7239M5-My_Library-SC-STM32L412K8-rescue U4
U 1 1 6056F677
P 6200 5050
F 0 "U4" H 6050 5800 50  0000 L CNN
F 1 "LMV7239M5" H 5900 5700 50  0000 L CNN
F 2 "Package_TO_SOT_SMD:SOT-23-5" H 6200 5050 50  0001 C CNN
F 3 "" H 6200 5050 50  0001 C CNN
	1    6200 5050
	1    0    0    -1  
$EndComp
$Comp
L Regulator_Linear:MCP1700-3002E_SOT23 U5
U 1 1 6038EAE6
P 7300 2800
F 0 "U5" H 7300 3042 50  0000 C CNN
F 1 "MCP1700-3002E" H 7350 2950 50  0000 C CNN
F 2 "Package_TO_SOT_SMD:SOT-23" H 7300 3025 50  0001 C CNN
F 3 "http://ww1.microchip.com/downloads/en/DeviceDoc/20001826C.pdf" H 7300 2800 50  0001 C CNN
	1    7300 2800
	1    0    0    -1  
$EndComp
Wire Wire Line
	7600 2800 7650 2800
Wire Wire Line
	7000 2800 6950 2800
Wire Wire Line
	3900 3500 3800 3500
Connection ~ 3800 3500
Connection ~ 6950 2800
Wire Wire Line
	6950 3100 7300 3100
Connection ~ 7650 2800
Connection ~ 7500 3400
Wire Wire Line
	7950 2800 7950 3400
Wire Wire Line
	7650 2800 7950 2800
Wire Wire Line
	7500 3400 7950 3400
Connection ~ 5550 4850
Wire Wire Line
	5550 4550 5550 4500
Wire Wire Line
	5550 4500 6150 4500
Wire Wire Line
	6150 5300 6150 5500
Wire Wire Line
	6150 5500 5850 5500
Connection ~ 6150 5500
Connection ~ 5550 5500
Wire Wire Line
	5550 5500 5250 5500
Connection ~ 5850 5500
Wire Wire Line
	5850 5500 5550 5500
Wire Wire Line
	5150 5150 5250 5150
Wire Wire Line
	5250 5150 5250 5200
Connection ~ 5250 5150
Wire Wire Line
	5550 5200 5550 5150
Wire Wire Line
	5550 5150 5950 5150
Connection ~ 5550 5150
Wire Wire Line
	5850 5200 5850 4950
Connection ~ 5850 4950
Wire Wire Line
	5200 4900 4750 4900
Wire Wire Line
	5150 4500 5550 4500
Connection ~ 5550 4500
Wire Wire Line
	4250 5500 5250 5500
Connection ~ 5250 5500
Wire Wire Line
	6550 5050 6850 5050
Wire Wire Line
	6300 4500 6150 4500
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue J?
U 1 1 604B8416
P 6750 5150
F 0 "J?" H 6692 4925 50  0001 C CNN
F 1 "PWM" H 6900 5150 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 6750 5150 50  0001 C CNN
F 3 "~" H 6750 5150 50  0001 C CNN
	1    6750 5150
	-1   0    0    1   
$EndComp
$EndSCHEMATC
