#include <jni.h>

extern "C" jstring Java_com_example_ndktest_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF("Hello from C++");
}