package com.okta.themoviedb.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
data class Video(val id: Int, val results: List<VideoData>)

@Parcelize
data class VideoData(
    val id: String,
    val name: String,
    val site: String,
    val key: String,
    val size: Int,
    val type: String
) : Parcelable