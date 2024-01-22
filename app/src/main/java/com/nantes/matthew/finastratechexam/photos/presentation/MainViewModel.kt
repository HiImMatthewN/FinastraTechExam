package com.nantes.matthew.finastratechexam.photos.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nantes.matthew.finastratechexam.core.util.Status
import com.nantes.matthew.finastratechexam.photos.domain.use_case.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO Add Navigation Component,Create UI and State
@HiltViewModel
class MainViewModel @Inject constructor(private val getPhotosUseCase: GetPhotosUseCase) :
    ViewModel() {
    companion object {
        private const val TAG = "MainActivityViewModel"
    }

    fun getData() {
        viewModelScope.launch {
            val result = getPhotosUseCase()
            when (result.status) {
                Status.SUCCESS -> {
                    val photos = result.data ?: emptyList()

                }

                Status.ERROR -> {
                    Log.e(TAG, "Error :${result.message}")
                }

                Status.LOADING -> {

                }
            }
        }
    }

}