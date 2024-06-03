FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://wpa_supplicant-wlan0.conf \
	    file://wpa_supplicant-wlan0.service \
	   "

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "wpa_supplicant-wlan0.service"

FILES_${PN} += "${systemd_unitdir}/system/wpa_supplicant-wlan0.service"

do_install:append () {
   install -d ${D}${sysconfdir}/wpa_supplicant/
   install -D -m 600 ${WORKDIR}/wpa_supplicant-wlan0.conf ${D}${sysconfdir}/wpa_supplicant/

   install -d ${D}${systemd_unitdir}
   install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
   #install ${WORKDIR}/wpa_supplicant-wlan0.service ${D}${sysconfdir}/systemd/system/
   install ${WORKDIR}/wpa_supplicant-wlan0.service ${D}${systemd_unitdir}/system/
   #rm ${systemd_unitdir}/system/wpa_supplicant.service
   #ln -s ${systemd_unitdir}/system/wpa_supplicant-wlan0.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
}
