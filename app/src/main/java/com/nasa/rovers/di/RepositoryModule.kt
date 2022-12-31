package com.nasa.rovers.di



import com.nasa.data.modules.photos.remote.PhotosRemoteDataSource
import com.nasa.data.modules.photos.repository.PhotosRepositoryImp
import com.nasa.domain.modules.photos.repository.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providePhotosRepository(
        remoteDataSource: PhotosRemoteDataSource
    ): PhotosRepository = PhotosRepositoryImp(remoteDataSource)

}