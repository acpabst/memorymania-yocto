# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/acpabst/memorymania;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
SRCREV = "231c0b01f8a3dcd645c3e659c576e116f9644c76"

DEPENDS += "aesdchar ncurses"

S = "${WORKDIR}/git"

FILES:${PN} += "${bindir}/memorymania"

TARGET_LDFLAGS += "-lncurses"

do_configure () {
        :
}

do_compile () {
        oe_runmake
}

do_install () {
        install -d ${D}${bindir}
        install -m 0755 ${S}/memorymania ${D}${bindir}/
}
