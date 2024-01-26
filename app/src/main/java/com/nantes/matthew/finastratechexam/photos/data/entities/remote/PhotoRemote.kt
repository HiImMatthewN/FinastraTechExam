package com.nantes.matthew.finastratechexam.photos.data.entities.remote

import com.google.gson.annotations.SerializedName
import com.nantes.matthew.finastratechexam.photos.data.entities.local.PhotoEntity
import com.nantes.matthew.finastratechexam.photos.data.entities.local.PhotoInfoEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PhotoRemote(
    @SerializedName("id")
    val id: String,
    @SerializedName("urls")
    val urls: PhotoUrls,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("created_at")
    val dateCreated: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("user")
    val author: AuthorRemote

) {
    fun getDateCreated(): Date? {
        val format = "yyyy-MM-d'T'HH:mm:ss'Z'"
        val dateFormatter = SimpleDateFormat(format, Locale.US)
        return try {
            dateFormatter.parse(this.dateCreated)!!
        } catch (e: Exception) {
            null
        }
    }


}

class PhotoUrls(
    @SerializedName("regular")
    val full: String
)

