package com.nantes.matthew.finastratechexam.photos.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nantes.matthew.finastratechexam.core.util.Status
import com.nantes.matthew.finastratechexam.photos.domain.use_case.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO Add Navigation Component,Create UI and State
@HiltViewModel
class MainViewModel @Inject constructor(private val getPhotosUseCase: GetPhotosUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow(PhotoFeedState())
    val uiState = _uiState as StateFlow<PhotoFeedState>

    companion object {
        private const val TAG = "MainActivityViewModel"
    }

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            val result = getPhotosUseCase()
            _uiState.update { currentState ->
                currentState.copy(
                    photos = result.data?.shuffled() ?: emptyList(),
                    showLoadingBar = result.status == Status.SUCCESS
                )
            }

        }
    }

}