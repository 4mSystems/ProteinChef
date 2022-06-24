//
// Created by t-e-s on ١٦‏/٦‏/٢٠٢٢.
//

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_app_te_protein_1chef_data_remote_Keys_debugBaseUrl(JNIEnv *env, jobject thiz) {
    std::string debugBaseUrl = "https://protein-chef.net/api/";
    return env->NewStringUTF(debugBaseUrl.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_app_te_protein_1chef_data_remote_Keys_releaseBaseUrl(JNIEnv *env, jobject thiz) {
    std::string releaseBaseUrl = "https://protein-chef.net/api/";
    return env->NewStringUTF(releaseBaseUrl.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_app_te_protein_1chef_data_remote_Keys_serverClientId(JNIEnv *env, jobject thiz) {
    std::string serverClientId = "654641907466-q9fljq7lumaci6cf0nilsp3d03m23osq.apps.googleusercontent.com";
    return env->NewStringUTF(serverClientId.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_app_te_protein_1chef_data_remote_Keys_mapKey(JNIEnv *env, jobject thiz) {
    std::string MAPS_API_KEY = "AIzaSyABbKL-v-jkzKgpidYbI1dTIrldfin8kJ0";
    return env->NewStringUTF(MAPS_API_KEY.c_str());
}

