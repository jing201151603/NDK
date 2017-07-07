#include "inc/fmod.hpp"
#include <string>
#include "com_jing_fmodchangevoice_EffectUtils.h"
#include <unistd.h>

#define MODE_NORMAL 0
#define MODE_LUOLI  1
#define MODE_DASHU  2
#define MODE_JINGSONG 3
#define MODE_GAOGUAI  4
#define MODE_KONGLING  5

using namespace FMOD;

JNIEXPORT void JNICALL Java_com_jing_fmodchangevoice_EffectUtils_fix
        (JNIEnv *env, jclass cls, jstring path_jstr, jint type) {

    Sound *sound;
    Channel *channel;
    bool playinng = true;
    DSP *dsp;
    float frequency = 0;

    System *system;
    System_Create(&system);

    system->init(30, FMOD_INIT_NORMAL, 0);

    const char *path_cstr = env->GetStringUTFChars(path_jstr, NULL);
//    try {


    system->createSound(path_cstr, FMOD_DEFAULT, NULL, &sound);

    switch (type) {
        case MODE_NORMAL://正常

            system->playSound(sound, 0, false, &channel);
            break;
        case MODE_LUOLI://萝莉

            //dsp:提升或者降低音调用的一种音效
            system->createDSPByType(FMOD_DSP_TYPE_PITCHSHIFT, &dsp);
            //设置音调的参数,音调提高一个八度
            dsp->setParameterFloat(FMOD_DSP_PITCHSHIFT_PITCH, 2.0);
            system->playSound(sound, 0, false, &channel);
            //添加进去,在音轨(channel)中添加音效(dsp)
            channel->addDSP(0, dsp);
            break;
        case MODE_DASHU://大叔

            //dsp:提升或者降低音调用的一种音效
            system->createDSPByType(FMOD_DSP_TYPE_PITCHSHIFT, &dsp);
            //设置音调的参数,音调降低0.2
            dsp->setParameterFloat(FMOD_DSP_PITCHSHIFT_PITCH, 0.8);
            system->playSound(sound, 0, false, &channel);
            //添加进去,在音轨(channel)中添加音效(dsp)
            channel->addDSP(0, dsp);
            break;
        case MODE_JINGSONG://惊悚

            system->createDSPByType(FMOD_DSP_TYPE_TREMOLO, &dsp);//紧张
            dsp->setParameterFloat(FMOD_DSP_TREMOLO_SKEW, 0.5);
            system->playSound(sound, 0, false, &channel);
            //添加进去,在音轨(channel)中添加音效(dsp)
            channel->addDSP(0, dsp);
            break;
        case MODE_GAOGUAI://搞怪

            system->playSound(sound, 0, false, &channel);
            channel->getFrequency(&frequency);
            frequency = frequency * 1.6;
            channel->setFrequency(frequency);
            break;
        case MODE_KONGLING://空灵

            system->createDSPByType(FMOD_DSP_TYPE_PITCHSHIFT, &dsp);
            dsp->setParameterFloat(FMOD_DSP_ECHO_DELAY, 300);//设置回音的延迟时间
            dsp->setParameterFloat(FMOD_DSP_ECHO_FEEDBACK, 20);//
            system->playSound(sound, 0, false, &channel);
            channel->addDSP(0, dsp);
            break;

        default:

            break;
    }
//    } catch (...) {
//        goto end;
//        env->ThrowNew()//给Java抛异常
//    }

    system->update();

    //休眠
    usleep(50 * 1000 * 1000);
//    while (playinng) {
//
//        channel->isPlaying(&playinng);
//        usleep(1000 * 1000);//每秒钟判断一次是否在播放
//    }
//    goto end;
//
//    end:
    env->ReleaseStringUTFChars(path_jstr, path_cstr);
    sound->release();
    system->close();
    system->release();

}

