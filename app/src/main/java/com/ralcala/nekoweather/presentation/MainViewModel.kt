package com.ralcala.nekoweather.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralcala.nekoweather.BuildConfig
import com.ralcala.nekoweather.data.network.Resource
import com.ralcala.nekoweather.data.repository.WeatherRepository
import com.ralcala.nekoweather.data.tempKelvinToCelsius
import com.ralcala.nekoweather.data.unixToLocalTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    var appState by mutableStateOf(AppState())

    init {
        getCurrentWeatherData()
    }

    @SuppressLint("DefaultLocale")
    private fun getCurrentWeatherData() = viewModelScope.launch {
        appState = appState.copy(isLoading = true)

        repository.getCurrentWeatherByCoordinates(
//            latitude = appState.latitude,
//            longitude = appState.longitude,
            latitude = 14.3222587,
            longitude = 121.1039431,
            apiKey = BuildConfig.WEATHER_API_KEY
        ).also { response ->
            when (response) {
                is Resource.Failed -> {
                    // show error modal message here
                    appState = appState.copy(isLoading = false)
                }

                is Resource.Success -> {
//                    println("Data: ${response.value.toString()}")

                    val currentTempC = String.format("%.2f°C", tempKelvinToCelsius(response.value.main.temp))
                    val sunriseTime = response.value.sys?.sunrise?.let { unixToLocalTime(it) }
                    val sunsetTime = response.value.sys?.sunset?.let { unixToLocalTime(it) }

                    appState = appState.copy(
                        city = response.value.name,
                        country = response.value.sys?.country ?: "",
                        currentTemp = currentTempC,
                        sunsetTime = sunsetTime.toString(),
                        sunriseTime = sunriseTime.toString(),
                    )

                    appState = appState.copy(isLoading = false)
                }
            }
        }
    }

}

data class AppState(
    val city: String = "",
    val country: String = "",
    val currentTemp: String = "",
    val sunriseTime: String = "",
    val sunsetTime: String = "",

    val latitude: Double = 0.0,
    val longitude: Double = 0.0,

    val isLoading: Boolean = false,
    val isError: Boolean = false
)