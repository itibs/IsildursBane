#include <jni.h>
#include <string>

typedef int func_ptr_t(void);

int f_malicious()
{
    return -1;
}

int f_benign()
{
    return 1;
}

func_ptr_t *p = f_benign;
func_ptr_t *q = f_malicious;

extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_toti_isildursbane_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    int res = p();
    std::string hello = "Func result: " + std::to_string(res);
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_toti_isildursbane_MainActivity_decrement(JNIEnv *env, jobject instance,
                                                          jlong ptr) {
    *(int *)(ptr) -= 1;
}

extern "C"
JNIEXPORT jlong JNICALL
Java_com_example_toti_isildursbane_MainActivity_getPtrAddress(JNIEnv *env, jobject instance) {
    return (long)&p;
}

extern "C"
JNIEXPORT jlong JNICALL
Java_com_example_toti_isildursbane_MainActivity_getPtrValue(JNIEnv *env, jobject instance) {
    return (long)p;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_toti_isildursbane_MainActivity_callFunc(JNIEnv *env, jobject instance) {
    int res = p();
    return res;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_toti_isildursbane_MainActivity_dummy(JNIEnv *env, jobject instance) {
    f_malicious();
}

extern "C"
JNIEXPORT jlong JNICALL
Java_com_example_toti_isildursbane_MainActivity_getMalPtrAddress(JNIEnv *env, jobject instance) {
    return (long)q;
}