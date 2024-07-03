package com.example.weatherapp.features.weather.di

import com.example.weatherapp.features.weather.data.data_source.remote.WeatherRemoteDataSource
import com.example.weatherapp.features.weather.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.features.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): WeatherRemoteDataSource {
        return retrofit.create(WeatherRemoteDataSource::class.java)
    }

}
