package com.rrohaill.refactoringsample.domain.usecase

import com.rrohaill.refactoringsample.domain.PlacesRepository

interface FetchPlacesUseCase {
    suspend operator fun invoke(id: Int = 50, records: Int = 500)
}

class FetchPlacesUseCaseImpl(private val placesRepository: PlacesRepository) : FetchPlacesUseCase {
    override suspend fun invoke(id: Int, records: Int) {
        placesRepository.fetchPlaces(id = id, records = records)
    }

}