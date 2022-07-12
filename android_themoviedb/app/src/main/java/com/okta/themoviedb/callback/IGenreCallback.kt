package com.okta.themoviedb.callback

import com.okta.themoviedb.models.GenreData

interface IGenreCallback {

    fun selectedList(position: Int, genreData: GenreData)

}