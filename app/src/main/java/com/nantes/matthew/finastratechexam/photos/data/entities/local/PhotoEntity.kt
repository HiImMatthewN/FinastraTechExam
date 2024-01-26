package com.nantes.matthew.finastratechexam.photos.data.entities.local

import androidx.room.Embedded
import androidx.room.Relation
import com.nantes.matthew.finastratechexam.photos.domain.model.Photo
import java.util.Date

class PhotoEntity(
    @Embedded
    val photoInfoEntity: PhotoInfoEntity,
    @Relation(
        parentColumn = "author_id",
        entityColumn = "id"
    )
    val authorEntity: AuthorEntity
)

fun PhotoEntity.toDomain(): Photo {
    return Photo(
        id = this.photoInfoEntity.id,
        url = this.photoInfoEntity.url,
        author = this.authorEntity.toDomain(),
        likes = this.photoInfoEntity.likes,
        dateCreated = this.photoInfoEntity.dateCreated.let { epochTime ->
            Date(epochTime)
        }
    )
}