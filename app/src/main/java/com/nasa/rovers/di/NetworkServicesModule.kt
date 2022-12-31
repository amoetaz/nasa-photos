package com.nasa.rovers.di



import com.nasa.data.modules.photos.remote.PhotosServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServicesModule {

    @Provides
    @Singleton
    fun provideArticlesService(retrofit: Retrofit): PhotosServices =
        retrofit.create(PhotosServices::class.java)

}