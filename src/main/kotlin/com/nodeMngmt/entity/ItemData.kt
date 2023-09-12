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
@Table(name="item_data")
data class ItemData(
    @Id
    @Column(name = "tcin")
    val tcin: String,
    @Column(name = "is_flammable")
    val isFlammable: Boolean,
    @Column(name = "is_hazmat_publishable")
    val isHazmatPublishable: Boolean,
    @Column(name = "lithium_classified")
    val lithiumClassified: Boolean,
    @Column(name = "code")
    val code: String,
    @Column(name = "value")
    val value: Double,
    @Column(name = "depth")
    val depth: Double,
    @Column(name = "height")
    val height: Double,
    @Column(name = "units")
    val units: String,
    @Column(name = "width")
    val width: Double
){
    constructor() : this("11010145", true, true, false, "POUND",0.4,4.69,2.13,"INCH",2.17)
}

