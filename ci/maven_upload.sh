#!/bin/bash

# 若项目在仓库多级目录下，则需进入项目根目录内，请反注释以下两行命令
#cd ./xxx/xxx
#echo "sdk.dir=/usr/lib/android-sdk" > local.properties

#默认为alps_dev分支，若为特殊分支，请反注释以下命令，并指定目标分支
#git checkout com.alps_feature_xxxx

#./gradlew clean --stacktrace

#自行指定构建task，格式：./gradle {模块名}:{Task名}，eg:
#./gradlew mapkit:publishReleasePublicationToMavenRepository