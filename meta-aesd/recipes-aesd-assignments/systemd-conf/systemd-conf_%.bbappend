# Used to set up wifi connectivity on the raspberry pi
# Created based on the following tutorial
# https://hub.mender.io/t/how-to-configure-networking-using-systemd-in-yocto-project/1097

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://wlan.network \
"

FILES:${PN} += " \
    ${sysconfdir}/systemd/network/wlan.network \
"

do_install:append() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/wlan.network ${D}${sysconfdir}/systemd/network
}
