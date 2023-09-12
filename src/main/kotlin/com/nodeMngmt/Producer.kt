package com.nodeMngmt

import com.nodeMngmt.entity.Item
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic
import org.apache.kafka.clients.producer.RecordMetadata
@KafkaClient
interface Producer {
    @Topic("component1")
    fun publish(@KafkaKey brand:String,name:Item) :RecordMetadata
}