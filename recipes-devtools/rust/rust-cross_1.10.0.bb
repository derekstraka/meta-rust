require rust.inc
inherit cross

SRC_URI[rust.md5sum] = "a48fef30353fc9daa70b484b690ce5db"
SRC_URI[rust.sha256sum] = "a4015aacf4f6d8a8239253c4da46e7abaa8584f8214d1828d2ff0a8f56176869"

# Otherwise we'll depend on what we provide
INHIBIT_DEFAULT_RUST_DEPS = "1"

# Unlike native (which nicely maps it's DEPENDS) cross wipes them out completely.
# Generally, we (and cross in general) need the same things that native needs,
# so it might make sense to take it's mapping. For now, though, we just mention
# the bits we need explicitly.
DEPENDS += "rust-llvm-native"
DEPENDS += "virtual/${TARGET_PREFIX}gcc virtual/${TARGET_PREFIX}compilerlibs virtual/libc"
DEPENDS += "rust-native ${RUSTLIB_DEP}"

PROVIDES = "virtual/${TARGET_PREFIX}rustc"
PN = "rust-cross-${TARGET_ARCH}"

# In the cross compilation case, rustc doesn't seem to get the rpath quite
# right. It manages to include '../../lib/${TARGET_PREFIX}', but doesn't
# include the '../../lib' (ie: relative path from cross_bindir to normal
# libdir. As a result, we end up not being able to properly reference files in normal ${libdir}.
# Most of the time this happens to work fine as the systems libraries are
# subsituted, but sometimes a host system will lack a library, or the right
# version of a library (libtinfo was how I noticed this).
#
# FIXME: this should really be fixed in rust itself.
# FIXME: using hard-coded relative paths is wrong, we should ask bitbake for
#        the relative path between 2 of it's vars.
HOST_POST_LINK_ARGS_append = " -Wl,-rpath=../../lib"
BUILD_POST_LINK_ARGS_append = " -Wl,-rpath=../../lib"

# We need the same thing for the calls to the compiler when building the runtime crap
TARGET_CC_ARCH_append = " --sysroot=${STAGING_DIR_TARGET}"

do_configure () {
}

do_compile () {
}

do_install () {
	mkdir -p ${D}${prefix}/${base_libdir_native}/rustlib
	cp ${WORKDIR}/targets/${TARGET_SYS}.json ${D}${prefix}/${base_libdir_native}/rustlib
}

rust_cross_sysroot_preprocess() {
    sysroot_stage_dir ${D}${prefix}/${base_libdir_native}/rustlib ${SYSROOT_DESTDIR}${prefix}/${base_libdir_native}/rustlib
}
SYSROOT_PREPROCESS_FUNCS += "rust_cross_sysroot_preprocess"
