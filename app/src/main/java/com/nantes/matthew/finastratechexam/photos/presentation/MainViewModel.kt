package com.nantes.matthew.finastratechexam.photos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nantes.matthew.finastratechexam.core.util.Status
import com.nantes.matthew.finastratechexam.photos.domain.use_case.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val getPhotosUseCase: GetPhotosUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow(PhotoFeedState())
    val uiState = _uiState as StateFlow<PhotoFeedState>


    companion object {
        private const val TAG = "MainActivityViewModel"
    }

    init {
        fetchPhotos()
    }


    fun setIntent(intent: PhotoFeedIntent) {
        when (intent) {
            PhotoFeedIntent.FetchPhotos -> {
                fetchPhotos()
            }

            PhotoFeedIntent.NextPage -> {
                val newPage = _uiState.value.currentPage + 1
                _uiState.update { currentState ->
                    currentState.copy(
                        currentPage = newPage,
                        showPreviousButton = true
                    )
                }
                fetchPhotos()
            }

            PhotoFeedIntent.PreviousPage -> {
                val previousPage =
                    if (_uiState.value.currentPage == 1) 1 else _uiState.value.currentPage - 1
                _uiState.update { currentState ->
                    currentState.copy(
                        currentPage = previousPage,
                        showPreviousButton = previousPage > 1
                    )
                }
                fetchPhotos()
            }

            is PhotoFeedIntent.UserScrolled -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        isScrolling = intent.isScrolling,
                        showNextButton = !intent.isScrolling && currentState.photos.isNotEmpty(),
                        showPreviousButton = !intent.isScrolling && currentState.currentPage > 1
                    )
                }
            }


        }

    }

    private var fetchPhotosJob: Job? = null
    private fun fetchPhotos() {
        fetchPhotosJob?.cancel()
        fetchPhotosJob = null

        fetchPhotosJob = viewModelScope.launch {
            getPhotosUseCase(
                page = _uiState.value.currentPage
            ).collectLatest { result ->
                when(result.status){
                    Status.SUCCESS,Status.LOADING -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                photos = if (result.data.isNullOrEmpty()) currentState.photos else result.data,
                                showProgressBar = result.data.isNullOrEmpty() &&
                                currentState.photos.isEmpty(),
                                showNextButton =
                                (currentState.photos.isNotEmpty() ||
                                        !result.data.isNullOrEmpty()) &&
                                        !currentState.isScrolling,
                                error = null
                            )
                        }
                    }
                    Status.ERROR ->{
                        _uiState.update { currentState ->
                            currentState.copy(
                                photos = result.data ?: emptyList(),
                                showProgressBar = false,
                                showNextButton =
                                (currentState.photos.isNotEmpty() ||
                                        !result.data.isNullOrEmpty()) &&
                                        !currentState.isScrolling,
                                error = if(result.data.isNullOrEmpty()
                                    && currentState.photos.isEmpty())
                                            result.message else null
                            )
                        }
                    }
                }

            }


        }
    }

}