package com.nodeMngmt

import com.nodeMngmt.entity.Item
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
class ProductListener {

    @Topic("component1")
    fun receive(@KafkaKey brand:String,name:Item) {
        System.out.println(name)
//        System.out.println("Received response - compliance : "+ name.compliance )
//        System.out.println("Received response - tcin : "+ name.tcin )
//        System.out.println("Received response - packageDimensions : "+ name.packageDimensions )
//        System.out.println("Received response - measurements : "+ name.packageDimensions.measurements )
//        System.out.println("Received response - dimensions : "+ name.packageDimensions.measurements.dimensions )
    }
}