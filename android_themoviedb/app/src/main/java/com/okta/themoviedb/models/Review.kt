package com.okta.themoviedb.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
data class Review(val page: Int, val results: List<ReviewData>, var total_pages: Int, var total_results: Int)

@Parcelize
data class ReviewData(
    val id: String,
    val author: String,
    val content: String,
    val url: String
) : Parcelable