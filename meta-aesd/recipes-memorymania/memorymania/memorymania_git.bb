# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/acpabst/memorymania;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
SRCREV = "633547cb43a9f291516d90514f51bc3bc33f4579"

DEPENDS += "aesdchar"

S = "${WORKDIR}/git"

FILES:${PN} += "${bindir}/memorymania"

#TARGET_LDFLAGS += "-pthread -lrt"

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
