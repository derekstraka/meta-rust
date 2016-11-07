export rustlibdir = "${libdir}/rust"
FILES_${PN} += "${rustlibdir}/*.so"
FILES_${PN}-dev += "${rustlibdir}/*.rlib"
FILES_${PN}-dbg += "${rustlibdir}/.debug"

RUSTLIB = ""
RUSTFLAGS += "-C rpath -C ${RUSTLIB}"
RUSTLIB_DEP ?= "libstd-rs"
