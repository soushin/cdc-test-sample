#! /bin/bash

DOCKER_COMPOSE_COMMAND=`which docker-compose`

if [ ! -x $DOCKER_COMPOSE_COMMAND ]; then
  echo "error, should install docker-compose" >&2
  exit -1
fi

if [ -z "$1" ]; then
  echo "USAGE: ./setup.sh <MACKEREL_API_KEY>" >&2
  exit -1
fi

sed -i.org -e "s/{ENV_MACKEREL_API_KEY}/$1/" ./pact-broker-host/docker-compose.yml
rm ./pact-broker-host/docker-compose.yml.org

$DOCKER_COMPOSE_COMMAND build --no-cache
$DOCKER_COMPOSE_COMMAND up -d