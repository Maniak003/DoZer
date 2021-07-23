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
L FJ600035-rescue:FJ60035-My_Library D1
U 1 1 60F0222B
P 5300 2950
F 0 "D1" H 5200 3400 50  0000 R CNN
F 1 "FJ60035" H 5200 3300 50  0000 R CNN
F 2 "My-library:FJ60035" H 5300 2950 50  0001 C CNN
F 3 "" H 5300 2950 50  0001 C CNN
	1    5300 2950
	1    0    0    -1  
$EndComp
$Comp
L FJ600035-rescue:Conn-My_Library J1
U 1 1 60F0340F
P 5300 2450
F 0 "J1" V 5242 2402 50  0000 R CNN
F 1 "Conn" V 5197 2402 50  0001 R CNN
F 2 "My-library:SMD-CONN" H 5350 2300 50  0001 C CNN
F 3 "~" H 5300 2450 50  0001 C CNN
	1    5300 2450
	0    -1   -1   0   
$EndComp
$Comp
L FJ600035-rescue:Conn-My_Library J2
U 1 1 60F0390A
P 5300 3450
F 0 "J2" V 5242 3498 50  0000 L CNN
F 1 "Conn" V 5287 3498 50  0001 L CNN
F 2 "My-library:SMD-CONN" H 5350 3300 50  0001 C CNN
F 3 "~" H 5300 3450 50  0001 C CNN
	1    5300 3450
	0    1    1    0   
$EndComp
$Comp
L FJ600035-rescue:Conn-My_Library J3
U 1 1 60F0A0D5
P 5800 2950
F 0 "J3" H 5828 2950 50  0000 L CNN
F 1 "Conn" H 5828 2905 50  0001 L CNN
F 2 "My-library:SMD-CONN" H 5850 2800 50  0001 C CNN
F 3 "~" H 5800 2950 50  0001 C CNN
	1    5800 2950
	1    0    0    -1  
$EndComp
Wire Wire Line
	5300 2550 5300 2650
Wire Wire Line
	5300 3350 5300 3250
Wire Wire Line
	5700 2950 5600 2950
Wire Wire Line
	5600 2900 5600 2950
Connection ~ 5600 2950
Wire Wire Line
	5600 2950 5600 3000
Wire Wire Line
	5350 2650 5300 2650
Connection ~ 5300 2650
Wire Wire Line
	5300 2650 5250 2650
Wire Wire Line
	5350 3250 5300 3250
Connection ~ 5300 3250
Wire Wire Line
	5300 3250 5250 3250
$EndSCHEMATC
