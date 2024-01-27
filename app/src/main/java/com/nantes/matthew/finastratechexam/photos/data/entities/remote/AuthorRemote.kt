package com.nantes.matthew.finastratechexam.photos.data.entities.remote

import com.google.gson.annotations.SerializedName
import com.nantes.matthew.finastratechexam.photos.data.entities.local.AuthorEntity

class AuthorRemote(
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    val userName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_image")
    val profileImage: ProfileImage

)

class ProfileImage(
    @SerializedName("small")
    val small: String,
    @SerializedName("medium")
    val medium: String
)

