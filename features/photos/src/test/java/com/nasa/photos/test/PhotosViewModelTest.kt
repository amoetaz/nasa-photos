package com.nasa.photos.test

import com.nasa.domain.modules.photos.repository.PhotosRepository
import com.nasa.domain.modules.photos.useCases.GetPhotosUseCase
import com.nasa.domain.utils.Resource
import com.nasa.photos.PhotosViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PhotosViewModelTest {

    private val photosRepository = mock<PhotosRepository>()
    private lateinit var viewModel: PhotosViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `for success resourse data not null`() = runBlocking {
        whenever(photosRepository.getPhotos(1, "", 1)).thenReturn(Resource.Success(emptyList()))
        viewModel = PhotosViewModel(GetPhotosUseCase(photosRepository))

        Assert.assertEquals(Resource.Loading, viewModel.photosResponse.value)

    }
}