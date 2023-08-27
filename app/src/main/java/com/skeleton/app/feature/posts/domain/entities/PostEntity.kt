package com.skeleton.app.feature.posts.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class PostEntity(
    val id:Long,
    val userName:String,
    val title:String,
    val body:String,
):Parcelable, Serializable
