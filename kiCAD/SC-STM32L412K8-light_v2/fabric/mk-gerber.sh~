#!/bin/bash

if [ -f Meteostation.zip ]; then
	rm Meteostation.zip
fi
mv Meteostation-B_Cu.gbr Bottom.gbr
mv Meteostation-B_Mask.gbr MaskBottom.gbr
if [ -f Meteostation-PTH.drl ]; then
	mv Meteostation-PTH.drl NCData.drl
else
	mv Meteostation.drl NCData.drl
fi
mv Meteostation-F_Cu.gbr Top.gbr
mv Meteostation-F_Mask.gbr MaskTop.gbr
mv Meteostation-Edge_Cuts.gbr Border.gbr
zip Meteostation.zip Border.gbr Bottom.gbr MaskBottom.gbr MaskTop.gbr NCData.drl Top.gbr
