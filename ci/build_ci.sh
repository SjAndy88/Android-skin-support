#!/bin/bash
## 打包编译命令
#./gradlew clean --stacktrace
#./gradlew app:assembleNormalRelease --stacktrace
#if [ $? -ne 0 ]; then
#  exit 1
#fi
#
## 获取当前日期时间戳
#time=$(date "+%Y%m%d")
#
## 输出release包到 ./build/outputs 目录下
#outputDir="./build/outputs"
#rm -rf $outputDir
#mkdir -p $outputDir
#cp app/build/outputs/apk/normal/release/app-normal-release.apk "$outputDir/AlpsMapHelper_$time.apk"
#
## 输出mapkit aar
#./gradlew mapkit:assembleRelease
#if [ $? -ne 0 ]; then
#  exit 1
#fi
#cp mapkit/build/outputs/aar/mapkit-release.aar "$outputDir/Mapkit-SDK-release.aar"
#
## 需要脚本上传aar，打开下面
##./gradlew mapkit:publishReleasePublicationToMavenRepository