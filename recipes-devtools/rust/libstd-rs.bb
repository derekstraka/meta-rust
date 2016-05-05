SUMMARY = "Rust standard libaries"
HOMEPAGE = "http://www.rust-lang.org"
SECTION = "devel"
LICENSE = "MIT | Apache-2.0"

LIC_FILES_CHKSUM ="file://COPYRIGHT;md5=43e1f1fb9c0ee3af66693d8c4fecafa8"
require rust-shared-source.inc

DEPENDS += "compiler-rt"

RUSTLIB_DEP = ""
inherit rust-bin
inherit cargo_util

export RUSTC_BOOTSTRAP_KEY="e8edd0fd"

B = "${S}/src/rustc/std_shim"

do_install () {
    mkdir -p ${D}${rustlibdir}
    cp ${B}/target/${TARGET_SYS}/release/deps/* ${D}${rustlibdir}
}

FILES_${PN} += "${rustlibdir}/*.so"
FILES_${PN}-dev += "${rustlibdir}/*.rlib"
