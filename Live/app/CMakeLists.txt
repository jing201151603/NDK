cmake_minimum_required(VERSION 3.4.1)

#查找系统库 liblog.so
find_library( log-lib
              log )

find_library( android-lib
              android )


#变量
set(distribution_DIR ../../../../libs)


add_library(faac STATIC IMPORTED)
set_target_properties(faac
                      PROPERTIES IMPORTED_LOCATION
                      ${distribution_DIR}/${ANDROID_ABI}/libfaac.a)

add_library(rtmp STATIC IMPORTED)
set_target_properties(rtmp
                      PROPERTIES IMPORTED_LOCATION
                      ${distribution_DIR}/${ANDROID_ABI}/librtmp.a)

add_library(x264 STATIC IMPORTED)
set_target_properties(x264
                      PROPERTIES IMPORTED_LOCATION
                      ${distribution_DIR}/${ANDROID_ABI}/libx264.a)



#C++11标准
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

add_library( jing_live
             SHARED
             src/main/cpp/queue.c
             src/main/cpp/jing_live.c)

#添加ljnigraphics库
#target_link_libraries(jing_live log android -ljnigraphics)


#预编译库中引用文件夹中的提示
include_directories(src/main/cpp/faac/include)
include_directories(src/main/cpp/rtmpdump/include)
include_directories(src/main/cpp/x264/include)

target_link_libraries( jing_live
                       faac
                       rtmp
                       x264
                       ${android-lib}
                       ${log-lib} )