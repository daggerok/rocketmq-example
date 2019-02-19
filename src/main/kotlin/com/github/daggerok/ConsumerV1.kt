package com.github.daggerok

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus.*
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly
import org.apache.rocketmq.common.message.MessageExt
import java.nio.charset.StandardCharsets.UTF_8
import java.util.concurrent.TimeUnit

fun main() {
  val consumer = DefaultMQPushConsumer(groupId)
  consumer.namesrvAddr = "127.0.0.1:9876"
  consumer.subscribe(topicName, "*")
  consumer.registerMessageListener(messageListener)
  consumer.start()
  TimeUnit.SECONDS.sleep(5)
  println("consuming data finished.")
  consumer.shutdown()
}

object messageListener : MessageListenerOrderly {
  override fun consumeMessage(msgs: MutableList<MessageExt>?, context: ConsumeOrderlyContext?): ConsumeOrderlyStatus {
    try {
      msgs?.forEach {
        println("handle message: ${String(it.body, UTF_8)}")
      } ?: return SUSPEND_CURRENT_QUEUE_A_MOMENT
      println("handling messages done.")
      return SUCCESS
    }
    catch (e: Throwable) { return SUSPEND_CURRENT_QUEUE_A_MOMENT }
  }
}
