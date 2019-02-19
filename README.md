# RocketMQ getting started

_start RocketMQ_

```bash
./download-and-start-rocketmq.sh

netstat -aln|grep 9876
```

_build and run consumer_

```bash
./gradlew -DmainClass=com.github.daggerok.ConsumerV1Kt
mv -f ./build/install/rocketmq-example ./build/consumer
bash ./build/consumer/bin/rocketmq-example &
```

_build and run producer_

```bash
./gradlew -DmainClass=com.github.daggerok.ProducerV1Kt
mv -f ./build/install/rocketmq-example ./build/producer
bash ./build/producer/bin/rocketmq-example and 1 and 2 and 3 and four
```

_cleanup_

```bash
killall -9 java
```

links

- [some examples](http://rocketmq.apache.org/docs/simple-example/)
- [quick start](http://rocketmq.apache.org/docs/quick-start/)
