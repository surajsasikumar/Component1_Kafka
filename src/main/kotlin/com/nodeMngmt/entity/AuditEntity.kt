package com.nodeMngmt.entity

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column
import java.time.Instant
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Serdeable
@Table(name="audits_entity")
data class AuditEntity(

    @Column(name = "tcin")
    val tcin: String,
    @Id
    @Column(name = "attribute_name")
    val attributeName: String,
    @Column(name = "previous_value")
    var previousValue: String?,
    @Column(name = "current_value")
    var currentValue: String,
    @Column(name = "updated_timestamp")
    var updatedTimestamp: Instant
){
    constructor() : this("", "", "", "", Instant.now())
}