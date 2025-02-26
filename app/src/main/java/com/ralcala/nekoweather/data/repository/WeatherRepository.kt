package com.ralcala.nekoweather.data.repository

import com.ralcala.nekoweather.data.api.OpenWeatherApi
import com.ralcala.nekoweather.data.network.SafeApiCall
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: OpenWeatherApi
) : SafeApiCall {

    suspend fun getCurrentWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ) = safeApiCall {
        api.getCurrentWeatherData(
            lat = latitude,
            lon = longitude,
            apiKey = apiKey
        )
    }
}