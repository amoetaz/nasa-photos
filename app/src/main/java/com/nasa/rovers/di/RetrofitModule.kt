package com.nasa.rovers.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val REQUEST_TIME_OUT: Long = 20

    @Provides
    @Singleton
    fun provideHeadersInterceptor() =
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .header("Accept" , "application/json")
                    .build()
            )
        }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        headersInterceptor: Interceptor,
        logging: HttpLoggingInterceptor
    ): Call.Factory {
        val cacheSize = 10 * 1024 * 1024
        return OkHttpClient.Builder()
            .cache(Cache(context.cacheDir , cacheSize.toLong()))
            .connectTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(headersInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton

    fun provideRetrofit(gson: Gson, okHttpClient: Call.Factory): Retrofit = Retrofit.Builder()
        .callFactory(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("https://api.nasa.gov/mars-photos/api/v1/")
        .build()


}