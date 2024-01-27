package com.nantes.matthew.finastratechexam.photos.presentation

import com.google.common.truth.Truth.assertThat
import com.nantes.matthew.finastratechexam.photos.domain.repository.FakePhotosRepository
import com.nantes.matthew.finastratechexam.photos.domain.use_case.GetPhotosUseCase
import com.nantes.matthew.finastratechexam.test_utils.MainDispatcherRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    private lateinit var fakePhotosRepository:FakePhotosRepository
    private lateinit var getPhotosUseCase: GetPhotosUseCase
    private lateinit var mainViewModel:MainViewModel


    @Before
    fun setUp(){
        fakePhotosRepository = FakePhotosRepository()
        getPhotosUseCase = GetPhotosUseCase(
            repository = fakePhotosRepository
        )
        mainViewModel = MainViewModel(
            getPhotosUseCase = getPhotosUseCase
        )
    }


    @Test
    fun `Photos fetched should match the correct page number`(){
        mainViewModel.setIntent(PhotoFeedIntent.NextPage)


        val updatedState = mainViewModel.uiState.value
        val photosFetched = updatedState.photos
        val updatedPageNumber = updatedState.currentPage

        val doesAllPhotosHaveTheSamePage = photosFetched.all {photo->
            photo.page == updatedPageNumber
        }

        assertThat(doesAllPhotosHaveTheSamePage).isEqualTo(true)
    }

    @Test
    fun `Should hide navigation buttons when scrolling`(){
        mainViewModel.setIntent(PhotoFeedIntent.UserScrolled(
            isScrolling = true
        ))
        val updatedState = mainViewModel.uiState.value

        assertThat(updatedState.showNextButton).isEqualTo(false)
        assertThat(updatedState.showPreviousButton).isEqualTo(false)
    }


}