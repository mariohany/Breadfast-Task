package com.skeleton.app.feature.posts.data.remote.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

data class User(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: Gender,
    @SerializedName("status")
    val status: String,
)

enum class Gender {
    male,
    female
}
