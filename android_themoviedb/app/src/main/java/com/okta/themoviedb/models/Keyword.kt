package com.okta.themoviedb.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Keyword(val id: Int, val keywords: List<KeywordData>, var total_pages: Int)

@Parcelize
data class KeywordData(
    val id: Int,
    val name: String
) : Parcelable