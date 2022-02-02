package com.rrohaill.refactoringsample.domain.usecase

import com.rrohaill.refactoringsample.data.PlacesResult
import com.rrohaill.refactoringsample.domain.PlacesRepository
import kotlinx.coroutines.flow.SharedFlow

interface GetPlacesUseCase {
    operator fun invoke(): SharedFlow<PlacesResult>
}

class GetPlacesUseCaseImpl(private val placesRepository: PlacesRepository) : GetPlacesUseCase {
    override fun invoke(): SharedFlow<PlacesResult> =
        placesRepository.getPlacesResult()

}