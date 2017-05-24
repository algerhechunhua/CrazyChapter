LOCAL_PATH:= $(call my-dir)

vPrimaryCode := 1
vSubCode := 0
vReleaseCode := 0
vCodeDate=$(shell date +%Y%m%d)
versioncode := 1
versionname := ${vPrimaryCode}.${vSubCode}.${vReleaseCode}.${vCodeDate}

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, src)
LOCAL_SRC_FILES += aidl/com/lewa/crazychapter11/ICat.aidl
LOCAL_SRC_FILES += aidl/com/RMS/service/ITrackerManager.aidl

#		   src/com/lewa/crazychapter11/ITelephony1.aidl \
#		   src/com/lewa/crazychapter11/NeighboringCellInfo1.aidl	

#LOCAL_JAVA_LIBRARIES += mediatek-framework
LOCAL_JAVA_LIBRARIES += telephony-common

LOCAL_STATIC_JAVA_LIBRARIES := \
    android-support-v13 \
    android-support-v4 \
    lewa-support-v7-appcompat \
    com.lewa.themes \
    mysql-connector \
    mytest

LOCAL_RESOURCE_DIR = \
    $(LOCAL_PATH)/res \
    vendor/lewa/apps/LewaSupportLib/actionbar_4.4/res \

LOCAL_AAPT_FLAGS := \
        --auto-add-overlay \
#        --extra-packages lewa.support.v7.appcompat


LOCAL_AAPT_FLAGS += \
    --version-code $(versioncode) \
    --version-name $(versionname)        

LOCAL_REQUIRED_MODULES := libvariablespeed

LOCAL_PACKAGE_NAME := crazychapter11
LOCAL_CERTIFICATE := platform

#LOCAL_PROGUARD_FLAG_FILES := proguard.flags

include $(BUILD_PACKAGE)

# Use the folloing include to make our test apk.
include $(call all-makefiles-under,$(LOCAL_PATH))

include $(CLEAR_VARS)
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := mysql-connector:libs/mysql-connector-java-5.1.22-bin.jar mytest:libs/mytest.jar

include $(BUILD_MULTI_PREBUILT)
