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
L SC-STM32L412K8-rescue:STM32L412K8-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue D6
U 1 1 60361EE9
P 7350 4450
F 0 "D6" H 7350 4500 50  0000 C CNN
F 1 "STM32L412K8" V 7350 4050 50  0000 C CNN
F 2 "Package_QFP:LQFP-32_7x7mm_P0.8mm" H 7500 5350 50  0001 C CNN
F 3 "" H 7350 4450 50  0001 C CNN
	1    7350 4450
	1    0    0    -1  
$EndComp
$Comp
L Device:R R3
U 1 1 60363E96
P 5000 2000
F 0 "R3" H 4800 2050 50  0000 L CNN
F 1 "4.7k" H 4750 1950 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 4930 2000 50  0001 C CNN
F 3 "~" H 5000 2000 50  0001 C CNN
	1    5000 2000
	1    0    0    -1  
$EndComp
$Comp
L Device:C C5
U 1 1 60364259
P 5200 1600
F 0 "C5" V 5050 1450 50  0000 C CNN
F 1 "1.5pF" V 5050 1650 50  0000 C CNN
F 2 "Capacitor_SMD:C_0603_1608Metric" H 5238 1450 50  0001 C CNN
F 3 "~" H 5200 1600 50  0001 C CNN
	1    5200 1600
	0    1    1    0   
$EndComp
$Comp
L Device:L L2
U 1 1 603653A2
P 4600 1300
F 0 "L2" V 4700 1300 50  0000 C CNN
F 1 "L2 LQH31MN220J03L" V 5300 1350 50  0000 C CNN
F 2 "Inductor_SMD:L_1210_3225Metric" H 4600 1300 50  0001 C CNN
F 3 "~" H 4600 1300 50  0001 C CNN
	1    4600 1300
	0    -1   -1   0   
$EndComp
$Comp
L power:GND #PWR013
U 1 1 6036595A
P 6700 3100
F 0 "#PWR013" H 6700 2850 50  0001 C CNN
F 1 "GND" H 6705 2927 50  0001 C CNN
F 2 "" H 6700 3100 50  0001 C CNN
F 3 "" H 6700 3100 50  0001 C CNN
	1    6700 3100
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR012
U 1 1 60366E3E
P 7300 5600
F 0 "#PWR012" H 7300 5350 50  0001 C CNN
F 1 "GND" H 7305 5427 50  0001 C CNN
F 2 "" H 7300 5600 50  0001 C CNN
F 3 "" H 7300 5600 50  0001 C CNN
	1    7300 5600
	1    0    0    -1  
$EndComp
Wire Wire Line
	7450 5600 7300 5600
$Comp
L Device:L L4
U 1 1 6037B2E7
P 7500 3550
F 0 "L4" H 7350 3550 50  0000 L CNN
F 1 "L4 MLZ2012M470WT000" H 6800 6500 50  0000 L CNN
F 2 "Inductor_SMD:L_0805_2012Metric" H 7500 3550 50  0001 C CNN
F 3 "~" H 7500 3550 50  0001 C CNN
	1    7500 3550
	1    0    0    -1  
$EndComp
$Comp
L Device:C C18
U 1 1 6037FBDB
P 7700 3750
F 0 "C18" V 7450 3750 50  0000 C CNN
F 1 "22uF" V 7550 3750 50  0000 C CNN
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
Wire Wire Line
	7950 4050 8100 4050
$Comp
L Device:C C17
U 1 1 6039B0E7
P 7150 3550
F 0 "C17" H 7050 3900 50  0000 L CNN
F 1 "22uF" H 7050 3800 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 7188 3400 50  0001 C CNN
F 3 "~" H 7150 3550 50  0001 C CNN
	1    7150 3550
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR010
U 1 1 6039D537
P 7150 3700
F 0 "#PWR010" H 7150 3450 50  0001 C CNN
F 1 "GND" H 7155 3527 50  0001 C CNN
F 2 "" H 7150 3700 50  0001 C CNN
F 3 "" H 7150 3700 50  0001 C CNN
	1    7150 3700
	1    0    0    -1  
$EndComp
Text GLabel 7950 4300 2    50   Input ~ 0
AIn
$Comp
L Device:C C16
U 1 1 603AB7C1
P 6950 3550
F 0 "C16" H 6800 3900 50  0000 L CNN
F 1 "22uF" H 6800 3800 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 6988 3400 50  0001 C CNN
F 3 "~" H 6950 3550 50  0001 C CNN
	1    6950 3550
	1    0    0    -1  
$EndComp
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue J5
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
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue J6
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
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue J3
U 1 1 603B523F
P 6750 4850
F 0 "J3" H 6692 4625 50  0001 C CNN
F 1 "TxD (20)" H 6900 4850 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 6750 4850 50  0001 C CNN
F 3 "~" H 6750 4850 50  0001 C CNN
	1    6750 4850
	-1   0    0    1   
$EndComp
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue J4
U 1 1 603B58EC
P 6750 4950
F 0 "J4" H 6692 4725 50  0001 C CNN
F 1 "RxD (19)" H 6900 4950 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 6750 4950 50  0001 C CNN
F 3 "~" H 6750 4950 50  0001 C CNN
	1    6750 4950
	-1   0    0    1   
$EndComp
$Comp
L Battery_Management:LTC4054ES5-4.2 U3
U 1 1 603B9C75
P 5000 3450
F 0 "U3" H 5050 2950 50  0000 L CNN
F 1 "MCP73831" H 5000 2850 50  0000 L CNN
F 2 "Package_TO_SOT_SMD:TSOT-23-5" H 5000 2950 50  0001 C CNN
F 3 "http://cds.linear.com/docs/en/datasheet/405442xf.pdf" H 5000 3350 50  0001 C CNN
	1    5000 3450
	1    0    0    -1  
$EndComp
$Comp
L Device:R R4
U 1 1 603BA35A
P 4800 3000
F 0 "R4" V 4700 3300 50  0000 C CNN
F 1 "1k" V 4800 3300 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 4730 3000 50  0001 C CNN
F 3 "~" H 4800 3000 50  0001 C CNN
	1    4800 3000
	0    1    1    0   
$EndComp
$Comp
L Device:LED D1
U 1 1 603BB323
P 4500 3250
F 0 "D1" V 4550 3150 50  0000 R CNN
F 1 "LED" V 4450 3200 50  0000 R CNN
F 2 "LED_SMD:LED_0805_2012Metric" H 4500 3250 50  0001 C CNN
F 3 "~" H 4500 3250 50  0001 C CNN
	1    4500 3250
	0    -1   -1   0   
$EndComp
$Comp
L Device:R R1
U 1 1 603BC118
P 4500 3750
F 0 "R1" H 4570 3796 50  0000 L CNN
F 1 "20k" H 4570 3705 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 4430 3750 50  0001 C CNN
F 3 "~" H 4500 3750 50  0001 C CNN
	1    4500 3750
	1    0    0    -1  
$EndComp
$Comp
L Device:R R6
U 1 1 603BCA72
P 4800 2850
F 0 "R6" V 4700 2750 50  0000 L CNN
F 1 "1" V 4700 2900 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 4730 2850 50  0001 C CNN
F 3 "~" H 4800 2850 50  0001 C CNN
	1    4800 2850
	0    1    1    0   
$EndComp
$Comp
L Device:C C13
U 1 1 603BFF56
P 5550 3750
F 0 "C13" H 5350 3850 50  0000 L CNN
F 1 "22uF" H 5300 3650 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 5588 3600 50  0001 C CNN
F 3 "~" H 5550 3750 50  0001 C CNN
	1    5550 3750
	1    0    0    -1  
$EndComp
$Comp
L Connector:USB_B_Micro J1
U 1 1 603CD9A3
P 4100 3050
F 0 "J1" H 4100 3500 50  0000 C CNN
F 1 "usb" H 4100 3400 50  0000 C CNN
F 2 "Connector_USB:USB_Micro-B_Molex_47346-0001" H 4250 3000 50  0001 C CNN
F 3 "~" H 4250 3000 50  0001 C CNN
	1    4100 3050
	1    0    0    -1  
$EndComp
Connection ~ 4500 3900
Wire Wire Line
	4500 3900 5000 3900
Wire Wire Line
	5000 3850 5000 3900
Wire Wire Line
	4500 3400 4500 3450
Wire Wire Line
	4500 3450 4600 3450
Wire Wire Line
	4600 3550 4500 3550
Wire Wire Line
	4500 3550 4500 3600
$Comp
L power:GND #PWR03
U 1 1 603F022D
P 4000 3900
F 0 "#PWR03" H 4000 3650 50  0001 C CNN
F 1 "GND" V 4005 3772 50  0001 R CNN
F 2 "" H 4000 3900 50  0001 C CNN
F 3 "" H 4000 3900 50  0001 C CNN
	1    4000 3900
	1    0    0    -1  
$EndComp
$Comp
L Regulator_Switching:LM2733YMF U2
U 1 1 604040C9
P 4600 1700
F 0 "U2" H 4600 2067 50  0000 C CNN
F 1 "AP3015KTR" H 4600 1976 50  0000 C CNN
F 2 "Package_TO_SOT_SMD:SOT-23-5" H 4650 1450 50  0001 L CIN
F 3 "http://www.ti.com/lit/ds/symlink/lm2733.pdf" H 4600 1800 50  0001 C CNN
	1    4600 1700
	1    0    0    -1  
$EndComp
$Comp
L Device:D D2
U 1 1 6040F684
P 5150 1300
F 0 "D2" H 5150 1100 50  0000 C CNN
F 1 "MBR0540" H 5150 1200 50  0000 C CNN
F 2 "Diode_SMD:D_SOD-123" H 5150 1300 50  0001 C CNN
F 3 "~" H 5150 1300 50  0001 C CNN
	1    5150 1300
	-1   0    0    1   
$EndComp
$Comp
L Device:R R11
U 1 1 60429F72
P 5950 1300
F 0 "R11" V 6050 1300 50  0000 C CNN
F 1 "100" V 6150 1300 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 5880 1300 50  0001 C CNN
F 3 "~" H 5950 1300 50  0001 C CNN
	1    5950 1300
	0    1    1    0   
$EndComp
$Comp
L Device:C C7
U 1 1 60435680
P 5400 2350
F 0 "C7" H 5350 2250 50  0000 R CNN
F 1 "C3216X7R1H106K160AC" H 6050 2600 50  0001 R CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 5438 2200 50  0001 C CNN
F 3 "~" H 5400 2350 50  0001 C CNN
	1    5400 2350
	-1   0    0    1   
$EndComp
$Comp
L Device:C C3
U 1 1 604360AF
P 4300 2350
F 0 "C3" H 4200 2300 50  0000 R CNN
F 1 "22uF" H 4200 2400 50  0000 R CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 4338 2200 50  0001 C CNN
F 3 "~" H 4300 2350 50  0001 C CNN
	1    4300 2350
	-1   0    0    1   
$EndComp
$Comp
L Device:L L1
U 1 1 60436BF5
P 4100 1300
F 0 "L1" V 4200 1300 50  0000 C CNN
F 1 "L1 LQM21FN100N" V 4800 0   50  0000 C CNN
F 2 "Inductor_SMD:L_0805_2012Metric" H 4100 1300 50  0001 C CNN
F 3 "~" H 4100 1300 50  0001 C CNN
	1    4100 1300
	0    -1   -1   0   
$EndComp
$Comp
L Device:C C1
U 1 1 60437474
P 3900 2350
F 0 "C1" H 3850 2150 50  0000 R CNN
F 1 "22uF" H 3850 2250 50  0000 R CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 3938 2200 50  0001 C CNN
F 3 "GRM21BR60J226M" H 3900 2350 50  0001 C CNN
	1    3900 2350
	-1   0    0    1   
$EndComp
$Comp
L Device:L L3
U 1 1 60437DE2
P 5600 1300
F 0 "L3" V 5700 1300 50  0000 C CNN
F 1 "L3 MLZ2012M470WT000" V 6300 650 50  0000 C CNN
F 2 "Inductor_SMD:L_0805_2012Metric" H 5600 1300 50  0001 C CNN
F 3 "MLZ2012M470WT000" H 5600 1300 50  0001 C CNN
	1    5600 1300
	0    -1   -1   0   
$EndComp
$Comp
L Device:R R5
U 1 1 6043948F
P 5200 1800
F 0 "R5" V 5300 1800 50  0000 C CNN
F 1 "10M" V 5400 1800 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 5130 1800 50  0001 C CNN
F 3 "~" H 5200 1800 50  0001 C CNN
	1    5200 1800
	0    1    1    0   
$EndComp
$Comp
L Device:C C8
U 1 1 604432B8
P 5800 2350
F 0 "C8" H 5750 2250 50  0000 R CNN
F 1 "C3216X7R1H106K160AC" H 6950 2800 50  0001 R CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 5838 2200 50  0001 C CNN
F 3 "~" H 5800 2350 50  0001 C CNN
	1    5800 2350
	-1   0    0    1   
$EndComp
$Comp
L Device:C C11
U 1 1 6044654C
P 6100 2350
F 0 "C11" H 6050 2250 50  0000 R CNN
F 1 "C3216X7R1H106K160AC" H 7550 2900 50  0001 R CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 6138 2200 50  0001 C CNN
F 3 "~" H 6100 2350 50  0001 C CNN
	1    6100 2350
	-1   0    0    1   
$EndComp
$Comp
L Device:R R13
U 1 1 60446D66
P 6250 1300
F 0 "R13" V 6350 1300 50  0000 C CNN
F 1 "1k" V 6450 1300 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 6180 1300 50  0001 C CNN
F 3 "~" H 6250 1300 50  0001 C CNN
	1    6250 1300
	0    1    1    0   
$EndComp
$Comp
L Device:C C14
U 1 1 604473D0
P 6400 2350
F 0 "C14" H 6350 2250 50  0000 R CNN
F 1 "C3216X7R1H106K160AC" H 8150 3000 50  0001 R CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 6438 2200 50  0001 C CNN
F 3 "~" H 6400 2350 50  0001 C CNN
	1    6400 2350
	-1   0    0    1   
$EndComp
Connection ~ 4600 2500
Wire Wire Line
	4300 1300 4300 1600
Connection ~ 4300 1600
Wire Wire Line
	4300 1600 4300 1800
Connection ~ 4300 2500
Wire Wire Line
	4300 2500 4600 2500
Connection ~ 4300 1800
Wire Wire Line
	3900 2500 4300 2500
Wire Wire Line
	3900 1300 3950 1300
Wire Wire Line
	4250 1300 4300 1300
Connection ~ 4300 1300
Wire Wire Line
	4300 1300 4450 1300
Wire Wire Line
	4750 1300 4900 1300
Wire Wire Line
	4900 1600 4900 1300
$Comp
L power:GND #PWR02
U 1 1 60484C98
P 3900 2500
F 0 "#PWR02" H 3900 2250 50  0001 C CNN
F 1 "GND" V 3905 2372 50  0001 R CNN
F 2 "" H 3900 2500 50  0001 C CNN
F 3 "" H 3900 2500 50  0001 C CNN
	1    3900 2500
	1    0    0    -1  
$EndComp
Connection ~ 3900 2500
$Comp
L Device:C C19
U 1 1 60487A06
P 7050 2950
F 0 "C19" H 7400 3100 50  0000 L CNN
F 1 "22uF" H 7400 3000 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 7088 2800 50  0001 C CNN
F 3 "~" H 7050 2950 50  0001 C CNN
	1    7050 2950
	1    0    0    -1  
$EndComp
Wire Wire Line
	6700 3100 7050 3100
Connection ~ 6700 3100
$Comp
L Device:C C15
U 1 1 604A052D
P 6350 2950
F 0 "C15" H 6250 2750 50  0000 L CNN
F 1 "22uF" H 6250 2650 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 6388 2800 50  0001 C CNN
F 3 "~" H 6350 2950 50  0001 C CNN
	1    6350 2950
	1    0    0    -1  
$EndComp
$Comp
L power:+BATT #PWR01
U 1 1 604E0AC3
P 3900 1300
F 0 "#PWR01" H 3900 1150 50  0001 C CNN
F 1 "+BATT" H 3650 1300 50  0000 C CNN
F 2 "" H 3900 1300 50  0001 C CNN
F 3 "" H 3900 1300 50  0001 C CNN
	1    3900 1300
	1    0    0    -1  
$EndComp
Connection ~ 3900 1300
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue J2
U 1 1 604E175B
P 6500 1300
F 0 "J2" H 6442 1075 50  0001 C CNN
F 1 "+29.6 SiPM" H 6350 1400 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 6500 1300 50  0001 C CNN
F 3 "~" H 6500 1300 50  0001 C CNN
	1    6500 1300
	1    0    0    -1  
$EndComp
$Comp
L Switch:SW_DPDT_x2 SW1
U 1 1 604E38DC
P 5750 3450
F 0 "SW1" H 5750 3650 50  0000 C CNN
F 1 "power" H 5750 3750 50  0000 C CNN
F 2 "My-library:MySwithc" H 5750 3450 50  0001 C CNN
F 3 "" H 5750 3450 50  0001 C CNN
	1    5750 3450
	1    0    0    -1  
$EndComp
Wire Wire Line
	8200 3750 8100 3750
Connection ~ 8100 3750
Wire Wire Line
	8100 3750 7950 3750
Wire Wire Line
	7950 3950 7950 3750
Connection ~ 7300 3400
Wire Wire Line
	7300 3400 7300 3750
Wire Wire Line
	7300 3400 7500 3400
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
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue J7
U 1 1 60622710
P 5950 3100
F 0 "J7" H 5892 2875 50  0001 C CNN
F 1 "+BAT" V 6000 3100 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 5950 3100 50  0001 C CNN
F 3 "~" H 5950 3100 50  0001 C CNN
	1    5950 3100
	0    -1   -1   0   
$EndComp
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue J9
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
	4000 3900 4500 3900
Wire Wire Line
	5550 3450 5550 3200
Wire Wire Line
	5550 3200 5950 3200
Wire Wire Line
	5950 3200 5950 3350
Wire Wire Line
	5800 2500 6100 2500
Connection ~ 6100 2500
Wire Wire Line
	6100 2500 6400 2500
Connection ~ 6100 1300
Connection ~ 6400 1300
$Comp
L Regulator_Linear:MCP1700-3002E_SOT23 U5
U 1 1 6038EAE6
P 6700 2800
F 0 "U5" H 6650 3100 50  0000 C CNN
F 1 "MCP1700-3002E" H 6750 3000 50  0000 C CNN
F 2 "Package_TO_SOT_SMD:SOT-23" H 6700 3025 50  0001 C CNN
F 3 "http://ww1.microchip.com/downloads/en/DeviceDoc/20001826C.pdf" H 6700 2800 50  0001 C CNN
	1    6700 2800
	1    0    0    -1  
$EndComp
Wire Wire Line
	7000 2800 7050 2800
Wire Wire Line
	6400 2800 6350 2800
Wire Wire Line
	6350 3100 6700 3100
Connection ~ 7050 2800
$Comp
L Device:R R15
U 1 1 604FA546
P 4350 5350
F 0 "R15" H 4500 5300 50  0000 C CNN
F 1 "100k" H 4500 5400 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 4280 5350 50  0001 C CNN
F 3 "~" H 4350 5350 50  0001 C CNN
	1    4350 5350
	-1   0    0    1   
$EndComp
$Comp
L Device:R R16
U 1 1 604FB12B
P 4350 4500
F 0 "R16" H 4500 4450 50  0000 C CNN
F 1 "100k" H 4500 4550 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 4280 4500 50  0001 C CNN
F 3 "~" H 4350 4500 50  0001 C CNN
	1    4350 4500
	-1   0    0    1   
$EndComp
$Comp
L power:GND #PWR0101
U 1 1 604FBD4D
P 4650 5500
F 0 "#PWR0101" H 4650 5250 50  0001 C CNN
F 1 "GND" H 4655 5327 50  0001 C CNN
F 2 "" H 4650 5500 50  0001 C CNN
F 3 "" H 4650 5500 50  0001 C CNN
	1    4650 5500
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR0102
U 1 1 604FC90E
P 4650 4650
F 0 "#PWR0102" H 4650 4400 50  0001 C CNN
F 1 "GND" H 4655 4477 50  0001 C CNN
F 2 "" H 4650 4650 50  0001 C CNN
F 3 "" H 4650 4650 50  0001 C CNN
	1    4650 4650
	1    0    0    -1  
$EndComp
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue J11
U 1 1 605058F1
P 4750 4150
F 0 "J11" H 4692 3925 50  0001 C CNN
F 1 "Vibro" H 4750 4250 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 4750 4150 50  0001 C CNN
F 3 "~" H 4750 4150 50  0001 C CNN
	1    4750 4150
	1    0    0    -1  
$EndComp
Wire Wire Line
	6550 5050 6850 5050
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue J8
U 1 1 606AB650
P 5500 5000
F 0 "J8" H 5442 4775 50  0001 C CNN
F 1 "SiPM-" V 5450 5150 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 5500 5000 50  0001 C CNN
F 3 "~" H 5500 5000 50  0001 C CNN
	1    5500 5000
	0    -1   -1   0   
$EndComp
Text GLabel 5400 5150 0    50   Input ~ 0
AIn
$Comp
L Device:C C2
U 1 1 603A63DB
P 5650 4650
F 0 "C2" H 5450 4550 50  0000 L CNN
F 1 "22uF" H 5400 4450 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 5688 4500 50  0001 C CNN
F 3 "~" H 5650 4650 50  0001 C CNN
	1    5650 4650
	1    0    0    -1  
$EndComp
$Comp
L Device:R R2
U 1 1 6036DAFE
P 5500 5350
F 0 "R2" H 5600 5400 50  0000 L CNN
F 1 "2.2k" H 5600 5300 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 5430 5350 50  0001 C CNN
F 3 "~" H 5500 5350 50  0001 C CNN
	1    5500 5350
	1    0    0    -1  
$EndComp
$Comp
L Device:R R9
U 1 1 6036BC49
P 5700 5150
F 0 "R9" V 5500 5100 50  0000 L CNN
F 1 "12k" V 5600 5100 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 5630 5150 50  0001 C CNN
F 3 "~" H 5700 5150 50  0001 C CNN
	1    5700 5150
	0    1    1    0   
$EndComp
$Comp
L Device:R R8
U 1 1 6036B7B6
P 5900 5350
F 0 "R8" H 5950 5400 50  0000 L CNN
F 1 "5.1k" H 5950 5300 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 5830 5350 50  0001 C CNN
F 3 "~" H 5900 5350 50  0001 C CNN
	1    5900 5350
	1    0    0    -1  
$EndComp
$Comp
L Device:R R7
U 1 1 6036B072
P 5900 4700
F 0 "R7" H 6000 4800 50  0000 L CNN
F 1 "680k" H 5950 4700 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 5830 4700 50  0001 C CNN
F 3 "~" H 5900 4700 50  0001 C CNN
	1    5900 4700
	1    0    0    -1  
$EndComp
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue J12
U 1 1 60613CF0
P 7050 2700
F 0 "J12" H 6992 2475 50  0001 C CNN
F 1 "+3v" V 7050 2600 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 7050 2700 50  0001 C CNN
F 3 "~" H 7050 2700 50  0001 C CNN
	1    7050 2700
	0    -1   -1   0   
$EndComp
Text GLabel 4200 4350 0    50   Input ~ 0
Vibro
Text GLabel 6850 4050 0    50   Input ~ 0
Vibro
Text GLabel 4200 5200 0    50   Input ~ 0
Buzer
Text GLabel 6850 4250 0    50   Input ~ 0
Buzer
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue J13
U 1 1 6052B033
P 6350 2700
F 0 "J13" H 6292 2475 50  0001 C CNN
F 1 "+vibro" V 6400 2700 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 6350 2700 50  0001 C CNN
F 3 "~" H 6350 2700 50  0001 C CNN
	1    6350 2700
	0    -1   -1   0   
$EndComp
$Comp
L Device:R R19
U 1 1 60539C8E
P 6150 3700
F 0 "R19" H 6300 3750 50  0000 C CNN
F 1 "39k" H 6300 3650 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 6080 3700 50  0001 C CNN
F 3 "~" H 6150 3700 50  0001 C CNN
	1    6150 3700
	-1   0    0    1   
$EndComp
$Comp
L Device:R R20
U 1 1 6053A8EC
P 6150 4000
F 0 "R20" H 6300 4000 50  0000 C CNN
F 1 "100k" H 6300 4100 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 6080 4000 50  0001 C CNN
F 3 "~" H 6150 4000 50  0001 C CNN
	1    6150 4000
	-1   0    0    1   
$EndComp
Text GLabel 5950 3850 0    50   Input ~ 0
bl
Text GLabel 6850 4650 0    50   Input ~ 0
bl
Connection ~ 7150 3400
Connection ~ 7150 3700
Wire Wire Line
	7150 3400 7300 3400
Wire Wire Line
	6950 3400 7150 3400
Wire Wire Line
	6950 3700 7150 3700
Text GLabel 6850 4550 0    50   Input ~ 0
com
Text GLabel 5950 4200 0    50   Input ~ 0
com
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
L Device:C C12
U 1 1 603759D5
P 6350 4650
F 0 "C12" H 6400 4850 50  0000 L CNN
F 1 "22uF" H 6400 4750 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 6388 4500 50  0001 C CNN
F 3 "~" H 6350 4650 50  0001 C CNN
	1    6350 4650
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR07
U 1 1 60376345
P 6350 4800
F 0 "#PWR07" H 6350 4550 50  0001 C CNN
F 1 "GND" H 6355 4627 50  0001 C CNN
F 2 "" H 6350 4800 50  0001 C CNN
F 3 "" H 6350 4800 50  0001 C CNN
	1    6350 4800
	1    0    0    -1  
$EndComp
Connection ~ 6150 4500
Wire Wire Line
	6150 4500 6150 4800
$Comp
L SC-STM32L412K8-rescue:LMV7239M5-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue U4
U 1 1 6056F677
P 6200 5050
F 0 "U4" H 5950 5800 50  0000 L CNN
F 1 "TLV3201AIDBVT" H 5700 5700 50  0000 L CNN
F 2 "Package_TO_SOT_SMD:SOT-23-5" H 6200 5050 50  0001 C CNN
F 3 "" H 6200 5050 50  0001 C CNN
	1    6200 5050
	1    0    0    -1  
$EndComp
Wire Wire Line
	6150 5300 6150 5500
Wire Wire Line
	6150 5500 5900 5500
Connection ~ 6150 5500
Connection ~ 5800 2500
$Comp
L Device:C C6
U 1 1 607E83A9
P 5600 2350
F 0 "C6" H 5550 2250 50  0000 R CNN
F 1 "C3216X7R1H106K160AC" H 6550 2700 50  0001 R CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 5638 2200 50  0001 C CNN
F 3 "~" H 5600 2350 50  0001 C CNN
	1    5600 2350
	-1   0    0    1   
$EndComp
Wire Wire Line
	5600 2500 5800 2500
Connection ~ 5900 5500
Wire Wire Line
	5900 4850 5900 4950
Wire Wire Line
	5900 4550 5900 4500
Wire Wire Line
	5900 4500 6150 4500
Wire Wire Line
	5950 4950 5900 4950
Connection ~ 5900 4950
Wire Wire Line
	5900 4950 5900 5200
Wire Wire Line
	5850 5150 5950 5150
Wire Wire Line
	5500 5500 5900 5500
Wire Wire Line
	5400 5150 5500 5150
Wire Wire Line
	5500 5200 5500 5150
Connection ~ 5500 5150
Wire Wire Line
	5500 5150 5550 5150
Wire Wire Line
	5500 5150 5500 5100
Text Notes 4150 750  0    50   ~ 0
C6, C7, C8, C11, C14 GRM21BC8YA106KE11L
Wire Wire Line
	5600 2500 5400 2500
Connection ~ 5600 2500
Connection ~ 5400 2500
Wire Wire Line
	5750 1300 5800 1300
Connection ~ 5800 1300
Wire Wire Line
	5450 1300 5400 1300
Wire Wire Line
	7400 3800 7400 3750
Wire Wire Line
	7400 3750 7300 3750
Connection ~ 7300 3750
Wire Wire Line
	7300 3750 7300 3800
Wire Wire Line
	5000 3900 5550 3900
Connection ~ 5000 3900
Connection ~ 4000 3900
Wire Wire Line
	4100 3650 4000 3650
Connection ~ 4000 3650
Wire Wire Line
	4000 3650 4000 3900
$Comp
L power:GND #PWR0104
U 1 1 607D18E2
P 5650 4800
F 0 "#PWR0104" H 5650 4550 50  0001 C CNN
F 1 "GND" H 5655 4627 50  0001 C CNN
F 2 "" H 5650 4800 50  0001 C CNN
F 3 "" H 5650 4800 50  0001 C CNN
	1    5650 4800
	1    0    0    -1  
$EndComp
Wire Wire Line
	5650 4500 5900 4500
Connection ~ 5900 4500
Connection ~ 6950 3400
Wire Wire Line
	5400 3450 5550 3450
Connection ~ 5550 3450
Wire Wire Line
	5550 3450 5550 3600
$Comp
L Device:C C4
U 1 1 6083E8FB
P 5450 3000
F 0 "C4" H 5400 2800 50  0000 R CNN
F 1 "22uF" H 5400 2900 50  0000 R CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 5488 2850 50  0001 C CNN
F 3 "~" H 5450 3000 50  0001 C CNN
	1    5450 3000
	-1   0    0    1   
$EndComp
$Comp
L power:GND #PWR0105
U 1 1 60845A3A
P 5450 3150
F 0 "#PWR0105" H 5450 2900 50  0001 C CNN
F 1 "GND" V 5455 3022 50  0001 R CNN
F 2 "" H 5450 3150 50  0001 C CNN
F 3 "" H 5450 3150 50  0001 C CNN
	1    5450 3150
	1    0    0    -1  
$EndComp
Wire Wire Line
	4400 2850 4650 2850
Wire Wire Line
	4950 2850 5000 2850
Connection ~ 5000 2850
Wire Wire Line
	5000 2850 5450 2850
Wire Wire Line
	4100 3450 4100 3650
Wire Wire Line
	4000 3450 4000 3650
Text Notes 4150 850  0    50   ~ 0
C1, C2, C3, C4, C12, C13, C15, C16, C17, C18, C19  GRM21BR60J226M
Wire Wire Line
	6350 4500 6350 3400
Wire Wire Line
	6350 3400 6950 3400
Wire Wire Line
	6150 4500 6350 4500
Connection ~ 6350 4500
Connection ~ 6350 2800
Wire Wire Line
	5950 3550 6150 3550
Wire Wire Line
	6150 2800 6350 2800
Wire Wire Line
	7050 2800 7300 2800
Wire Wire Line
	7300 2800 7300 3400
Connection ~ 5950 3200
$Comp
L power:+BATT #PWR04
U 1 1 609801DD
P 6150 2800
F 0 "#PWR04" H 6150 2650 50  0001 C CNN
F 1 "+BATT" H 6050 2950 50  0000 C CNN
F 2 "" H 6150 2800 50  0001 C CNN
F 3 "" H 6150 2800 50  0001 C CNN
	1    6150 2800
	1    0    0    -1  
$EndComp
Connection ~ 6150 2800
Connection ~ 4900 1300
Connection ~ 5400 1300
Wire Wire Line
	4900 1800 5000 1800
Connection ~ 5000 1800
$Comp
L Device:R R10
U 1 1 609706B7
P 5000 2350
F 0 "R10" H 4750 2400 50  0000 L CNN
F 1 "442k" H 4750 2300 50  0000 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 4930 2350 50  0001 C CNN
F 3 "~" H 5000 2350 50  0001 C CNN
	1    5000 2350
	1    0    0    -1  
$EndComp
Wire Wire Line
	4600 2500 5000 2500
Wire Wire Line
	5800 2200 5600 2200
$Comp
L Device:R R12
U 1 1 60985878
P 4800 5000
F 0 "R12" V 4600 5000 50  0000 C CNN
F 1 "200" V 4700 5000 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 4730 5000 50  0001 C CNN
F 3 "~" H 4800 5000 50  0001 C CNN
	1    4800 5000
	0    1    1    0   
$EndComp
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue J10
U 1 1 6098637F
P 5050 5000
F 0 "J10" H 4992 4775 50  0001 C CNN
F 1 "Buzzer" H 5050 5100 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 5050 5000 50  0001 C CNN
F 3 "~" H 5050 5000 50  0001 C CNN
	1    5050 5000
	1    0    0    -1  
$EndComp
$Comp
L Transistor_FET:2N7002 Q1
U 1 1 609A9FBA
P 4550 4350
F 0 "Q1" H 4400 4600 50  0000 L CNN
F 1 "IRLML0060RTPbF" H 3900 4500 50  0000 L CNN
F 2 "Package_TO_SOT_SMD:SOT-23" H 4750 4275 50  0001 L CIN
F 3 "https://www.fairchildsemi.com/datasheets/2N/2N7002.pdf" H 4550 4350 50  0001 L CNN
	1    4550 4350
	1    0    0    -1  
$EndComp
Wire Wire Line
	4650 4550 4650 4650
Wire Wire Line
	4350 4650 4650 4650
Connection ~ 4650 4650
Wire Wire Line
	4200 4350 4350 4350
Connection ~ 4350 4350
$Comp
L Transistor_FET:2N7002 Q2
U 1 1 609E193B
P 4550 5200
F 0 "Q2" H 4400 5450 50  0000 L CNN
F 1 "IRLML0060RTPbF" H 3900 5350 50  0000 L CNN
F 2 "Package_TO_SOT_SMD:SOT-23" H 4750 5125 50  0001 L CIN
F 3 "https://www.fairchildsemi.com/datasheets/2N/2N7002.pdf" H 4550 5200 50  0001 L CNN
	1    4550 5200
	1    0    0    -1  
$EndComp
Wire Wire Line
	4200 5200 4350 5200
Connection ~ 4350 5200
Wire Wire Line
	4350 5500 4650 5500
Wire Wire Line
	4650 5500 4650 5400
Connection ~ 4650 5500
Wire Wire Line
	5950 3850 6150 3850
Connection ~ 6150 3850
Connection ~ 6150 3550
Wire Wire Line
	6150 2800 6150 3550
Wire Wire Line
	6400 1300 6400 2200
Wire Wire Line
	6100 1300 6100 2200
Wire Wire Line
	5800 1300 5800 2200
Connection ~ 5800 2200
Wire Wire Line
	5000 1800 5000 1850
Wire Wire Line
	5000 2150 5000 2200
Wire Wire Line
	4600 2000 4600 2500
Wire Wire Line
	4300 1800 4300 2200
Wire Wire Line
	3900 1300 3900 2200
Wire Wire Line
	5950 4200 6150 4200
Wire Wire Line
	6150 4200 6150 4150
Wire Wire Line
	5000 2500 5400 2500
Wire Wire Line
	5400 1300 5400 1600
Connection ~ 5000 2500
Wire Wire Line
	5000 1600 5050 1600
Wire Wire Line
	5000 1600 5000 1800
Wire Wire Line
	5350 1600 5400 1600
Connection ~ 5400 1600
Wire Wire Line
	5400 1600 5400 1800
Wire Wire Line
	5350 1800 5400 1800
Connection ~ 5400 1800
Wire Wire Line
	5400 1800 5400 2200
Wire Wire Line
	5050 1800 5000 1800
Wire Wire Line
	5300 1300 5400 1300
Wire Wire Line
	4900 1300 5000 1300
Wire Wire Line
	5000 2850 5000 3000
Wire Wire Line
	4950 3000 5000 3000
Connection ~ 5000 3000
Wire Wire Line
	5000 3000 5000 3150
Wire Wire Line
	4500 3100 4500 3000
Wire Wire Line
	4500 3000 4650 3000
$Comp
L SC-STM32L412K8-rescue:LTC1661CMS8-My_Library D3
U 1 1 60B43F1F
P 7100 1750
F 0 "D3" H 7100 2165 50  0000 C CNN
F 1 "LTC1662CMS8" H 7100 2074 50  0000 C CNN
F 2 "Package_SO:MSOP-8_3x3mm_P0.65mm" H 7100 2150 50  0001 C CNN
F 3 "" H 7100 1600 50  0001 C CNN
	1    7100 1750
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR0103
U 1 1 60B4B638
P 7500 1700
F 0 "#PWR0103" H 7500 1450 50  0001 C CNN
F 1 "GND" H 7505 1527 50  0001 C CNN
F 2 "" H 7500 1700 50  0001 C CNN
F 3 "" H 7500 1700 50  0001 C CNN
	1    7500 1700
	0    -1   -1   0   
$EndComp
$Comp
L power:+3.3V #PWR0106
U 1 1 60B51D5A
P 7300 2800
F 0 "#PWR0106" H 7300 2650 50  0001 C CNN
F 1 "+3.3V" H 7315 2973 50  0000 C CNN
F 2 "" H 7300 2800 50  0001 C CNN
F 3 "" H 7300 2800 50  0001 C CNN
	1    7300 2800
	1    0    0    -1  
$EndComp
Connection ~ 7300 2800
$Comp
L power:+3.3V #PWR0107
U 1 1 60B52F4B
P 7650 1800
F 0 "#PWR0107" H 7650 1650 50  0001 C CNN
F 1 "+3.3V" V 7550 1850 50  0000 L CNN
F 2 "" H 7650 1800 50  0001 C CNN
F 3 "" H 7650 1800 50  0001 C CNN
	1    7650 1800
	0    1    1    0   
$EndComp
Text GLabel 4900 2200 0    50   Input ~ 0
Adjust
$Comp
L Device:R R17
U 1 1 60B5AD62
P 7850 1900
F 0 "R17" V 7750 1900 50  0000 C CNN
F 1 "10M" V 7650 1900 50  0000 C CNN
F 2 "Resistor_SMD:R_0603_1608Metric" V 7780 1900 50  0001 C CNN
F 3 "~" H 7850 1900 50  0001 C CNN
	1    7850 1900
	0    -1   -1   0   
$EndComp
Text GLabel 8000 1900 2    50   Input ~ 0
Adjust
Wire Wire Line
	4900 2200 5000 2200
Connection ~ 5000 2200
Wire Wire Line
	7500 1800 7600 1800
Wire Wire Line
	6700 1900 6700 2150
Wire Wire Line
	6700 2150 7600 2150
Wire Wire Line
	7600 2150 7600 1800
Connection ~ 7600 1800
Wire Wire Line
	7600 1800 7650 1800
Wire Wire Line
	7700 1900 7500 1900
Text GLabel 6700 1800 0    50   Input ~ 0
DI
Text GLabel 6850 4750 0    50   Input ~ 0
DI
Text GLabel 6700 1700 0    50   Input ~ 0
SC
Text GLabel 7950 4500 2    50   Input ~ 0
SC
Text GLabel 6700 1600 0    50   Input ~ 0
CS
Text GLabel 6850 5150 0    50   Input ~ 0
CS
Connection ~ 7300 5600
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue J14
U 1 1 61532825
P 5000 4300
F 0 "J14" H 4942 4075 50  0001 C CNN
F 1 "gndBT" V 5050 4350 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 5000 4300 50  0001 C CNN
F 3 "~" H 5000 4300 50  0001 C CNN
	1    5000 4300
	0    -1   -1   0   
$EndComp
$Comp
L SC-STM32L412K8-rescue:Conn-My_Library-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue-SC-STM32L412K8-rescue J15
U 1 1 61533860
P 5150 4300
F 0 "J15" H 5092 4075 50  0001 C CNN
F 1 "-BAT" V 5200 4200 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 5150 4300 50  0001 C CNN
F 3 "~" H 5150 4300 50  0001 C CNN
	1    5150 4300
	0    -1   -1   0   
$EndComp
$Comp
L power:GND #PWR0108
U 1 1 61534211
P 5000 4400
F 0 "#PWR0108" H 5000 4150 50  0001 C CNN
F 1 "GND" H 5005 4227 50  0001 C CNN
F 2 "" H 5000 4400 50  0001 C CNN
F 3 "" H 5000 4400 50  0001 C CNN
	1    5000 4400
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR0109
U 1 1 61534DF7
P 5150 4400
F 0 "#PWR0109" H 5150 4150 50  0001 C CNN
F 1 "GND" H 5155 4227 50  0001 C CNN
F 2 "" H 5150 4400 50  0001 C CNN
F 3 "" H 5150 4400 50  0001 C CNN
	1    5150 4400
	1    0    0    -1  
$EndComp
$EndSCHEMATC
