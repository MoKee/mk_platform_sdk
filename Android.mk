# Copyright (C) 2015-2016 The MoKee Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
LOCAL_PATH := $(call my-dir)

# We have a special case here where we build the library's resources
# independently from its code, so we need to find where the resource
# class source got placed in the course of building the resources.
# Thus, the magic here.
# Also, this module cannot depend directly on the R.java file; if it
# did, the PRIVATE_* vars for R.java wouldn't be guaranteed to be correct.
# Instead, it depends on the R.stamp file, which lists the corresponding
# R.java file as a prerequisite.
mk_platform_res := APPS/org.mokee.platform-res_intermediates/src

# The MoKee Platform Framework Library
# ============================================================
include $(CLEAR_VARS)

mokee_src := src/java/mokee
mokee_internal_src := src/java/org/mokee/internal
library_src := mk/lib/main/java

LOCAL_MODULE := org.mokee.platform
LOCAL_MODULE_TAGS := optional

LOCAL_JAVA_LIBRARIES := \
    services \
    org.mokee.hardware

LOCAL_SRC_FILES := \
    $(call all-java-files-under, $(mokee_src)) \
    $(call all-java-files-under, $(mokee_internal_src)) \
    $(call all-java-files-under, $(library_src))

## READ ME: ########################################################
##
## When updating this list of aidl files, consider if that aidl is
## part of the SDK API.  If it is, also add it to the list below that
## is preprocessed and distributed with the SDK.  This list should
## not contain any aidl files for parcelables, but the one below should
## if you intend for 3rd parties to be able to send those objects
## across process boundaries.
##
## READ ME: ########################################################
LOCAL_SRC_FILES += \
    $(call all-Iaidl-files-under, $(mokee_src)) \
    $(call all-Iaidl-files-under, $(mokee_internal_src))

mkplat_LOCAL_INTERMEDIATE_SOURCES := \
    $(mk_platform_res)/mokee/platform/R.java \
    $(mk_platform_res)/mokee/platform/Manifest.java \
    $(mk_platform_res)/org/mokee/platform/internal/R.java

LOCAL_INTERMEDIATE_SOURCES := \
    $(mkplat_LOCAL_INTERMEDIATE_SOURCES)

# Include aidl files from mokee.app namespace as well as internal src aidl files
LOCAL_AIDL_INCLUDES := $(LOCAL_PATH)/src/java

include $(BUILD_JAVA_LIBRARY)
mk_framework_module := $(LOCAL_INSTALLED_MODULE)

# Make sure that R.java and Manifest.java are built before we build
# the source for this library.
mk_framework_res_R_stamp := \
    $(call intermediates-dir-for,APPS,org.mokee.platform-res,,COMMON)/src/R.stamp
$(full_classes_compiled_jar): $(mk_framework_res_R_stamp)
$(built_dex_intermediate): $(mk_framework_res_R_stamp)

$(mk_framework_module): | $(dir $(mk_framework_module))org.mokee.platform-res.apk

mk_framework_built := $(call java-lib-deps, org.mokee.platform)

# ====  org.mokee.platform.xml lib def  ========================
include $(CLEAR_VARS)

LOCAL_MODULE := org.mokee.platform.xml
LOCAL_MODULE_TAGS := optional

LOCAL_MODULE_CLASS := ETC

# This will install the file in /system/etc/permissions
LOCAL_MODULE_PATH := $(TARGET_OUT_ETC)/permissions

LOCAL_SRC_FILES := $(LOCAL_MODULE)

include $(BUILD_PREBUILT)

# the sdk
# ============================================================
include $(CLEAR_VARS)

LOCAL_MODULE:= org.mokee.platform.sdk
LOCAL_MODULE_TAGS := optional
LOCAL_REQUIRED_MODULES := services

LOCAL_SRC_FILES := \
    $(call all-java-files-under, $(mokee_src)) \
    $(call all-Iaidl-files-under, $(mokee_src)) \
    $(call all-Iaidl-files-under, $(mokee_internal_src))

# Included aidl files from mokee.app namespace
LOCAL_AIDL_INCLUDES := $(LOCAL_PATH)/src/java

mksdk_LOCAL_INTERMEDIATE_SOURCES := \
    $(mk_platform_res)/mokee/platform/R.java \
    $(mk_platform_res)/mokee/platform/Manifest.java

LOCAL_INTERMEDIATE_SOURCES := \
    $(mksdk_LOCAL_INTERMEDIATE_SOURCES)

# Make sure that R.java and Manifest.java are built before we build
# the source for this library.
mk_framework_res_R_stamp := \
    $(call intermediates-dir-for,APPS,org.mokee.platform-res,,COMMON)/src/R.stamp
$(full_classes_compiled_jar): $(mk_framework_res_R_stamp)
$(built_dex_intermediate): $(mk_framework_res_R_stamp)
$(full_target): $(mk_framework_built) $(gen)
include $(BUILD_STATIC_JAVA_LIBRARY)

# the sdk as a jar for publish, not built as part of full target
# DO NOT LINK AGAINST THIS IN BUILD
# ============================================================
include $(CLEAR_VARS)

LOCAL_MODULE:= org.mokee.platform.sdk.jar
LOCAL_MODULE_TAGS := optional
LOCAL_REQUIRED_MODULES := services
LOCAL_JACK_ENABLED := disabled

LOCAL_SRC_FILES := \
    $(call all-java-files-under, $(mokee_src)) \
    $(call all-Iaidl-files-under, $(mokee_src)) \
    $(call all-Iaidl-files-under, $(mokee_internal_src))

# Included aidl files from mokee.app namespace
LOCAL_AIDL_INCLUDES := $(LOCAL_PATH)/src/java

mksdk_LOCAL_INTERMEDIATE_SOURCES := \
    $(mk_platform_res)/mokee/platform/R.java \
    $(mk_platform_res)/mokee/platform/Manifest.java

LOCAL_INTERMEDIATE_SOURCES := \
    $(mksdk_LOCAL_INTERMEDIATE_SOURCES)

include $(BUILD_STATIC_JAVA_LIBRARY)

# full target for use by platform apps
#
include $(CLEAR_VARS)

LOCAL_MODULE:= org.mokee.platform.internal
LOCAL_MODULE_TAGS := optional
LOCAL_REQUIRED_MODULES := services

LOCAL_SRC_FILES := \
    $(call all-java-files-under, $(mokee_src)) \
    $(call all-java-files-under, $(mokee_internal_src)) \
    $(call all-Iaidl-files-under, $(mokee_src)) \
    $(call all-Iaidl-files-under, $(mokee_internal_src))

# Included aidl files from mokee.app namespace
LOCAL_AIDL_INCLUDES := $(LOCAL_PATH)/src/java

mksdk_LOCAL_INTERMEDIATE_SOURCES := \
    $(mk_platform_res)/mokee/platform/R.java \
    $(mk_platform_res)/mokee/platform/Manifest.java \
    $(mk_platform_res)/org/mokee/platform/internal/R.java \
    $(mk_platform_res)/org/mokee/platform/internal/Manifest.java

LOCAL_INTERMEDIATE_SOURCES := \
    $(mksdk_LOCAL_INTERMEDIATE_SOURCES)

$(full_target): $(mk_framework_built) $(gen)
include $(BUILD_STATIC_JAVA_LIBRARY)


# ===========================================================
# Common Droiddoc vars
mkplat_docs_src_files := \
    $(call all-java-files-under, $(mokee_src)) \
    $(call all-html-files-under, $(mokee_src))

mkplat_docs_java_libraries := \
    org.mokee.platform.sdk

# SDK version as defined
mkplat_docs_SDK_VERSION := 1.0

# release version
mkplat_docs_SDK_REL_ID := 5

mkplat_docs_LOCAL_MODULE_CLASS := JAVA_LIBRARIES

mkplat_docs_LOCAL_DROIDDOC_SOURCE_PATH := \
    $(mkplat_docs_src_files)

intermediates.COMMON := $(call intermediates-dir-for,$(LOCAL_MODULE_CLASS), org.mokee.platform.sdk,,COMMON)

# ====  the api stubs and current.xml ===========================
include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
    $(mkplat_docs_src_files)
LOCAL_INTERMEDIATE_SOURCES:= $(mkplat_LOCAL_INTERMEDIATE_SOURCES)
LOCAL_JAVA_LIBRARIES:= $(mkplat_docs_java_libraries)
LOCAL_MODULE_CLASS:= $(mkplat_docs_LOCAL_MODULE_CLASS)
LOCAL_DROIDDOC_SOURCE_PATH:= $(mkplat_docs_LOCAL_DROIDDOC_SOURCE_PATH)
LOCAL_ADDITIONAL_JAVA_DIR:= $(intermediates.COMMON)/src
LOCAL_ADDITIONAL_DEPENDENCIES:= $(mkplat_docs_LOCAL_ADDITIONAL_DEPENDENCIES)

LOCAL_MODULE := mk-api-stubs

LOCAL_DROIDDOC_CUSTOM_TEMPLATE_DIR:= build/tools/droiddoc/templates-sdk

LOCAL_DROIDDOC_OPTIONS:= \
        -stubs $(TARGET_OUT_COMMON_INTERMEDIATES)/JAVA_LIBRARIES/mksdk_stubs_current_intermediates/src \
        -stubpackages mokee.alarmclock:mokee.app:mokee.content:mokee.hardware:mokee.media:mokee.os:mokee.profiles:mokee.providers:mokee.platform:mokee.power:mokee.externalviews \
        -exclude org.mokee.platform.internal \
        -api $(INTERNAL_MK_PLATFORM_API_FILE) \
        -removedApi $(INTERNAL_MK_PLATFORM_REMOVED_API_FILE) \
        -nodocs

LOCAL_UNINSTALLABLE_MODULE := true

include $(BUILD_DROIDDOC)

# $(gen), i.e. framework.aidl, is also needed while building against the current stub.
$(full_target): $(mk_framework_built) $(gen)
$(INTERNAL_MK_PLATFORM_API_FILE): $(full_target)
$(call dist-for-goals,sdk,$(INTERNAL_MK_PLATFORM_API_FILE))

# ====  the system api stubs ===================================
include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
    $(mkplat_docs_src_files)
LOCAL_INTERMEDIATE_SOURCES:= $(mkplat_LOCAL_INTERMEDIATE_SOURCES)
LOCAL_JAVA_LIBRARIES:= $(mkplat_docs_java_libraries)
LOCAL_MODULE_CLASS:= $(mkplat_docs_LOCAL_MODULE_CLASS)
LOCAL_DROIDDOC_SOURCE_PATH:= $(mkplat_docs_LOCAL_DROIDDOC_SOURCE_PATH)
LOCAL_ADDITIONAL_JAVA_DIR:= $(intermediates.COMMON)/src

LOCAL_MODULE := mk-system-api-stubs

LOCAL_DROIDDOC_OPTIONS:=\
        -stubs $(TARGET_OUT_COMMON_INTERMEDIATES)/JAVA_LIBRARIES/mksdk_system_stubs_current_intermediates/src \
        -stubpackages mokee.alarmclock:mokee.app:mokee.content:mokee.hardware:mokee.media:mokee.os:mokee.profiles:mokee.providers:mokee.platform:mokee.power:mokee.externalviews \
        -showAnnotation android.annotation.SystemApi \
        -exclude org.mokee.platform.internal \
        -api $(INTERNAL_MK_PLATFORM_SYSTEM_API_FILE) \
        -removedApi $(INTERNAL_MK_PLATFORM_SYSTEM_REMOVED_API_FILE) \
        -nodocs

LOCAL_DROIDDOC_CUSTOM_TEMPLATE_DIR:= build/tools/droiddoc/templates-sdk

LOCAL_UNINSTALLABLE_MODULE := true

include $(BUILD_DROIDDOC)

# $(gen), i.e. framework.aidl, is also needed while building against the current stub.
$(full_target): $(mk_framework_built) $(gen)
$(INTERNAL_MK_PLATFORM_API_FILE): $(full_target)
$(call dist-for-goals,sdk,$(INTERNAL_MK_PLATFORM_API_FILE))

# Documentation
# ===========================================================
include $(CLEAR_VARS)

LOCAL_MODULE := org.mokee.platform.sdk
LOCAL_INTERMEDIATE_SOURCES:= $(mkplat_LOCAL_INTERMEDIATE_SOURCES)
LOCAL_MODULE_CLASS := JAVA_LIBRARIES
LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(mkplat_docs_src_files)
LOCAL_ADDITONAL_JAVA_DIR := $(intermediates.COMMON)/src

LOCAL_IS_HOST_MODULE := false
LOCAL_ADDITIONAL_DEPENDENCIES := \
    services \
    org.mokee.hardware

LOCAL_JAVA_LIBRARIES := $(mkplat_docs_java_libraries)

LOCAL_DROIDDOC_OPTIONS := \
        -offlinemode \
        -exclude org.mokee.platform.internal \
        -hidePackage org.mokee.platform.internal \
        -hdf android.whichdoc offline \
        -hdf sdk.version $(mkplat_docs_docs_SDK_VERSION) \
        -hdf sdk.rel.id $(mkplat_docs_docs_SDK_REL_ID) \
        -hdf sdk.preview 0 \
        -since $(MK_SRC_API_DIR)/1.txt 1 \
        -since $(MK_SRC_API_DIR)/2.txt 2 \
        -since $(MK_SRC_API_DIR)/3.txt 3 \
        -since $(MK_SRC_API_DIR)/4.txt 4 \
        -since $(MK_SRC_API_DIR)/5.txt 5

$(full_target): $(mk_framework_built) $(gen)
include $(BUILD_DROIDDOC)

include $(call first-makefiles-under,$(LOCAL_PATH))

# Cleanup temp vars
# ===========================================================
mkplat.docs.src_files :=
mkplat.docs.java_libraries :=
intermediates.COMMON :=
