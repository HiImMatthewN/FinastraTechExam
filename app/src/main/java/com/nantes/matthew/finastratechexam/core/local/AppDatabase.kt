package com.nantes.matthew.finastratechexam.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nantes.matthew.finastratechexam.photos.data.data_source.PhotoDao
import com.nantes.matthew.finastratechexam.photos.data.entities.local.AuthorEntity
import com.nantes.matthew.finastratechexam.photos.data.entities.local.PhotoInfoEntity

@Database(
    entities = [
        PhotoInfoEntity::class,
        AuthorEntity::class
               ],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}