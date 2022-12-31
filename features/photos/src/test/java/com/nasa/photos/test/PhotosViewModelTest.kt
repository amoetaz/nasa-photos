package com.nasa.photos.test

import com.nasa.domain.modules.photos.repository.PhotosRepository
import com.nasa.domain.modules.photos.useCases.GetPhotosUseCase
import com.nasa.domain.utils.Resource
import com.nasa.photos.PhotosViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
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
    fun `first rover name is set to Curiosity`() = runBlocking {

        viewModel = PhotosViewModel(GetPhotosUseCase(photosRepository))

        Assert.assertEquals("Curiosity",viewModel.roverName)

    }
}