package com.okta.themoviedb.models

import androidx.annotation.Keep

@Keep
data class Genre(val genres: List<GenreData>)

@Keep
data class GenreData(val id: Int, val name: String, var selected: Boolean = false)