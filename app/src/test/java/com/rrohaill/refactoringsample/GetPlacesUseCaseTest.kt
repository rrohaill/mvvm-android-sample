package com.rrohaill.refactoringsample

import com.rrohaill.refactoringsample.domain.usecase.GetPlacesUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetPlacesUseCaseTest {

    @Test
    fun testUseCase(): Unit = runBlocking {

        val useCase: GetPlacesUseCase = mock()
        verifyZeroInteractions(useCase)

    }
}