#!/usr/bin/env bash
export ROCKETMQ_VERSION="4.4.0"
export ROCKETMQ_NAME="rocketmq-all-${ROCKETMQ_VERSION}-bin-release"
export ROCKETMQ_HOME="/tmp/${ROCKETMQ_NAME}"
if ! [[ -d "${ROCKETMQ_HOME}" ]]; then
  wget -qO- "https://www-eu.apache.org/dist/rocketmq/${ROCKETMQ_VERSION}/${ROCKETMQ_NAME}.zip" | tar -xzv
  #curl -qSs "https://www-eu.apache.org/dist/rocketmq/${ROCKETMQ_VERSION}/${ROCKETMQ_NAME}.zip" | tar -xzv
  mv ${ROCKETMQ_NAME} /tmp/
fi
echo "Start Name Server"
${ROCKETMQ_HOME}/bin/mqnamesrv &
mkdir -p ~/logs/rocketmqlogs/
touch ~/logs/rocketmqlogs/namesrv.log
tail -f ~/logs/rocketmqlogs/namesrv.log &
sleep 6s
echo "Start Broker"
${ROCKETMQ_HOME}/bin/mqbroker -n localhost:9876 &
mkdir -p ~/logs/rocketmqlogs/
touch ~/logs/rocketmqlogs/broker.log
tail -f ~/logs/rocketmqlogs/broker.log &
sleep 10s

## test
#export NAMESRV_ADDR=localhost:9876
#${ROCKETMQ_HOME}/bin/tools.sh org.apache.rocketmq.example.quickstart.Producer
#${ROCKETMQ_HOME}/bin/tools.sh org.apache.rocketmq.example.quickstart.Consumer

## shutdown
#${ROCKETMQ_HOME}/bin/mqshutdown broker
#${ROCKETMQ_HOME}/bin/mqshutdown namesrv
