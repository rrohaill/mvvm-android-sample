package com.rrohaill.refactoringsample.domain

import com.rrohaill.refactoringsample.api.BaseDataSource
import com.rrohaill.refactoringsample.api.MyApi

class PlacesDataSource constructor(private val service: MyApi) : BaseDataSource() {

    suspend fun fetchActivities(
        id: Int,
        records: Int
    ) = getResult {
        service.getPlaces(id = id, records = records)
    }

}