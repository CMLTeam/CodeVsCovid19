#!/usr/bin/env bash

set -ex;

# remove old resources
rm -rf ./build/
ssh tamerlan@cmlteam.com 'rm -rf covid_backoffice/build/*'

# build and copy resources
npm i
npm run-script build
scp -r ./build/ tamerlan@cmlteam.com:./covid_backoffice

# start frontend
ssh tamerlan@cmlteam.com '

PORT=$(sudo lsof -t -i:8098);

if [[ $PORT ]]
then
sudo kill -9 $PORT;
fi;

serve -s -n -l 8098 ./covid_backoffice/build/ &>/dev/null &

' &
