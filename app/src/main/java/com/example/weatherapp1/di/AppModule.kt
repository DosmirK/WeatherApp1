package com.example.weatherapp1.di

import com.example.weatherapp1.data.api.WeatherService
import com.example.weatherapp1.data.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = NetworkService.createRetrofit(okHttpClient)
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient = NetworkService.createOkHttpClient(interceptor)
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor = NetworkService.createLoggingInterceptor()
    @Provides
    fun provideCartoonApiService(retrofit: Retrofit): WeatherService = retrofit.create(WeatherService::class.java)

}