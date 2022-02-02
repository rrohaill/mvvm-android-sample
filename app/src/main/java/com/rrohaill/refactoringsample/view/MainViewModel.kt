package com.rrohaill.refactoringsample.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rrohaill.refactoringsample.data.PlacesResult
import com.rrohaill.refactoringsample.domain.usecase.FetchPlacesUseCase
import com.rrohaill.refactoringsample.domain.usecase.GetPlacesUseCase
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val fetchPlacesUseCase: FetchPlacesUseCase,
    private val getPlacesUseCase: GetPlacesUseCase
) : ViewModel() {
    fun fetchPlaces() {
        viewModelScope.launch {
            fetchPlacesUseCase()
        }
    }

    fun getResult(): SharedFlow<PlacesResult> =
        getPlacesUseCase()
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory constructor(
    private val fetchPlacesUseCase: FetchPlacesUseCase,
    private val getPlacesUseCase: GetPlacesUseCase
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainViewModel(
            fetchPlacesUseCase, getPlacesUseCase
        ) as T

}