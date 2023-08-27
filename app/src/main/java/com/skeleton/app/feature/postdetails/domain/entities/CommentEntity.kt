package com.skeleton.app.feature.postdetails.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentEntity(
    val id:Long,
    val name:String,
    val body:String,
):Parcelable
