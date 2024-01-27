package com.nantes.matthew.finastratechexam.photos.data.data_source

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.nantes.matthew.finastratechexam.photos.data.entities.local.AuthorEntity
import com.nantes.matthew.finastratechexam.photos.data.entities.local.PhotoEntity
import com.nantes.matthew.finastratechexam.photos.data.entities.local.PhotoInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePhotos(entities: List<PhotoInfoEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAuthors(entities: List<AuthorEntity>): List<Long>

    @Transaction
    suspend fun savePhotosAndAuthors(
        photoEntities: List<PhotoInfoEntity>,
        authorEntities: List<AuthorEntity>
    ) {
        savePhotos(
            entities = photoEntities
        )
        saveAuthors(
            entities = authorEntities
        )
    }
    @Query("SELECT * FROM photo_tbl WHERE page = :page LIMIT :limit")
    fun getPhotos(page:Int,limit:Int):Flow<List<PhotoEntity>>

}