package com.nodeMngmt.controller

import com.nodeMngmt.entity.AuditEntity
import com.nodeMngmt.entity.Item
import com.nodeMngmt.service.AuditService
import io.micronaut.http.annotation.*

@Controller("/component1")
class AuditController(private val auditService: AuditService) {

    @Post("/add")
    fun addAudit(auditEntity: AuditEntity):AuditEntity{
        return auditService.addAudit(auditEntity);
    }

    @Put("/update")
    fun updateAudit(auditEntity: AuditEntity):AuditEntity{
        return auditService.updateAudit(auditEntity);
    }

    @Post("/add/item")
    fun addItem(item: Item){
        auditService.addItem(item);
    }
}


