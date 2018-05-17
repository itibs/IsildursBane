#include <jni.h>

extern "C"
JNIEXPORT void JNICALL
Java_com_example_toti_myopenssl_OpenSSLCertificate_X509_1free(JNIEnv *env, jclass type,
                                                              jlong x509ctx) {
    *(int *)(x509ctx) -= 1;
}