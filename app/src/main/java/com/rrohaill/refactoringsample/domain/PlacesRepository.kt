package com.rrohaill.refactoringsample.domain

import com.rrohaill.refactoringsample.api.Result2
import com.rrohaill.refactoringsample.data.PlacesResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

interface PlacesRepository {
    suspend fun fetchPlaces(id: Int, records: Int)

    fun getPlacesResult(): SharedFlow<PlacesResult>
}

class PlacesRepositoryImpl(private val placesDataSource: PlacesDataSource) : PlacesRepository {

    private val placesResult = MutableSharedFlow<PlacesResult>()

    override suspend fun fetchPlaces(id: Int, records: Int) {
        placesDataSource.fetchActivities(id, records).let {
            when (it) {
                is Result2.Success -> placesResult.emit(PlacesResult.Success(data = it.data))
                is Result2.Error -> placesResult.emit(PlacesResult.Error(error = it.error))
            }
        }
    }

    override fun getPlacesResult(): SharedFlow<PlacesResult> = placesResult

}