package com.github.daggerok

import org.apache.rocketmq.client.producer.DefaultMQProducer
import org.apache.rocketmq.common.message.Message
import java.nio.charset.StandardCharsets.UTF_8

fun String.asMessage() =
    Message(topicName, this.toByteArray(UTF_8))

fun main(args: Array<String>) {
  val producer = DefaultMQProducer(groupId)
  producer.namesrvAddr = nameServerAddress
  producer.start()
  args.forEach {
    producer.send(it.asMessage())
  }
  producer.shutdown()
  println("producing data ${args.toList()} finished.")
}
