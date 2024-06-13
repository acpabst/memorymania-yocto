#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/cu-ecen-aeld/assignments-3-and-later-acpabst.git;protocol=https;branch=master \
          	file://aesdchar_init \ 
	  	"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "16006ca6696477ef21e1954a60a3a9b349b4430b"

S = "${WORKDIR}/git/aesd-char-driver"

inherit module
MODULES_INSTALL_TARGET = "install"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR}"

inherit update-rc.d
FILES:${PN} += "${bindir}/aesdchar_load ${bindir}/aesdchar_unload"
FILES:${PN} += "${sysconfdir}/init.d/aesdchar_init"
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME = "aesdchar_init"

#inherit autotools
FILES:${PN} += "home/flush_test/flush_test /home/flush_test/run_flush.sh"

do_configure () {
        :
}

do_compile () {
        oe_runmake
}

do_install () {
        install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/
        install -m 0755 ${S}/aesdchar.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/

	install -d ${D}${bindir}
	install -m 0755 ${S}/aesdchar_load ${D}${bindir}/
	install -m 0755 ${S}/aesdchar_unload ${D}${bindir}/

        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${WORKDIR}/aesdchar_init ${D}${sysconfdir}/init.d

	# install test files
	install -d ${D}/home/flush_test/
	install -m 0755 ${S}/flush_test ${D}/home/flush_test/
	install -m 0755 ${S}/run_flush.sh ${D}/home/flush_test/
}
