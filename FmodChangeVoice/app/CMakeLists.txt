cmake_minimum_required(VERSION 3.4.1)

#查找系统库 liblog.so
find_library( log-lib
              log )

#变量
set(distribution_DIR ../../../../libs)


add_library( fmod
             SHARED
             IMPORTED)

set_target_properties( fmod
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libfmod.so)

add_library( fmodL
             SHARED
             IMPORTED)

set_target_properties( fmodL
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libfmodL.so)


#C++11标准
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

add_library( effect_fix
             SHARED
             src/main/cpp/effect_fix.cpp)

#预编译库中引用文件夹中的提示
include_directories(src/main/cpp/inc)


target_link_libraries( effect_fix
                       fmod
                       fmodL
                       ${log-lib}
                        )