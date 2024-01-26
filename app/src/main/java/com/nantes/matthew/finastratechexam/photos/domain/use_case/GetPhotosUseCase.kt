package com.nantes.matthew.finastratechexam.photos.domain.use_case

import com.nantes.matthew.finastratechexam.photos.domain.repository.PhotoRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: PhotoRepository) {

     operator fun invoke(page: Int) = repository.getPhotos(
        page = page
    )

}