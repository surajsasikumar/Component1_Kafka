package com.nodeMngmt.service

import com.nodeMngmt.Producer
import com.nodeMngmt.entity.AuditEntity
import com.nodeMngmt.entity.Item
import com.nodeMngmt.entity.ItemData
import com.nodeMngmt.repository.AuditRepository
import com.nodeMngmt.repository.ItemRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import jakarta.persistence.Column
import java.time.Instant

@Singleton
@Transactional
class AuditService (
    private val producer: Producer,
    private val auditRepository: AuditRepository,
    private val itemRepository: ItemRepository){


    fun addAudit(auditEntity: AuditEntity):AuditEntity{
        System.out.println("Audit entity "+auditEntity)
       return auditRepository.save(auditEntity)
    }

    fun updateAudit(auditEntity: AuditEntity): AuditEntity {
        val attributeName = auditEntity.attributeName
        val optionalEntity = auditRepository.findById(attributeName)

        if (optionalEntity.isPresent) {
            val existingEntity = optionalEntity.get()
            existingEntity.previousValue = auditEntity.previousValue
            existingEntity.currentValue = auditEntity.currentValue
            existingEntity.updatedTimestamp = auditEntity.updatedTimestamp

            auditRepository.update(existingEntity)
            return existingEntity
        } else {

            auditRepository.save(auditEntity)
            return auditEntity
        }
    }



    fun addItem(item: Item){

        val prev_item= Item()
        System.out.println("lithiumclassified "+item.compliance.lithiumClassified)
        System.out.println("")

        val auditEntity= AuditEntity(prev_item.tcin, "isFlammable", prev_item.compliance.isFlammable.toString(),item.compliance.isFlammable.toString(),
            Instant.now())
        updateAudit(auditEntity)

        val HazmatPublishable= AuditEntity(prev_item.tcin, "isHazmatPublishable", prev_item.compliance.isHazmatPublishable.toString(),item.compliance.isHazmatPublishable.toString(),
                Instant.now())
        updateAudit(HazmatPublishable)


        val LithiunClassified= AuditEntity(prev_item.tcin, "lithiunClassified", prev_item.compliance.lithiumClassified.toString(),item.compliance.lithiumClassified.toString(),
            Instant.now())
        updateAudit(LithiunClassified)

        val Depth= AuditEntity(prev_item.tcin, "Depth", prev_item.packageDimensions.measurements.dimensions.depth.toString(),item.packageDimensions.measurements.dimensions.depth.toString(),
            Instant.now())
        updateAudit(Depth)


        val Height= AuditEntity(prev_item.tcin, "Height", prev_item.packageDimensions.measurements.dimensions.height.toString(),item.packageDimensions.measurements.dimensions.height.toString(),
            Instant.now())
        updateAudit(Height)


        val Units= AuditEntity(prev_item.tcin, "units", prev_item.packageDimensions.measurements.dimensions.units,item.packageDimensions.measurements.dimensions.units,
            Instant.now())
        updateAudit(Units)


        val Width= AuditEntity(prev_item.tcin, "width", prev_item.packageDimensions.measurements.dimensions.width.toString(),item.packageDimensions.measurements.dimensions.width.toString(),
            Instant.now())
        updateAudit(Width)


        val Code= AuditEntity(prev_item.tcin, "code", prev_item.packageDimensions.measurements.weight.code,item.packageDimensions.measurements.weight.code,
            Instant.now())
        updateAudit(Code)


        val Value= AuditEntity(prev_item.tcin, "value", prev_item.packageDimensions.measurements.weight.value.toString(),item.packageDimensions.measurements.weight.value.toString(),
            Instant.now())
        updateAudit(Value)


        val records=producer.publish("data",item)
    }


}

