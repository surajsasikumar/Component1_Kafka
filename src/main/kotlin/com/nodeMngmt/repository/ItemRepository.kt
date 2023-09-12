package com.nodeMngmt.repository

import com.nodeMngmt.entity.ItemData
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
@Repository
interface ItemRepository: CrudRepository<ItemData, String> {
}