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
L Regulator_Switching:LM2733YMF U2
U 1 1 5F3A83E4
P 5400 1700
F 0 "U2" H 5250 2050 50  0000 C CNN
F 1 "LM2733YMF" H 5400 1950 50  0000 C CNN
F 2 "Package_TO_SOT_SMD:SOT-23-5" H 5450 1450 50  0001 L CIN
F 3 "http://www.ti.com/lit/ds/symlink/lm2733.pdf" H 5400 1800 50  0001 C CNN
	1    5400 1700
	1    0    0    -1  
$EndComp
$Comp
L Device:L L3
U 1 1 5F3A846C
P 5400 1150
F 0 "L3" V 5500 1150 50  0000 C CNN
F 1 "B82422A1223K100" V 5650 1200 50  0001 C CNN
F 2 "Inductor_SMD:L_1210_3225Metric" H 5400 1150 50  0001 C CNN
F 3 "~" H 5400 1150 50  0001 C CNN
	1    5400 1150
	0    -1   -1   0   
$EndComp
$Comp
L Device:D D2
U 1 1 5F3A85A6
P 5900 1150
F 0 "D2" H 5900 950 50  0000 C CNN
F 1 "MBR0540LT" H 5900 1050 50  0000 C CNN
F 2 "Diode_SMD:D_SOD-123" H 5900 1150 50  0001 C CNN
F 3 "~" H 5900 1150 50  0001 C CNN
	1    5900 1150
	-1   0    0    1   
$EndComp
$Comp
L Device:C C9
U 1 1 5F3A8620
P 5100 2300
F 0 "C9" H 5150 2400 50  0000 L CNN
F 1 "CL21A226MQQNNNE" H 5150 2200 50  0001 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 5138 2150 50  0001 C CNN
F 3 "GRM21BR60J226M" H 5100 2300 50  0001 C CNN
	1    5100 2300
	1    0    0    -1  
$EndComp
$Comp
L Device:R R5
U 1 1 5F3A86A1
P 5900 1800
F 0 "R5" V 5700 1750 50  0000 L CNN
F 1 "300k" V 5800 1700 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 5830 1800 50  0001 C CNN
F 3 "~" H 5900 1800 50  0001 C CNN
	1    5900 1800
	0    1    1    0   
$EndComp
$Comp
L Device:R R9
U 1 1 5F3A8789
P 5700 2300
F 0 "R9" H 5550 2350 50  0000 L CNN
F 1 "13k" H 5500 2250 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 5630 2300 50  0001 C CNN
F 3 "~" H 5700 2300 50  0001 C CNN
	1    5700 2300
	1    0    0    -1  
$EndComp
$Comp
L Device:C C6
U 1 1 5F3A8843
P 5900 2000
F 0 "C6" V 5850 2050 50  0000 L CNN
F 1 "82pF" V 6050 1900 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 5938 1850 50  0001 C CNN
F 3 "~" H 5900 2000 50  0001 C CNN
	1    5900 2000
	0    1    1    0   
$EndComp
$Comp
L Device:L L4
U 1 1 5F3A889B
P 6250 1150
F 0 "L4" V 6350 1150 50  0000 C CNN
F 1 "47uH" V 6200 1150 50  0000 C CNN
F 2 "Inductor_SMD:L_0805_2012Metric" H 6250 1150 50  0001 C CNN
F 3 "~" H 6250 1150 50  0001 C CNN
	1    6250 1150
	0    -1   -1   0   
$EndComp
$Comp
L Device:R R1
U 1 1 5F3A88F9
P 6850 1150
F 0 "R1" V 6750 1150 50  0000 C CNN
F 1 "100" V 6950 1150 50  0000 C CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 6780 1150 50  0001 C CNN
F 3 "~" H 6850 1150 50  0001 C CNN
	1    6850 1150
	0    1    1    0   
$EndComp
$Comp
L Device:R R2
U 1 1 5F3A8957
P 7450 1150
F 0 "R2" V 7350 1150 50  0000 C CNN
F 1 "1k" V 7550 1150 50  0000 C CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 7380 1150 50  0001 C CNN
F 3 "~" H 7450 1150 50  0001 C CNN
	1    7450 1150
	0    1    1    0   
$EndComp
$Comp
L Device:C C1
U 1 1 5F3A89CB
P 6450 1950
F 0 "C1" H 6450 2050 50  0000 L CNN
F 1 ".1uF" H 6450 1850 50  0000 L CNN
F 2 "Capacitor_SMD:C_1206_3216Metric" H 6488 1800 50  0001 C CNN
F 3 "~" H 6450 1950 50  0001 C CNN
	1    6450 1950
	1    0    0    -1  
$EndComp
$Comp
L Device:C C2
U 1 1 5F3A8A3D
P 6650 1950
F 0 "C2" H 6650 2050 50  0000 L CNN
F 1 ".1uF" H 6650 1850 50  0000 L CNN
F 2 "Capacitor_SMD:C_1206_3216Metric" H 6688 1800 50  0001 C CNN
F 3 "~" H 6650 1950 50  0001 C CNN
	1    6650 1950
	1    0    0    -1  
$EndComp
$Comp
L Device:C C3
U 1 1 5F3A8A6B
P 7050 1950
F 0 "C3" H 7050 1850 50  0000 L CNN
F 1 "GRM32ER71H106K" H 6700 1700 50  0001 L CNN
F 2 "Capacitor_SMD:C_1210_3225Metric" H 7088 1800 50  0001 C CNN
F 3 "GRM32ER71H106K" H 7050 1950 50  0001 C CNN
	1    7050 1950
	1    0    0    -1  
$EndComp
$Comp
L Device:C C4
U 1 1 5F3A8A9D
P 7250 1950
F 0 "C4" H 7250 1850 50  0000 L CNN
F 1 "GRM32ER71H106K" H 7250 1700 50  0001 L CNN
F 2 "Capacitor_SMD:C_1210_3225Metric" H 7288 1800 50  0001 C CNN
F 3 "GRM32ER71H106K" H 7250 1950 50  0001 C CNN
	1    7250 1950
	1    0    0    -1  
$EndComp
Wire Wire Line
	5100 2450 5100 2550
Wire Wire Line
	5100 2550 5400 2550
$Comp
L Device:C C10
U 1 1 5F3ABE0A
P 6100 2300
F 0 "C10" H 6100 2400 50  0000 L CNN
F 1 "GRM32ER71H106K" H 6100 2200 50  0001 L CNN
F 2 "Capacitor_SMD:C_1210_3225Metric" H 6138 2150 50  0001 C CNN
F 3 "GRM32ER71H106K" H 6100 2300 50  0001 C CNN
	1    6100 2300
	1    0    0    -1  
$EndComp
$Comp
L Device:L L2
U 1 1 5F3BAD33
P 4950 1150
F 0 "L2" V 5050 1150 50  0000 C CNN
F 1 "10uH" V 4900 1150 50  0000 C CNN
F 2 "Inductor_SMD:L_0805_2012Metric" H 4950 1150 50  0001 C CNN
F 3 "~" H 4950 1150 50  0001 C CNN
	1    4950 1150
	0    -1   -1   0   
$EndComp
$Comp
L power:GND #PWR03
U 1 1 5F3DFD13
P 7650 2550
F 0 "#PWR03" H 7650 2300 50  0001 C CNN
F 1 "GND" H 7655 2377 50  0001 C CNN
F 2 "" H 7650 2550 50  0001 C CNN
F 3 "" H 7650 2550 50  0001 C CNN
	1    7650 2550
	1    0    0    -1  
$EndComp
$Comp
L Connector:USB_B_Micro J2
U 1 1 5F3EA0A0
P 2350 1350
F 0 "J2" H 2405 1817 50  0000 C CNN
F 1 "USB_B_Micro" H 2405 1726 50  0000 C CNN
F 2 "Connector_USB:USB_Micro-B_Molex_47346-0001" H 2500 1300 50  0001 C CNN
F 3 "~" H 2500 1300 50  0001 C CNN
	1    2350 1350
	1    0    0    -1  
$EndComp
$Comp
L Device:R R13
U 1 1 5F3FCC35
P 3000 5300
F 0 "R13" V 2800 5250 50  0000 L CNN
F 1 "4.7k" V 2900 5250 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 2930 5300 50  0001 C CNN
F 3 "~" H 3000 5300 50  0001 C CNN
	1    3000 5300
	0    1    1    0   
$EndComp
$Comp
L Device:R R14
U 1 1 5F3FCCED
P 2800 5850
F 0 "R14" H 2850 5850 50  0000 L CNN
F 1 "12k" H 2850 5750 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 2730 5850 50  0001 C CNN
F 3 "~" H 2800 5850 50  0001 C CNN
	1    2800 5850
	1    0    0    -1  
$EndComp
$Comp
L Device:R R11
U 1 1 5F3FCD59
P 2800 5150
F 0 "R11" H 2900 4950 50  0000 L CNN
F 1 "100k" H 2900 5050 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 2730 5150 50  0001 C CNN
F 3 "~" H 2800 5150 50  0001 C CNN
	1    2800 5150
	-1   0    0    1   
$EndComp
$Comp
L Device:C C16
U 1 1 5F3FCDCB
P 3150 5850
F 0 "C16" H 3150 5950 50  0000 L CNN
F 1 "10nF" H 3150 5750 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 3188 5700 50  0001 C CNN
F 3 "~" H 3150 5850 50  0001 C CNN
	1    3150 5850
	1    0    0    -1  
$EndComp
Connection ~ 5400 2550
Wire Wire Line
	5400 2550 5700 2550
$Comp
L Device:R R12
U 1 1 5F4A74AD
P 2450 5850
F 0 "R12" H 2500 5850 50  0000 L CNN
F 1 "2.2k" H 2500 5750 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 2380 5850 50  0001 C CNN
F 3 "~" H 2450 5850 50  0001 C CNN
	1    2450 5850
	1    0    0    -1  
$EndComp
$Comp
L Device:C C15
U 1 1 5F50F0DA
P 2600 5600
F 0 "C15" V 2350 5500 50  0000 L CNN
F 1 "470pF" V 2450 5500 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 2638 5450 50  0001 C CNN
F 3 "~" H 2600 5600 50  0001 C CNN
	1    2600 5600
	0    1    1    0   
$EndComp
Connection ~ 5700 2550
Wire Wire Line
	7650 2550 7650 2100
Wire Wire Line
	5700 2550 6100 2550
Wire Wire Line
	7600 1150 7650 1150
Wire Wire Line
	7300 1150 7250 1150
Wire Wire Line
	6700 1150 6650 1150
Wire Wire Line
	5700 1150 5550 1150
Wire Wire Line
	5100 2150 5100 1800
Connection ~ 5100 1800
Wire Wire Line
	5100 1800 5100 1600
Connection ~ 5700 1150
Wire Wire Line
	5700 1800 5700 2000
Wire Wire Line
	5700 1800 5750 1800
Connection ~ 5700 1800
Wire Wire Line
	5750 2000 5700 2000
Wire Wire Line
	5400 2000 5400 2550
Wire Wire Line
	6050 1800 6100 1800
Wire Wire Line
	6050 2000 6100 2000
Wire Wire Line
	6100 2000 6100 1800
Connection ~ 6100 2550
Wire Wire Line
	6100 2550 6450 2550
Connection ~ 6450 1150
Wire Wire Line
	6450 1150 6400 1150
Connection ~ 6650 1150
Wire Wire Line
	6650 1150 6450 1150
Wire Wire Line
	6450 2100 6450 2550
Connection ~ 6450 2550
Wire Wire Line
	6450 2550 6650 2550
Wire Wire Line
	6650 2100 6650 2550
Connection ~ 6650 2550
Wire Wire Line
	6650 2550 7050 2550
Connection ~ 7050 1150
Wire Wire Line
	7050 1150 7000 1150
Connection ~ 7250 1150
Wire Wire Line
	7250 1150 7050 1150
Wire Wire Line
	7050 2100 7050 2550
Connection ~ 7050 2550
Wire Wire Line
	7050 2550 7250 2550
Wire Wire Line
	7250 2100 7250 2550
Connection ~ 7250 2550
Wire Wire Line
	7250 2550 7650 2550
Wire Wire Line
	5700 1150 5750 1150
Wire Wire Line
	6050 1150 6100 1150
Connection ~ 6100 1150
Connection ~ 7650 1150
$Comp
L Device:C C14
U 1 1 5F7BAE21
P 4100 3300
F 0 "C14" V 4200 3100 50  0000 L CNN
F 1 "0.1uF" V 4300 3100 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 4138 3150 50  0001 C CNN
F 3 "~" H 4100 3300 50  0001 C CNN
	1    4100 3300
	0    1    1    0   
$EndComp
$Comp
L cs-rescue:Conn-My_Library J8
U 1 1 5F7DCD0F
P 4250 3200
F 0 "J8" V 4200 3500 50  0001 C CNN
F 1 "nRST" V 4300 3200 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 4250 3200 50  0001 C CNN
F 3 "~" H 4250 3200 50  0001 C CNN
	1    4250 3200
	0    -1   -1   0   
$EndComp
$Comp
L cs-rescue:Conn-My_Library J9
U 1 1 5F46A74E
P 2350 5600
F 0 "J9" H 2450 5550 50  0001 L CNN
F 1 "SiPM Anode(1)" H 2400 5600 50  0000 L CNN
F 2 "My-library:SMD-CONN" H 2350 5600 50  0001 C CNN
F 3 "~" H 2350 5600 50  0001 C CNN
	1    2350 5600
	-1   0    0    1   
$EndComp
$Comp
L cs-rescue:Conn-My_Library J1
U 1 1 5F4715E0
P 7900 1050
F 0 "J1" V 8050 1000 50  0001 L CNN
F 1 "SiPM Catode(3) 29.6v" V 7950 700 50  0000 L CNN
F 2 "My-library:SMD-CONN" H 7900 1050 50  0001 C CNN
F 3 "~" H 7900 1050 50  0001 C CNN
	1    7900 1050
	0    -1   -1   0   
$EndComp
$Comp
L Device:LED D3
U 1 1 5F494A92
P 6100 5650
F 0 "D3" V 6150 5800 50  0000 C CNN
F 1 "LED" V 6050 5800 50  0000 C CNN
F 2 "LED_SMD:LED_0805_2012Metric" H 6100 5650 50  0001 C CNN
F 3 "~" H 6100 5650 50  0001 C CNN
	1    6100 5650
	0    1    -1   0   
$EndComp
$Comp
L Device:R R10
U 1 1 5F494BC3
P 5950 5500
F 0 "R10" V 5850 5400 50  0000 L CNN
F 1 "1k" V 5850 5250 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 5880 5500 50  0001 C CNN
F 3 "~" H 5950 5500 50  0001 C CNN
	1    5950 5500
	0    1    -1   0   
$EndComp
$Comp
L Device:C C12
U 1 1 5F492E50
P 3300 3850
F 0 "C12" H 3300 3950 50  0000 L CNN
F 1 "CL21A226MQQNNNE" H 3300 3750 50  0001 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 3338 3700 50  0001 C CNN
F 3 "GRM21BR60J226M" H 3300 3850 50  0001 C CNN
	1    3300 3850
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR09
U 1 1 5F4B9C76
P 2900 4000
F 0 "#PWR09" H 2900 3750 50  0001 C CNN
F 1 "GND" H 2905 3827 50  0001 C CNN
F 2 "" H 2900 4000 50  0001 C CNN
F 3 "" H 2900 4000 50  0001 C CNN
	1    2900 4000
	1    0    0    -1  
$EndComp
Wire Wire Line
	3400 6050 3400 5750
Connection ~ 3400 6050
Connection ~ 7650 2550
$Comp
L Device:C C7
U 1 1 5F4CF84B
P 4150 2300
F 0 "C7" H 4200 2400 50  0000 L CNN
F 1 "CL21A226MQQNNNE" H 4200 2200 50  0001 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 4188 2150 50  0001 C CNN
F 3 "GRM21BR60J226M" H 4150 2300 50  0001 C CNN
	1    4150 2300
	1    0    0    -1  
$EndComp
$Comp
L Device:C C8
U 1 1 5F4EC9D0
P 4800 2300
F 0 "C8" H 4850 2400 50  0000 L CNN
F 1 "CL21A226MQQNNNE" H 4850 2200 50  0001 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 4838 2150 50  0001 C CNN
F 3 "GRM21BR60J226M" H 4800 2300 50  0001 C CNN
	1    4800 2300
	1    0    0    -1  
$EndComp
$Comp
L Battery_Management:LTC4054ES5-4.2 U3
U 1 1 5F52CA8C
P 3250 1900
F 0 "U3" H 2950 2150 50  0000 L CNN
F 1 "LTC4054ES5-4.2" H 2900 1450 50  0000 L CNN
F 2 "Package_TO_SOT_SMD:TSOT-23-5" H 3250 1400 50  0001 C CNN
F 3 "http://cds.linear.com/docs/en/datasheet/405442xf.pdf" H 3250 1800 50  0001 C CNN
	1    3250 1900
	1    0    0    -1  
$EndComp
$Comp
L Device:R R3
U 1 1 5F52CEB0
P 3250 1400
F 0 "R3" V 3150 1350 50  0000 L CNN
F 1 "1R" V 3350 1400 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 3180 1400 50  0001 C CNN
F 3 "~" H 3250 1400 50  0001 C CNN
	1    3250 1400
	-1   0    0    1   
$EndComp
$Comp
L Device:D D1
U 1 1 5F52D2A3
P 3900 1150
F 0 "D1" H 3950 950 50  0000 C CNN
F 1 "MBR0520LT" H 3950 1050 50  0000 C CNN
F 2 "Diode_SMD:D_SOD-123" H 3900 1150 50  0001 C CNN
F 3 "~" H 3900 1150 50  0001 C CNN
	1    3900 1150
	-1   0    0    1   
$EndComp
$Comp
L Device:R R7
U 1 1 5F54721B
P 3950 2300
F 0 "R7" H 3800 2350 50  0000 L CNN
F 1 "10k" H 3750 2250 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 3880 2300 50  0001 C CNN
F 3 "~" H 3950 2300 50  0001 C CNN
	1    3950 2300
	1    0    0    -1  
$EndComp
Wire Wire Line
	3650 1900 3650 1450
Wire Wire Line
	3250 2300 3250 2550
Wire Wire Line
	3250 2550 3650 2550
Wire Wire Line
	3250 1250 3250 1150
Wire Wire Line
	3250 1550 3250 1600
Connection ~ 3250 1150
Wire Wire Line
	3250 2550 2750 2550
Connection ~ 3250 2550
Wire Wire Line
	5100 1150 5250 1150
$Comp
L Device:R R6
U 1 1 5F5B1C2F
P 2750 2300
F 0 "R6" H 2600 2350 50  0000 L CNN
F 1 "20k" H 2500 2250 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 2680 2300 50  0001 C CNN
F 3 "~" H 2750 2300 50  0001 C CNN
	1    2750 2300
	1    0    0    -1  
$EndComp
Connection ~ 2750 2550
Wire Wire Line
	2750 2000 2850 2000
$Comp
L Device:Q_PMOS_GSD Q1
U 1 1 5F4DFB6A
P 3950 1550
F 0 "Q1" V 3900 1400 50  0000 C CNN
F 1 "IRLML5203" V 4200 1550 50  0000 C CNN
F 2 "Package_TO_SOT_SMD:SOT-23" H 4150 1650 50  0001 C CNN
F 3 "~" H 3950 1550 50  0001 C CNN
	1    3950 1550
	0    -1   -1   0   
$EndComp
$Comp
L cs-rescue:Conn-My_Library J10
U 1 1 5F4E0203
P 3650 2000
F 0 "J10" V 3600 2100 50  0001 C CNN
F 1 "B+" V 3700 2000 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 3650 2000 50  0001 C CNN
F 3 "~" H 3650 2000 50  0001 C CNN
	1    3650 2000
	0    1    1    0   
$EndComp
$Comp
L cs-rescue:Conn-My_Library J11
U 1 1 5F4E0367
P 3650 2450
F 0 "J11" V 3600 2550 50  0001 C CNN
F 1 "B-" V 3700 2450 50  0000 C CNN
F 2 "My-library:SMD-CONN" H 3650 2450 50  0001 C CNN
F 3 "~" H 3650 2450 50  0001 C CNN
	1    3650 2450
	0    -1   -1   0   
$EndComp
Connection ~ 3650 1900
Wire Wire Line
	7650 1150 7650 1800
Wire Wire Line
	7250 1150 7250 1800
Wire Wire Line
	7050 1150 7050 1800
Wire Wire Line
	6650 1150 6650 1800
Wire Wire Line
	6450 1150 6450 1800
Wire Wire Line
	6100 1150 6100 1800
Connection ~ 6100 1800
Wire Wire Line
	5700 1150 5700 1600
Wire Wire Line
	5100 1150 5100 1600
Connection ~ 5100 1600
Connection ~ 5100 2550
$Comp
L Device:R R15
U 1 1 5F4FE0DB
P 2750 1450
F 0 "R15" H 2550 1450 50  0000 L CNN
F 1 "1k" H 2600 1550 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 2680 1450 50  0001 C CNN
F 3 "~" H 2750 1450 50  0001 C CNN
	1    2750 1450
	-1   0    0    1   
$EndComp
$Comp
L Device:LED D6
U 1 1 5F4FE244
P 2750 1750
F 0 "D6" V 2700 1950 50  0000 R CNN
F 1 "LED" V 2600 1950 50  0000 R CNN
F 2 "LED_SMD:LED_0805_2012Metric" H 2750 1750 50  0001 C CNN
F 3 "~" H 2750 1750 50  0001 C CNN
	1    2750 1750
	0    -1   -1   0   
$EndComp
Wire Wire Line
	2750 1900 2850 1900
Wire Wire Line
	2250 1750 2250 1800
Wire Wire Line
	2250 2550 2750 2550
Wire Wire Line
	2350 1750 2350 1800
Wire Wire Line
	2350 1800 2250 1800
Connection ~ 2250 1800
Wire Wire Line
	2250 1800 2250 2550
Wire Wire Line
	2650 1150 3250 1150
Wire Wire Line
	3050 1550 3250 1550
Connection ~ 3250 1550
Connection ~ 3650 2550
Wire Wire Line
	3250 1150 3500 1150
Wire Wire Line
	3650 2550 3950 2550
Connection ~ 3950 2550
Wire Wire Line
	3650 1450 3750 1450
Wire Wire Line
	3950 1750 3700 1750
Wire Wire Line
	3700 1750 3700 1150
Wire Wire Line
	3950 2550 4150 2550
Wire Wire Line
	4150 2150 4150 1450
Wire Wire Line
	4150 1150 4050 1150
Connection ~ 4150 1450
Wire Wire Line
	4150 1450 4150 1150
Wire Wire Line
	4150 2450 4150 2550
Wire Wire Line
	3700 1150 3750 1150
Connection ~ 3700 1150
Connection ~ 4150 1150
Text Label 2750 1100 0    50   ~ 0
VBUS
Text Label 3700 1900 0    50   ~ 0
B+
$Comp
L power:GND #PWR0103
U 1 1 5F5F2078
P 4250 3500
F 0 "#PWR0103" H 4250 3250 50  0001 C CNN
F 1 "GND" H 4255 3327 50  0001 C CNN
F 2 "" H 4250 3500 50  0001 C CNN
F 3 "" H 4250 3500 50  0001 C CNN
	1    4250 3500
	0    1    1    0   
$EndComp
$Comp
L power:GND #PWR0104
U 1 1 5F5F2269
P 3950 3300
F 0 "#PWR0104" H 3950 3050 50  0001 C CNN
F 1 "GND" H 3955 3127 50  0001 C CNN
F 2 "" H 3950 3300 50  0001 C CNN
F 3 "" H 3950 3300 50  0001 C CNN
	1    3950 3300
	0    1    1    0   
$EndComp
$Comp
L cs-rescue:Conn-My_Library J18
U 1 1 5F61DF4C
P 4150 5000
F 0 "J18" H 4500 5000 50  0001 L CNN
F 1 "I2C-SCL" H 4150 5000 50  0000 L CNN
F 2 "My-library:SMD-CONN" H 4150 5000 50  0001 C CNN
F 3 "~" H 4150 5000 50  0001 C CNN
	1    4150 5000
	-1   0    0    1   
$EndComp
$Comp
L cs-rescue:Conn-My_Library J19
U 1 1 5F61DFF6
P 4150 5100
F 0 "J19" H 4500 5100 50  0001 L CNN
F 1 "I2C-SDA" H 4150 5100 50  0000 L CNN
F 2 "My-library:SMD-CONN" H 4150 5100 50  0001 C CNN
F 3 "~" H 4150 5100 50  0001 C CNN
	1    4150 5100
	-1   0    0    1   
$EndComp
$Comp
L cs-rescue:Conn-My_Library J3
U 1 1 5F631FEA
P 5650 5700
F 0 "J3" H 5800 5750 50  0001 L CNN
F 1 "SWDIO" H 5700 5700 50  0000 L CNN
F 2 "My-library:SMD-CONN" H 5650 5700 50  0001 C CNN
F 3 "~" H 5650 5700 50  0001 C CNN
	1    5650 5700
	1    0    0    -1  
$EndComp
$Comp
L cs-rescue:Conn-My_Library J4
U 1 1 5F63207A
P 5650 5800
F 0 "J4" H 5800 5850 50  0001 L CNN
F 1 "SWCLK" H 5700 5800 50  0000 L CNN
F 2 "My-library:SMD-CONN" H 5650 5800 50  0001 C CNN
F 3 "~" H 5650 5800 50  0001 C CNN
	1    5650 5800
	1    0    0    -1  
$EndComp
$Comp
L Device:C C11
U 1 1 5F692265
P 3500 1300
F 0 "C11" H 3550 1400 50  0000 L CNN
F 1 "CL21A226MQQNNNE" H 3550 1200 50  0001 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 3538 1150 50  0001 C CNN
F 3 "GRM21BR60J226M" H 3500 1300 50  0001 C CNN
	1    3500 1300
	1    0    0    -1  
$EndComp
Connection ~ 3500 1150
Wire Wire Line
	3500 1150 3700 1150
$Comp
L power:GND #PWR02
U 1 1 5F69232F
P 3500 1450
F 0 "#PWR02" H 3500 1200 50  0001 C CNN
F 1 "GND" H 3505 1277 50  0000 C CNN
F 2 "" H 3500 1450 50  0001 C CNN
F 3 "" H 3500 1450 50  0001 C CNN
	1    3500 1450
	1    0    0    -1  
$EndComp
Connection ~ 5100 1150
Wire Wire Line
	4800 2450 4800 2550
Wire Wire Line
	4800 2550 5100 2550
Wire Wire Line
	4800 1150 4800 2150
$Comp
L Device:C C20
U 1 1 5F981D65
P 7550 3850
F 0 "C20" H 7550 3950 50  0000 L CNN
F 1 "CL21A226MQQNNNE" H 7550 3750 50  0001 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 7588 3700 50  0001 C CNN
F 3 "GRM21BR60J226M" H 7550 3850 50  0001 C CNN
	1    7550 3850
	1    0    0    -1  
$EndComp
Wire Wire Line
	3950 1750 3950 2150
Connection ~ 3950 1750
Wire Wire Line
	3950 2450 3950 2550
Wire Wire Line
	5700 2150 5700 2000
Connection ~ 5700 2000
Wire Wire Line
	5700 2450 5700 2550
Wire Wire Line
	2750 2000 2750 2150
Wire Wire Line
	2750 2450 2750 2550
Wire Wire Line
	6100 2000 6100 2150
Connection ~ 6100 2000
Wire Wire Line
	6100 2450 6100 2550
Wire Wire Line
	3150 6000 3150 6050
Connection ~ 3150 6050
Wire Wire Line
	3150 6050 3400 6050
Wire Wire Line
	3150 5700 3150 5400
Wire Wire Line
	3150 5400 3200 5400
Connection ~ 2450 5600
Wire Wire Line
	2750 5600 2800 5600
Wire Wire Line
	2450 6050 2450 6000
Wire Wire Line
	2450 5700 2450 5600
$Comp
L cs-rescue:TLV3501-My_Library D5
U 1 1 5F81603B
P 3550 5500
F 0 "D5" H 3500 5350 50  0000 L CNN
F 1 "TLV3501" H 3450 5750 50  0000 L CNN
F 2 "Package_TO_SOT_SMD:SOT-23-6" H 3550 5500 50  0001 C CNN
F 3 "" H 3550 5500 50  0001 C CNN
	1    3550 5500
	1    0    0    -1  
$EndComp
Wire Wire Line
	3650 6050 3400 6050
$Comp
L Device:C C24
U 1 1 5FB2508D
P 3500 3850
F 0 "C24" H 3500 3950 50  0000 L CNN
F 1 "CL21A226MQQNNNE" H 3500 3750 50  0001 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 3538 3700 50  0001 C CNN
F 3 "GRM21BR60J226M" H 3500 3850 50  0001 C CNN
	1    3500 3850
	1    0    0    -1  
$EndComp
$Comp
L cs-rescue:NCP551SN-My_Library D8
U 1 1 5FB6DBB6
P 2900 3650
F 0 "D8" H 2900 3975 50  0000 C CNN
F 1 "NCP551SN" H 2900 3884 50  0000 C CNN
F 2 "Package_TO_SOT_SMD:TSOT-23-5" H 2900 3650 50  0001 C CNN
F 3 "" H 2900 3650 50  0001 C CNN
	1    2900 3650
	1    0    0    -1  
$EndComp
Wire Wire Line
	2600 3700 2600 3550
Wire Wire Line
	3200 3550 3300 3550
$Comp
L power:+5V #PWR0114
U 1 1 5FB93608
P 2600 3450
F 0 "#PWR0114" H 2600 3300 50  0001 C CNN
F 1 "+5V" H 2615 3623 50  0001 C CNN
F 2 "" H 2600 3450 50  0001 C CNN
F 3 "" H 2600 3450 50  0001 C CNN
	1    2600 3450
	1    0    0    -1  
$EndComp
Wire Wire Line
	2600 3450 2600 3550
Connection ~ 2600 3550
$Comp
L Device:C C25
U 1 1 5F8F1332
P 6400 3850
F 0 "C25" H 6150 3900 50  0000 L CNN
F 1 "CL21A226MQQNNNE" H 6100 3800 50  0001 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 6438 3700 50  0001 C CNN
F 3 "GRM21BR60J226M" H 6400 3850 50  0001 C CNN
	1    6400 3850
	1    0    0    -1  
$EndComp
$Comp
L power:+5V #PWR0102
U 1 1 5F8B4010
P 6050 3600
F 0 "#PWR0102" H 6050 3450 50  0001 C CNN
F 1 "+5V" H 5900 3700 50  0001 C CNN
F 2 "" H 6050 3600 50  0001 C CNN
F 3 "" H 6050 3600 50  0001 C CNN
	1    6050 3600
	1    0    0    -1  
$EndComp
$Comp
L Device:L L6
U 1 1 5F8D05CE
P 6200 3600
F 0 "L6" V 6250 3600 50  0000 C CNN
F 1 "47uH" V 6150 3600 50  0000 C CNN
F 2 "Inductor_SMD:L_0805_2012Metric" H 6200 3600 50  0001 C CNN
F 3 "~" H 6200 3600 50  0001 C CNN
	1    6200 3600
	0    -1   -1   0   
$EndComp
$Comp
L Regulator_Linear:LP2985-3.3 U4
U 1 1 5F92576E
P 6850 3700
F 0 "U4" H 6850 4042 50  0000 C CNN
F 1 "LP2985-3" H 6850 3951 50  0000 C CNN
F 2 "Package_TO_SOT_SMD:SOT-23-5" H 6850 4025 50  0001 C CNN
F 3 "http://www.ti.com/lit/ds/symlink/lp2985.pdf" H 6850 3700 50  0001 C CNN
	1    6850 3700
	1    0    0    -1  
$EndComp
$Comp
L Device:C C21
U 1 1 5F925965
P 7300 3850
F 0 "C21" H 7300 3950 50  0000 L CNN
F 1 "0.1uF" H 7300 3750 50  0000 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 7338 3700 50  0001 C CNN
F 3 "~" H 7300 3850 50  0001 C CNN
	1    7300 3850
	1    0    0    -1  
$EndComp
Wire Wire Line
	6350 3600 6400 3600
Wire Wire Line
	6450 3700 6400 3700
Wire Wire Line
	6400 3700 6400 3600
Connection ~ 6400 3700
Connection ~ 6400 3600
Wire Wire Line
	6400 3600 6450 3600
Wire Wire Line
	7250 3700 7300 3700
Wire Wire Line
	7250 3600 7550 3600
Wire Wire Line
	7550 3600 7550 3700
Wire Wire Line
	7550 4000 7300 4000
Connection ~ 6850 4000
Wire Wire Line
	6850 4000 6400 4000
Connection ~ 7300 4000
Wire Wire Line
	7300 4000 6850 4000
Wire Wire Line
	2750 1300 3050 1300
Wire Wire Line
	3050 1300 3050 1550
Connection ~ 4800 2550
Connection ~ 4800 1150
$Comp
L power:GND #PWR013
U 1 1 5F9875BC
P 3400 6050
F 0 "#PWR013" H 3400 5800 50  0001 C CNN
F 1 "GND" H 3405 5877 50  0001 C CNN
F 2 "" H 3400 6050 50  0001 C CNN
F 3 "" H 3400 6050 50  0001 C CNN
	1    3400 6050
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR012
U 1 1 5F98764D
P 6400 4000
F 0 "#PWR012" H 6400 3750 50  0001 C CNN
F 1 "GND" H 6405 3827 50  0001 C CNN
F 2 "" H 6400 4000 50  0001 C CNN
F 3 "" H 6400 4000 50  0001 C CNN
	1    6400 4000
	1    0    0    -1  
$EndComp
Connection ~ 6400 4000
$Comp
L power:+3.3V #PWR06
U 1 1 5F98E2BD
P 3300 3550
F 0 "#PWR06" H 3300 3400 50  0001 C CNN
F 1 "+3.3V" H 3315 3723 50  0001 C CNN
F 2 "" H 3300 3550 50  0001 C CNN
F 3 "" H 3300 3550 50  0001 C CNN
	1    3300 3550
	1    0    0    -1  
$EndComp
$Comp
L power:+5V #PWR01
U 1 1 5F99BFA8
P 4800 1150
F 0 "#PWR01" H 4800 1000 50  0001 C CNN
F 1 "+5V" H 4815 1323 50  0001 C CNN
F 2 "" H 4800 1150 50  0001 C CNN
F 3 "" H 4800 1150 50  0001 C CNN
	1    4800 1150
	1    0    0    -1  
$EndComp
Connection ~ 3300 3550
$Comp
L Device:C C5
U 1 1 5F99D110
P 7650 1950
F 0 "C5" H 7700 1850 50  0000 L CNN
F 1 "GRM32ER71H106K" H 7950 2200 50  0001 L CNN
F 2 "Capacitor_SMD:C_1210_3225Metric" H 7688 1800 50  0001 C CNN
F 3 "GRM32ER71H106K" H 7650 1950 50  0001 C CNN
	1    7650 1950
	1    0    0    -1  
$EndComp
$Comp
L Device:C C17
U 1 1 5F99D1BA
P 7900 1950
F 0 "C17" H 7950 1850 50  0000 L CNN
F 1 "GRM32ER71H106K" H 7950 1750 50  0001 L CNN
F 2 "Capacitor_SMD:C_1210_3225Metric" H 7938 1800 50  0001 C CNN
F 3 "" H 7900 1950 50  0001 C CNN
	1    7900 1950
	1    0    0    -1  
$EndComp
Wire Wire Line
	7900 2100 7900 2550
Wire Wire Line
	7900 2550 7650 2550
Wire Wire Line
	7650 1150 7900 1150
Wire Wire Line
	7900 1150 7900 1800
$Comp
L power:+3.3VADC #PWR0101
U 1 1 5F9AB0A6
P 7550 3600
F 0 "#PWR0101" H 7700 3550 50  0001 C CNN
F 1 "+3.3VADC" H 7570 3743 50  0001 C CNN
F 2 "" H 7550 3600 50  0001 C CNN
F 3 "" H 7550 3600 50  0001 C CNN
	1    7550 3600
	1    0    0    -1  
$EndComp
Connection ~ 7550 3600
Wire Wire Line
	4600 2450 4600 2550
Connection ~ 4600 2550
Wire Wire Line
	4600 2550 4800 2550
Wire Wire Line
	4600 2150 4600 1250
Wire Wire Line
	4600 1150 4800 1150
Connection ~ 7900 1150
$Comp
L Device:C C23
U 1 1 5F9DA919
P 7750 3850
F 0 "C23" H 7750 3950 50  0000 L CNN
F 1 "CL21A226MQQNNNE" H 7750 3750 50  0001 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 7788 3700 50  0001 C CNN
F 3 "GRM21BR60J226M" H 7750 3850 50  0001 C CNN
	1    7750 3850
	1    0    0    -1  
$EndComp
Wire Wire Line
	7550 4000 7750 4000
Connection ~ 7550 4000
Wire Wire Line
	7550 3600 7750 3600
Wire Wire Line
	7750 3600 7750 3700
Text Label 7450 3500 0    50   ~ 0
+3VDA
Text Label 3250 3450 0    50   ~ 0
+3V
Text Label 4700 1050 0    50   ~ 0
+BAT
Text Label 2500 3350 0    50   ~ 0
+BAT
Text Label 5950 3500 0    50   ~ 0
+BAT
Wire Wire Line
	3650 5650 3650 6050
Wire Wire Line
	3300 3550 3500 3550
$Comp
L Device:Q_NPN_BEC Q3
U 1 1 601D5B2E
P 6400 5400
F 0 "Q3" H 6591 5446 50  0000 L CNN
F 1 "KT231" H 6591 5355 50  0000 L CNN
F 2 "Package_TO_SOT_SMD:SOT-23" H 6600 5500 50  0001 C CNN
F 3 "~" H 6400 5400 50  0001 C CNN
	1    6400 5400
	1    0    0    -1  
$EndComp
$Comp
L Device:Q_NPN_BEC Q2
U 1 1 601DE151
P 6950 5400
F 0 "Q2" H 7141 5446 50  0000 L CNN
F 1 "KT231" H 7141 5355 50  0000 L CNN
F 2 "Package_TO_SOT_SMD:SOT-23" H 7150 5500 50  0001 C CNN
F 3 "~" H 6950 5400 50  0001 C CNN
	1    6950 5400
	1    0    0    -1  
$EndComp
$Comp
L cs-rescue:Conn-My_Library J7
U 1 1 60201E7D
P 7150 5200
F 0 "J7" H 7200 5200 50  0001 L CNN
F 1 "Vibro" H 7050 5300 50  0000 L CNN
F 2 "My-library:SMD-CONN" H 7150 5200 50  0001 C CNN
F 3 "~" H 7150 5200 50  0001 C CNN
	1    7150 5200
	1    0    0    -1  
$EndComp
$Comp
L cs-rescue:Conn-My_Library J12
U 1 1 60211FE7
P 6600 5200
F 0 "J12" H 6650 5200 50  0001 L CNN
F 1 "Buzzer" H 6400 5300 50  0000 L CNN
F 2 "My-library:SMD-CONN" H 6600 5200 50  0001 C CNN
F 3 "~" H 6600 5200 50  0001 C CNN
	1    6600 5200
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR05
U 1 1 60212CD6
P 7050 5600
F 0 "#PWR05" H 7050 5350 50  0001 C CNN
F 1 "GND" H 7055 5427 50  0001 C CNN
F 2 "" H 7050 5600 50  0001 C CNN
F 3 "" H 7050 5600 50  0001 C CNN
	1    7050 5600
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR07
U 1 1 60213AAB
P 6500 5600
F 0 "#PWR07" H 6500 5350 50  0001 C CNN
F 1 "GND" H 6505 5427 50  0001 C CNN
F 2 "" H 6500 5600 50  0001 C CNN
F 3 "" H 6500 5600 50  0001 C CNN
	1    6500 5600
	1    0    0    -1  
$EndComp
$Comp
L Device:R R4
U 1 1 60214698
P 6750 5250
F 0 "R4" H 6600 5200 50  0000 L CNN
F 1 "10k" H 6550 5300 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 6680 5250 50  0001 C CNN
F 3 "~" H 6750 5250 50  0001 C CNN
	1    6750 5250
	-1   0    0    1   
$EndComp
$Comp
L Device:R R8
U 1 1 6021548B
P 6200 5250
F 0 "R8" H 6050 5150 50  0000 L CNN
F 1 "10k" H 6000 5250 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 6130 5250 50  0001 C CNN
F 3 "~" H 6200 5250 50  0001 C CNN
	1    6200 5250
	-1   0    0    1   
$EndComp
$Comp
L Switch:SW_SPDT SW1
U 1 1 6022549C
P 4350 1150
F 0 "SW1" H 4350 1450 50  0000 C CNN
F 1 "power" H 4350 1400 50  0000 C CNN
F 2 "My-library:MySwithc" H 4350 1150 50  0001 C CNN
F 3 "" H 4350 1150 50  0001 C CNN
	1    4350 1150
	1    0    0    -1  
$EndComp
Wire Wire Line
	4150 2550 4600 2550
Connection ~ 4150 2550
Wire Wire Line
	4550 1250 4600 1250
Connection ~ 4600 1250
Wire Wire Line
	4600 1250 4600 1150
$Comp
L Device:R R16
U 1 1 602C232B
P 6050 3850
F 0 "R16" H 6100 3750 50  0000 L CNN
F 1 "10k" H 6100 3850 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 5980 3850 50  0001 C CNN
F 3 "~" H 6050 3850 50  0001 C CNN
	1    6050 3850
	-1   0    0    1   
$EndComp
$Comp
L Device:R R17
U 1 1 602C3252
P 6050 4150
F 0 "R17" H 5850 4100 50  0000 L CNN
F 1 "10k" H 5850 4200 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 5980 4150 50  0001 C CNN
F 3 "~" H 6050 4150 50  0001 C CNN
	1    6050 4150
	-1   0    0    1   
$EndComp
$Comp
L power:GND #PWR010
U 1 1 602CBED5
P 6050 4300
F 0 "#PWR010" H 6050 4050 50  0001 C CNN
F 1 "GND" H 6055 4127 50  0001 C CNN
F 2 "" H 6050 4300 50  0001 C CNN
F 3 "" H 6050 4300 50  0001 C CNN
	1    6050 4300
	1    0    0    -1  
$EndComp
$Comp
L cs-rescue:Conn-My_Library J13
U 1 1 602E7896
P 5950 3600
F 0 "J13" H 5900 3500 50  0001 L CNN
F 1 "+BAT" H 6000 3600 50  0000 L CNN
F 2 "My-library:SMD-CONN" H 5950 3600 50  0001 C CNN
F 3 "~" H 5950 3600 50  0001 C CNN
	1    5950 3600
	-1   0    0    1   
$EndComp
Text Label 3900 5450 0    50   ~ 0
trigger
Connection ~ 6050 3600
Wire Wire Line
	6050 3600 6050 3700
$Comp
L Device:R R18
U 1 1 60175B91
P 2800 5450
F 0 "R18" H 2850 5500 50  0000 L CNN
F 1 "220" H 2850 5400 50  0000 L CNN
F 2 "Resistor_SMD:R_0805_2012Metric" V 2730 5450 50  0001 C CNN
F 3 "~" H 2800 5450 50  0001 C CNN
	1    2800 5450
	1    0    0    -1  
$EndComp
Connection ~ 2800 5600
Wire Wire Line
	2450 6050 2800 6050
Wire Wire Line
	2800 6000 2800 6050
Wire Wire Line
	2800 5600 3200 5600
Connection ~ 2800 6050
Wire Wire Line
	2800 6050 3150 6050
Wire Wire Line
	2800 5700 2800 5600
Wire Wire Line
	2850 5300 2800 5300
Connection ~ 2800 5300
Wire Wire Line
	3150 5300 3150 5400
Connection ~ 3150 5400
Wire Wire Line
	2800 5000 3400 5000
Wire Wire Line
	3400 5000 3400 5250
Connection ~ 3500 3550
$Comp
L Device:C C18
U 1 1 60434A92
P 2600 3850
F 0 "C18" H 2400 4050 50  0000 L CNN
F 1 "CL21A226MQQNNNE" H 2400 3950 50  0001 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 2638 3700 50  0001 C CNN
F 3 "GRM21BR60J226M" H 2600 3850 50  0001 C CNN
	1    2600 3850
	1    0    0    -1  
$EndComp
Connection ~ 2600 3700
Wire Wire Line
	2600 4000 2900 4000
Connection ~ 2900 4000
Wire Wire Line
	2900 4000 3300 4000
Connection ~ 3300 4000
Wire Wire Line
	3300 4000 3500 4000
Wire Wire Line
	2900 3900 2900 4000
Wire Wire Line
	3300 3550 3300 3700
Wire Wire Line
	3500 3550 3500 3700
$Comp
L MCU_ST_STM32F1:STM32F103C8Tx U1
U 1 1 60275E25
P 4950 4600
F 0 "U1" H 4900 3011 50  0000 C CNN
F 1 "STM32F103C8Tx" H 4950 5200 50  0000 C CNN
F 2 "Package_QFP:LQFP-48_7x7mm_P0.5mm" H 4350 3200 50  0001 R CNN
F 3 "http://www.st.com/st-web-ui/static/active/en/resource/technical/document/datasheet/CD00161566.pdf" H 4950 4600 50  0001 C CNN
	1    4950 4600
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR04
U 1 1 60277865
P 5050 6250
F 0 "#PWR04" H 5050 6000 50  0001 C CNN
F 1 "GND" H 5055 6077 50  0001 C CNN
F 2 "" H 5050 6250 50  0001 C CNN
F 3 "" H 5050 6250 50  0001 C CNN
	1    5050 6250
	1    0    0    -1  
$EndComp
Wire Wire Line
	4750 6100 4850 6100
Wire Wire Line
	5050 6100 5050 6250
Connection ~ 5050 6100
Connection ~ 4850 6100
Wire Wire Line
	4850 6100 4950 6100
Connection ~ 4950 6100
Wire Wire Line
	4950 6100 5050 6100
Connection ~ 4250 3300
Wire Wire Line
	4750 3100 4850 3100
Connection ~ 4850 3100
Wire Wire Line
	4850 3100 4950 3100
Connection ~ 4950 3100
Wire Wire Line
	4950 3100 5050 3100
$Comp
L Device:L L1
U 1 1 602E8F8B
P 5150 2950
F 0 "L1" H 5250 3000 50  0000 C CNN
F 1 "47uH" H 5300 2900 50  0000 C CNN
F 2 "Inductor_SMD:L_0805_2012Metric" H 5150 2950 50  0001 C CNN
F 3 "~" H 5150 2950 50  0001 C CNN
	1    5150 2950
	1    0    0    -1  
$EndComp
$Comp
L power:+3.3V #PWR08
U 1 1 602EAA78
P 5150 2800
F 0 "#PWR08" H 5150 2650 50  0001 C CNN
F 1 "+3.3V" H 5165 2973 50  0001 C CNN
F 2 "" H 5150 2800 50  0001 C CNN
F 3 "" H 5150 2800 50  0001 C CNN
	1    5150 2800
	1    0    0    -1  
$EndComp
Wire Wire Line
	5150 2800 5050 2800
Wire Wire Line
	5050 2800 5050 3100
Connection ~ 5150 2800
Connection ~ 5050 3100
$Comp
L Device:C C13
U 1 1 602F33AF
P 5600 3250
F 0 "C13" H 5600 3350 50  0000 L CNN
F 1 "CL21A226MQQNNNE" H 5600 3150 50  0001 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 5638 3100 50  0001 C CNN
F 3 "GRM21BR60J226M" H 5600 3250 50  0001 C CNN
	1    5600 3250
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR011
U 1 1 602FC0E3
P 5600 3400
F 0 "#PWR011" H 5600 3150 50  0001 C CNN
F 1 "GND" H 5605 3227 50  0001 C CNN
F 2 "" H 5600 3400 50  0001 C CNN
F 3 "" H 5600 3400 50  0001 C CNN
	1    5600 3400
	1    0    0    -1  
$EndComp
$Comp
L Device:C C19
U 1 1 602FD978
P 3700 3850
F 0 "C19" H 3700 3950 50  0000 L CNN
F 1 "CL21A226MQQNNNE" H 3700 3750 50  0001 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 3738 3700 50  0001 C CNN
F 3 "GRM21BR60J226M" H 3700 3850 50  0001 C CNN
	1    3700 3850
	1    0    0    -1  
$EndComp
Wire Wire Line
	3500 3550 3700 3550
Wire Wire Line
	3700 3550 3700 3700
Wire Wire Line
	3500 4000 3700 4000
Connection ~ 3500 4000
$Comp
L power:+3.3VADC #PWR0106
U 1 1 60343C17
P 3400 5000
F 0 "#PWR0106" H 3550 4950 50  0001 C CNN
F 1 "+3.3VADC" H 3420 5143 50  0001 C CNN
F 2 "" H 3400 5000 50  0001 C CNN
F 3 "" H 3400 5000 50  0001 C CNN
	1    3400 5000
	1    0    0    -1  
$EndComp
Connection ~ 3400 5000
Wire Wire Line
	3850 5500 4250 5500
$Comp
L power:GND #PWR0105
U 1 1 6041381D
P 6100 5800
F 0 "#PWR0105" H 6100 5550 50  0001 C CNN
F 1 "GND" H 6105 5627 50  0001 C CNN
F 2 "" H 6100 5800 50  0001 C CNN
F 3 "" H 6100 5800 50  0001 C CNN
	1    6100 5800
	-1   0    0    -1  
$EndComp
Wire Wire Line
	2450 5600 2450 4400
Wire Wire Line
	2450 4400 4250 4400
Wire Wire Line
	5800 5500 5550 5500
Wire Wire Line
	5750 4900 5550 4900
Connection ~ 5150 3100
$Comp
L Device:Thermistor_NTC TH1
U 1 1 601DACE1
P 3800 6300
F 0 "TH1" H 3897 6300 50  0000 L CNN
F 1 "NCP18XH103F03RB" H 3898 6255 50  0001 L CNN
F 2 "Resistor_SMD:R_0603_1608Metric" H 3800 6350 50  0001 C CNN
F 3 "~" H 3800 6350 50  0001 C CNN
	1    3800 6300
	1    0    0    -1  
$EndComp
Wire Wire Line
	5750 4000 5750 4900
Wire Wire Line
	5750 4000 6050 4000
Connection ~ 6050 4000
Wire Wire Line
	6750 4700 6750 5100
Wire Wire Line
	5550 4700 6750 4700
Wire Wire Line
	5550 4800 6200 4800
Wire Wire Line
	6200 4800 6200 5100
Wire Wire Line
	4550 1050 4550 950 
Wire Wire Line
	4550 950  4150 950 
Wire Wire Line
	4150 950  4150 1150
$Comp
L power:+3.3VA #PWR0108
U 1 1 603AEE2B
P 5600 3100
F 0 "#PWR0108" H 5600 2950 50  0001 C CNN
F 1 "+3.3VA" H 5615 3273 50  0000 C CNN
F 2 "" H 5600 3100 50  0001 C CNN
F 3 "" H 5600 3100 50  0001 C CNN
	1    5600 3100
	1    0    0    -1  
$EndComp
Connection ~ 5600 3100
Wire Wire Line
	5150 3100 5600 3100
$Comp
L Device:C C22
U 1 1 601F5CF1
P 4600 2300
F 0 "C22" H 4650 2400 50  0000 L CNN
F 1 "CL21A226MQQNNNE" H 4650 2200 50  0001 L CNN
F 2 "Capacitor_SMD:C_0805_2012Metric" H 4638 2150 50  0001 C CNN
F 3 "GRM21BR60J226M" H 4600 2300 50  0001 C CNN
	1    4600 2300
	1    0    0    -1  
$EndComp
$Comp
L My_Library:JDY-10 D4
U 1 1 6021FB35
P 8250 4950
F 0 "D4" H 8250 5675 50  0000 C CNN
F 1 "JDY-10" H 8250 5584 50  0000 C CNN
F 2 "" H 8250 5300 50  0001 C CNN
F 3 "" H 8250 5300 50  0001 C CNN
	1    8250 4950
	1    0    0    -1  
$EndComp
$Comp
L power:+3.3V #PWR014
U 1 1 602232DF
P 7750 5150
F 0 "#PWR014" H 7750 5000 50  0001 C CNN
F 1 "+3.3V" H 7765 5323 50  0001 C CNN
F 2 "" H 7750 5150 50  0001 C CNN
F 3 "" H 7750 5150 50  0001 C CNN
	1    7750 5150
	0    -1   -1   0   
$EndComp
$Comp
L power:GND #PWR015
U 1 1 60224484
P 7750 5250
F 0 "#PWR015" H 7750 5000 50  0001 C CNN
F 1 "GND" H 7755 5077 50  0001 C CNN
F 2 "" H 7750 5250 50  0001 C CNN
F 3 "" H 7750 5250 50  0001 C CNN
	1    7750 5250
	0    1    1    0   
$EndComp
Wire Wire Line
	4250 5700 4150 5700
Wire Wire Line
	4150 5700 4150 6450
Wire Wire Line
	4150 6450 7750 6450
Wire Wire Line
	7750 6450 7750 5450
Text GLabel 5750 5300 2    50   Input ~ 0
uart_txd
Text GLabel 5750 5400 2    50   Input ~ 0
uart_rxd
Text GLabel 8850 5250 2    50   Input ~ 0
uart_txd
Text GLabel 8850 5150 2    50   Input ~ 0
uart_rxd
Wire Wire Line
	8750 5150 8850 5150
Wire Wire Line
	8750 5250 8850 5250
Wire Wire Line
	5550 5300 5600 5300
Wire Wire Line
	5550 5400 5700 5400
$Comp
L cs-rescue:Conn-My_Library J6
U 1 1 602CFFB9
P 5600 5200
F 0 "J6" H 5650 5200 50  0001 L CNN
F 1 "TxD" H 5400 5300 50  0000 L CNN
F 2 "My-library:SMD-CONN" H 5600 5200 50  0001 C CNN
F 3 "~" H 5600 5200 50  0001 C CNN
	1    5600 5200
	0    -1   -1   0   
$EndComp
Connection ~ 5600 5300
Wire Wire Line
	5600 5300 5750 5300
$Comp
L cs-rescue:Conn-My_Library J14
U 1 1 602D0C82
P 5700 5300
F 0 "J14" H 5750 5300 50  0001 L CNN
F 1 "RxD" H 5500 5400 50  0000 L CNN
F 2 "My-library:SMD-CONN" H 5700 5300 50  0001 C CNN
F 3 "~" H 5700 5300 50  0001 C CNN
	1    5700 5300
	0    -1   -1   0   
$EndComp
Connection ~ 5700 5400
Wire Wire Line
	5700 5400 5750 5400
$Comp
L cs-rescue:Conn-My_Library J5
U 1 1 602D1503
P 4150 5600
F 0 "J5" H 4200 5600 50  0001 L CNN
F 1 "Stat" H 3950 5700 50  0000 L CNN
F 2 "My-library:SMD-CONN" H 4150 5600 50  0001 C CNN
F 3 "~" H 4150 5600 50  0001 C CNN
	1    4150 5600
	0    -1   -1   0   
$EndComp
Connection ~ 4150 5700
$Comp
L cs-rescue:Conn-My_Library J15
U 1 1 602D7834
P 3700 3450
F 0 "J15" H 3750 3450 50  0001 L CNN
F 1 "+BT" H 3500 3550 50  0000 L CNN
F 2 "My-library:SMD-CONN" H 3700 3450 50  0001 C CNN
F 3 "~" H 3700 3450 50  0001 C CNN
	1    3700 3450
	0    -1   -1   0   
$EndComp
Connection ~ 3700 3550
$EndSCHEMATC
