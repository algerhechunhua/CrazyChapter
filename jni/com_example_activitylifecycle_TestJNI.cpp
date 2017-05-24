#include <stdio.h>
#include <stdlib.h>
#include "com_example_activitylifecycle_TestJNI.h"
#include "Add.h"
Add *pCAdd = NULL;
JNIEXPORT jboolean JNICALL Java_com_example_activitylifecycle_TestJNI_Init(JNIEnv *env,jobject obj) {   
   if (pCAdd == NULL) {
        pCAdd = new CAdd;
    }    
   return pCAdd != NULL;
}
JNIEXPORT jint JNICALL Java_com_example_activitylifecycle_TestJNI_Add(JNIEnv *env, jobject obj,
        jint x, jint y) {    
        int res = -1;    
       if (pCAdd != NULL) {
          res = pCAdd->Add(x, y);
        }    
       return res;
}
JNIEXPORT void JNICALL Java_com_example_activitylifecycle_TestJNI_Destory(JNIEnv *env, jobject obj)
{    if (pCAdd != NULL)
    {
        pCAdd = NULL;
    }
}