#!/bin/zsh

PATH=$PATH:/usr/local/bin/

if ! docker ps > /dev/null 2>&1; then
   echo "Docker not running"
   echo "Starting Docker"
   open -a Docker
fi

# wait for docker started
while ! docker ps >/dev/null 2>&1 ; do
   echo "waiting for Docker start..."
   sleep 1
done
echo "Docker started"


if ! docker ps | grep mysql5 > /dev/null 2>&1; then
  echo "mysql5 not run"
  docker start mysql5
fi
if ! docker ps | grep mysql8 > /dev/null 2>&1; then
  echo "mysql8 not run"
  docker start mysql8
fi

if ! docker ps | grep lb-test > /dev/null 2>&1; then
  echo "lb-test not run"
  docker start lb-test
fi

while ! mycli -P 3304 -u root -p root -e "show databases" > /dev/null 2>&1; do
   echo "waiting for lb-test start..."
   sleep 1
done
echo "stated lb-test"

while ! mycli -P 3305 -u root -p root -e "show databases" > /dev/null 2>&1; do
   echo "waiting for mysql8 start..."
   sleep 1
done
echo "stated mysql8"

while ! mycli -P 3306 -u root -p root -e "show databases" > /dev/null 2>&1; do
   echo "waiting for mysql5 start..."
   sleep 1
done
echo "stated mysql5"


# start nacos
if ! ps aux | grep -v grep | grep nacos > /dev/null 2>&1 ; then
  $HOME/.local/bin/nacos-dir/bin/startup.sh -m standalone > /dev/null 2>&1;
fi

# wait for nacos started
while ! curl localhost:8848/nacos > /dev/null 2>&1 ; do
   echo "waiting for Nacos start..."
   sleep 1
done
echo "stated Nacos"