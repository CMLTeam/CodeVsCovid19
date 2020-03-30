#!/usr/bin/env bash

set -ex;

SERVER_SSH=tamerlan@cmlteam.com

# remove old resources
rm -rf ./build/
ssh $SERVER_SSH '
rm -rf covid_backoffice/build/*

mkdir -p covid_backoffice
'

# build and copy resources
npm i
npm run-script build
scp -r ./build/ $SERVER_SSH:./covid_backoffice

# start frontend
ssh $SERVER_SSH '

PORT=$(sudo lsof -t -i:8098);

if [[ $PORT ]]
then
sudo kill -9 $PORT;
fi;

serve -s -n -l 8098 ./covid_backoffice/build/ &>/dev/null &

' &
