#!/bin/bash

git submodule init
git submodule sync
git submodule update

source poky/oe-init-build-env rpi-build

MACHINE="raspberrypi4"
MACHINEFILE="../meta-aesd/conf/raspberrypi4.conf"

cat conf/local.conf | grep "${MACHINE}" > /dev/null
local_conf_info=$?

if [ $local_conf_info -ne 0 ];then
	echo "Append contents of ${MACHINEFILE} in the local.conf file"
	cat ${MACHINEFILE} >> conf/local.conf
else
	echo "${MACHINE} is already set"
fi

bitbake-layers add-layer ../meta-raspberrypi
bitbake-layers add-layer ../meta-openembedded/meta-oe
bitbake-layers add-layer ../meta-openembedded/meta-python
bitbake-layers add-layer ../meta-openembedded/meta-networking
bitbake-layers add-layer ../meta-aesd

bitbake-layers show-layers

set -e
bitbake core-image-aesd
