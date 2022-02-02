package com.rrohaill.refactoringsample.utils

import android.content.Context
import com.rrohaill.refactoringsample.domain.PlacesDataSource
import com.rrohaill.refactoringsample.domain.PlacesRepository
import com.rrohaill.refactoringsample.domain.PlacesRepositoryImpl
import com.rrohaill.refactoringsample.api.ApiFactory

object RepositoryFactory {

    private lateinit var placesRepository: PlacesRepository

    fun getPlacesRepository(context: Context): PlacesRepository {
        if (!::placesRepository.isInitialized) {

            val service = ApiFactory.getApi(context = context)
            val dataSource = PlacesDataSource(service = service)

            placesRepository =
                PlacesRepositoryImpl(
                    placesDataSource = dataSource
                )
        }

        return placesRepository
    }
}