package com.example.weatherapp.features.new_weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.wrapper.UIState
import com.example.weatherapp.features.new_weather.domain.entity.WeatherEntity
import com.example.weatherapp.features.new_weather.domain.use_case.GetWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeather
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<WeatherEntity>>(UIState.Idle)
    val uiState: StateFlow<UIState<WeatherEntity>> get() = _uiState

    init {
        fetchData("Dhaka")
    }

    fun fetchData(city: String) {
        viewModelScope.launch {
            _uiState.emit(UIState.Loading) // Set UIState to Loading before fetching data
            val result = getWeatherUseCase(city) // Fetch data from repository
            result.fold(
                ifLeft = { failure ->
                    _uiState.emit(UIState.Failure(failure)) // Emit UIState.Failure with AppFailure
                },
                ifRight = { weatherEntity ->
                    _uiState.emit(UIState.Success(weatherEntity)) // Emit UIState.Success with WeatherEntity
                }
            )
        }
    }
}