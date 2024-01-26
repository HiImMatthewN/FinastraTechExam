package com.nantes.matthew.finastratechexam.photos.data.entities.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nantes.matthew.finastratechexam.photos.domain.model.Author

@Entity(tableName = "photo_author_tbl")
class AuthorEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "profile_image")
    val profileImage: String
)

fun AuthorEntity.toDomain(): Author {
    return Author(
        id = this.id,
        username = this.username,
        name = this.name,
        profileImage = this.profileImage
    )
}
