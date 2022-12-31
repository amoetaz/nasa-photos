package com.nasa.rovers.di



import com.nasa.data.modules.photos.remote.ArticlesRemoteDataSource
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
    fun provideArticlesRepository(
        remoteDataSource: ArticlesRemoteDataSource
    ): PhotosRepository = PhotosRepositoryImp(remoteDataSource)

}