#!/usr/bin/env bash

set -e

SERVER_SSH=tamerlan@cmlteam.com
PORT=8099
JAVA_XMX=700
JAR_NAME="codevscovid19-0.0.1-SNAPSHOT.jar"

mydir=$(cd $(dirname "$0"); pwd)

#echo "$mydir"
echo "Build backend..."

cd "$mydir/backend"
mvn clean package -DskipTests

echo
echo "Deploy..."
echo

scp target/$JAR_NAME $SERVER_SSH:~/covid19hackathon

echo
echo "(Re)start..."
echo

ssh $SERVER_SSH "
set -e
echo 'pkill...'

pkill -9 'sudo lsof -t -i:8099'

echo 'staring...'
nohup java \
    -Xmx${JAVA_XMX}M \
    -Xms${JAVA_XMX}M \
    -jar 'covid19hackathon/$JAR_NAME' 2>&1 >>~/codeVsCivid19.log &
tail -f ~/codeVsCivid19.log
"
