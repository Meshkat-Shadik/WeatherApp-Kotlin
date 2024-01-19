package com.example.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.UIState
import com.example.weatherapp.data.dto.WeatherDto
import com.example.weatherapp.domain.usecase.GetCityWeatherUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CityWeatherViewModel @Inject constructor
    (private val usecase: GetCityWeatherUsecase) : ViewModel() {
    private val _uiState = MutableStateFlow<UIState<WeatherDto>>(UIState.Idle)
    val uiState: StateFlow<UIState<WeatherDto>> get() = _uiState

    init {
        fetchData()
    }

     fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(UIState.Loading) // Set UIState to Loading before fetching data

            //delay(2000) // Simulate network delay
            delay(5000)
            val result = usecase.invoke("Dhaka") // Fetch data from repository
            withContext(Dispatchers.Main) {
                result.fold(
                    { _uiState.emit(UIState.Failure(it)) },
                    { _uiState.emit(UIState.Success(it)) }
                )
            }
        }
    }
}
