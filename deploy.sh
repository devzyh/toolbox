#!/bin/bash
# git
git config --global credential.helper store
git pull origin master

# maven
mvn clean package

# directory
if [ ! -d "build" ]; then
  mkdir build
fi

# stop
echo "Stop Toolbox"
pid=$(cat build/toolbox.pid)
kill -9 $pid
echo "Stop Success"

# deploy
rm -rf build/toolbox.jar
cp target/toolbox-*.jar build/toolbox.jar

# start
cd build
echo "Start Toolbox"
nohup java -jar toolbox.jar > run.log &
echo $! > toolbox.pid
echo "Start Success"
cd ..
