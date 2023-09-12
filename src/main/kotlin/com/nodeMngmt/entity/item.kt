package com.nodeMngmt.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.Embeddable
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Embedded

@Serdeable
@Introspected
data class Item(
    val tcin: String,
    @Embedded
    val compliance: ComplianceDto,
    @Embedded
    val packageDimensions: PackageDimensionsDto
){
    constructor() : this(
        "11010144",
        ComplianceDto(false, true, true),
        PackageDimensionsDto(MeasurementsDto(DimensionsDto(4.69, 2.13, "INCH", 2.17), WeightDto("POUND", 0.4)))
    )
}
@Embeddable
@Serdeable
data class ComplianceDto(
    val isFlammable: Boolean,
    val isHazmatPublishable: Boolean,
    val lithiumClassified: Boolean
)
@Embeddable
@Serdeable
data class PackageDimensionsDto(
    @Embedded
    val measurements: MeasurementsDto
)
@Embeddable
@Serdeable
data class MeasurementsDto(
    @Embedded
    val dimensions: DimensionsDto,
    @Embedded
    val weight: WeightDto
)
@Embeddable
@Serdeable
data class WeightDto(
    val code: String,
    val value: Double
)
@Embeddable
@Serdeable
data class DimensionsDto(
    val depth: Double,
    val height: Double,
    val units: String,
    val width: Double
)