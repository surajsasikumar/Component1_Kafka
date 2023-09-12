package com.nodeMngmt.repository
import com.nodeMngmt.entity.AuditEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface AuditRepository : CrudRepository<AuditEntity, String> {

}