package com.nantes.matthew.finastratechexam.photos.data.entities

import com.google.gson.annotations.SerializedName

class AuthorRemote(
    @SerializedName("id")
    val id:String,
    @SerializedName("username")
    val userName:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("profile_image")
    val profileImage:ProfileImage

)
class ProfileImage(
    @SerializedName("small")
    val small:String
)