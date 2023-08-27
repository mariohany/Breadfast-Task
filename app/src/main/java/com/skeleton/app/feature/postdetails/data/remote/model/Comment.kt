package com.skeleton.app.feature.postdetails.data.remote.model

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("id")
    val id: Long,
    @SerializedName("post_id")
    val postId: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("body")
    val body: String,
)