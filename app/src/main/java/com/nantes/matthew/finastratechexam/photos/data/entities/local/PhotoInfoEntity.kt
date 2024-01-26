package com.nantes.matthew.finastratechexam.photos.data.entities.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nantes.matthew.finastratechexam.photos.domain.model.Author
import java.util.Date


@Entity(tableName = "photo_tbl")
class PhotoInfoEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "likes")
    val likes: Int,
    @ColumnInfo(name = "author_id")
    val authorId: String,
    @ColumnInfo(name = "page")
    val page:Int,
    @ColumnInfo(name = "date_created")
    val dateCreated: Long
)