package com.example.weatherapp1.data.network

import okhttp3.logging.HttpLoggingInterceptor.Level
import com.example.weatherapp1.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkService {

    fun createRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun createOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .callTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .url(chain.request().url.newBuilder()
                    .addQueryParameter("key", BuildConfig.API_KEY)
                    .build())
                .build()
            chain.proceed(request)
        }
        .addInterceptor(interceptor)
        .build()


    fun createLoggingInterceptor(): HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = Level.BODY
        return interceptor
    }
}