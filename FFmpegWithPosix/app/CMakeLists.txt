cmake_minimum_required(VERSION 3.4.1)

#查找系统库 liblog.so
find_library( log-lib
              log )

find_library( android-lib
              android )


#变量
set(distribution_DIR ../../../../libs)

add_library( avutil-54
             SHARED
             IMPORTED )
set_target_properties( avutil-54
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libavutil-54.so )

add_library( swresample-1
             SHARED
             IMPORTED )
set_target_properties( swresample-1
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libswresample-1.so )
add_library( avcodec-56
             SHARED
             IMPORTED )
set_target_properties( avcodec-56
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libavcodec-56.so )
add_library( avfilter-5
             SHARED
             IMPORTED)
set_target_properties( avfilter-5
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libavfilter-5.so )
add_library( swscale-3
             SHARED
             IMPORTED)
set_target_properties( swscale-3
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libswscale-3.so )
add_library( avdevice-56
             SHARED
             IMPORTED)
set_target_properties( avdevice-56
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libavdevice-56.so )
add_library( avformat-56
             SHARED
             IMPORTED)
set_target_properties( avformat-56
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libavformat-56.so )
add_library( yuv
             SHARED
             IMPORTED)
set_target_properties( yuv
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libyuv.so )


#C++11标准
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

add_library( jing_player
             SHARED
             src/main/cpp/util/queue.c
             src/main/cpp/jing_player.c)

#添加ljnigraphics库
#target_link_libraries(jing_player log android -ljnigraphics)


#预编译库中引用文件夹中的提示
include_directories(src/main/cpp/include/ffmpeg)
include_directories(src/main/cpp/include/libyuv)

target_link_libraries( jing_player
                       avutil-54
                       swresample-1
                       avcodec-56
                       avfilter-5
                       swscale-3
                       avdevice-56
                       avformat-56
                       yuv
                       ${android-lib}
                       ${log-lib} )