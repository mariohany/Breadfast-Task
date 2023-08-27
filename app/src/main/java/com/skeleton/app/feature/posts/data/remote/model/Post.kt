package com.skeleton.app.feature.posts.data.remote.model

import com.google.gson.annotations.SerializedName


data class Post(
    @SerializedName("id")
    val id: Long,
    @SerializedName("user_id")
    val userId: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
)
