
package com.lewa.crazychapter11;

import android.os.SystemProperties;

public class FeatureOption {
    public static final boolean LEWA_FEATURE_TEST_SUPPORT = true;//getValue("ro.mtk_wapi_support");
    
    // Important!!!  the SystemProperties key's length must less than 31 , or will have JE
    /* get the key's value*/
    private static boolean getValue(String key) {
        return SystemProperties.get(key).equals("1");
    }
}

