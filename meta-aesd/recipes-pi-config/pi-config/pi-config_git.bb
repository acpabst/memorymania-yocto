#   LICENSE
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/acpabst/memorymania.git;protocol=https;branch=main \
                "
PV = "1.0+git${SRCPV}"
SRCREV = "70ff807ff7e72f5d4667d0979bd60e3701ff7ec8"

S = "${WORKDIR}/git/pi-config"

FILES:${PN} += "${prefix}/*"
#FILES:${PN} += "${prefix}/pi-config/wpa_supplicant.conf"

#inherit update-rc.d
#FILES:${PN} += "${sysconfdir}/init.d/pi-config"
#INITSCRIPT_PACKAGES = "${PN}"
#INITSCRIPT_NAME = "pi_config"

do_configure () {
        :
}

do_compile () {
}

do_install () {
        install -d ${D}${prefix}
        install -m 0755 ${S}/wpa_supplicant.conf ${D}${prefix}/pi-config

#        install -d ${D}${sysconfdir}/init.d
#        install -m 0755 ${S}/pi-config ${D}${sysconfdir}/init.d
}

