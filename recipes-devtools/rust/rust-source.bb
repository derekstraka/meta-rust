# In order to share the same source between multiple packages (.bb files), we
# unpack and patch the rustc source here into a shared dir.
#
# Take a look at gcc-source.inc for the general structure of this

inherit shared-source-provide

require rust-version.inc
require rust-release.inc

SRC_URI[rust.md5sum] = "a48fef30353fc9daa70b484b690ce5db"
SRC_URI[rust.sha256sum] = "a4015aacf4f6d8a8239253c4da46e7abaa8584f8214d1828d2ff0a8f56176869"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=eb87dba71cb424233bcce88db3ae2f1a"

SRC_URI_append = "\
        file://rust/0001-Add-config-for-musl-based-arm-builds.patch \
        file://rust/0002-Target-add-default-target.json-path-libdir-rust-targ.patch \
        file://rust/0003-mk-for-stage0-use-RUSTFLAGS-to-override-target-libs-.patch \
        file://rust/0004-mk-add-missing-CFG_LIBDIR_RELATIVE.patch \
        file://rust/0005-configure-support-bindir-and-extend-libdir-to-non-bl.patch \
        file://rust/0006-std-thread_local-workaround-for-NULL-__dso_handle.patch \
        file://rust/0007-mk-install-use-disable-rewrite-paths.patch \
        file://rust/0008-Allow-overriding-crate_hash-with-C-crate_hash.patch \
        file://rust/0009-mk-platform.mk-pass-C-crate_hash-to-builds.patch \
        file://rust/0010-mk-allow-changing-the-platform-configuration-source-.patch \
        file://rust/0011-Get-rid-of-the-.note-interpretation-of-rustc-dylib-m.patch \
        file://rust-llvm/0001-Don-t-build-unittests.patch;patchdir=src/llvm \
        file://rust-installer/0001-add-option-to-disable-rewriting-of-install-paths.patch;patchdir=src/rust-installer \
"
