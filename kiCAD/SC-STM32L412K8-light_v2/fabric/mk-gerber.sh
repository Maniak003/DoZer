#!/bin/bash

if [ -f Meteostation.zip ]; then
	rm Meteostation.zip
fi
mv SC-STM32L412K8-B_Cu.gbr Bottom.gbr
mv SC-STM32L412K8-B_Mask.gbr MaskBottom.gbr
if [ -f SC-STM32L412K8-PTH.drl ]; then
	mv SC-STM32L412K8.drl NCData.drl
else
	mv SC-STM32L412K8.drl NCData.drl
fi
mv SC-STM32L412K8-F_Cu.gbr Top.gbr
mv SC-STM32L412K8-F_Mask.gbr MaskTop.gbr
mv SC-STM32L412K8-Edge_Cuts.gbr Border.gbr
zip SC-STM32L412K8.zip Border.gbr Bottom.gbr MaskBottom.gbr MaskTop.gbr NCData.drl Top.gbr

