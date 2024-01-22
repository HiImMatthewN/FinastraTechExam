package com.nantes.matthew.finastratechexam.photos.data.entities

import com.google.gson.annotations.SerializedName

class PhotoRemote(
    @SerializedName("id")
    val id:String,
    @SerializedName("urls")
    val urls:PhotoUrls,
    @SerializedName("likes")
    val likes:Int,
    @SerializedName("created_at")
    val dateCreated:String,
    @SerializedName("user")
    val author:AuthorRemote

)
class PhotoUrls(
    @SerializedName("full")
    val full:String
)