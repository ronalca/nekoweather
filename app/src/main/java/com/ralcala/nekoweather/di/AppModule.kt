package com.ralcala.nekoweather.di

import com.ralcala.nekoweather.BuildConfig
import com.ralcala.nekoweather.data.api.OpenWeatherApi
import com.ralcala.nekoweather.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesWeatherApi(
        retrofit: Retrofit
    ): OpenWeatherApi {
        return retrofit.create(OpenWeatherApi::class.java)
    }

    @Provides
    fun providesWeatherRepository(
        weatherApi: OpenWeatherApi
    ): WeatherRepository {
        return WeatherRepository(weatherApi)
    }

}