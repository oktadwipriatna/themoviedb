package com.okta.themoviedb.models

import androidx.annotation.Keep
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Keep
data class Movie(val page: Int, val results: List<MovieData>, var total_pages: Int)

@Parcelize
data class MovieData(
    var keywords: List<KeywordData>? = ArrayList(),
    var videos: List<VideoData>? = ArrayList(),
    var reviews: List<ReviewData>? = ArrayList(),
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String,
    val genre_ids: List<Int>,
    val original_title: String,
    val original_language: String,
    val title: String,
    val backdrop_path: String?,
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float,
    val id: Int
) : Parcelable