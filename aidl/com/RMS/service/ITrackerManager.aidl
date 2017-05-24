// ITrackerManager.aidl
package com.RMS.service;

import java.util.List;

// Declare any non-default types here with import statements

interface ITrackerManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void setPackageName(in String packageName);
     void addValue(in String jsonArray);
}
