#include <jni.h>

//
// Created by Asus on 12/6/2020.
//

extern "C"
JNIEXPORT jdouble JNICALL
Java_id_ac_ui_cs_mobileprogramming_mrizkyanditama_sportrack_ui_start_1exercise_StartExerciseViewModel_totalCalories(
        JNIEnv *env, jobject thiz, jdouble time_spent, jdouble calories_burned_per_minute) {
    // TODO: implement totalCalories()
    return time_spent * calories_burned_per_minute;
}