package com.nodeMngmt.service

import com.nodeMngmt.Producer
import com.nodeMngmt.entity.AuditEntity
import com.nodeMngmt.entity.Item
import com.nodeMngmt.repository.AuditRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.time.Instant

@Singleton
@Transactional
class AuditService (
    private val producer: Producer,
    private val auditRepository: AuditRepository,
    ){
        fun addItem(item: Item){
    
            val prevItem= Item()
    
            val auditEntity= AuditEntity(prevItem.tcin, "isFlammable", prevItem.compliance.isFlammable.toString(),item.compliance.isFlammable.toString(),
                Instant.now())
            updateAudit(auditEntity)
    
            val hazmatPublishable= AuditEntity(prevItem.tcin, "isHazmatPublishable", prevItem.compliance.isHazmatPublishable.toString(),item.compliance.isHazmatPublishable.toString(),
                    Instant.now())
            updateAudit(hazmatPublishable)
    
    
            val lithiunClassified= AuditEntity(prevItem.tcin, "lithiunClassified", prevItem.compliance.lithiumClassified.toString(),item.compliance.lithiumClassified.toString(),
                Instant.now())
            updateAudit(lithiunClassified)
    
            val depth= AuditEntity(prevItem.tcin, "Depth", prevItem.packageDimensions.measurements.dimensions.depth.toString(),item.packageDimensions.measurements.dimensions.depth.toString(),
                Instant.now())
            updateAudit(depth)
    
    
            val height= AuditEntity(prevItem.tcin, "Height", prevItem.packageDimensions.measurements.dimensions.height.toString(),item.packageDimensions.measurements.dimensions.height.toString(),
                Instant.now())
            updateAudit(height)
    
    
            val units= AuditEntity(prevItem.tcin, "units", prevItem.packageDimensions.measurements.dimensions.units,item.packageDimensions.measurements.dimensions.units,
                Instant.now())
            updateAudit(units)
    
    
            val width= AuditEntity(prevItem.tcin, "width", prevItem.packageDimensions.measurements.dimensions.width.toString(),item.packageDimensions.measurements.dimensions.width.toString(),
                Instant.now())
            updateAudit(width)
    
    
            val code= AuditEntity(prevItem.tcin, "code", prevItem.packageDimensions.measurements.weight.code,item.packageDimensions.measurements.weight.code,
                Instant.now())
            updateAudit(code)
    
    
            val value= AuditEntity(prevItem.tcin, "value", prevItem.packageDimensions.measurements.weight.value.toString(),item.packageDimensions.measurements.weight.value.toString(),
                Instant.now())
            updateAudit(value)
            
           producer.publish("data",item)
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
            } else {
                auditRepository.save(auditEntity)
            }
            return auditEntity
        }
}

