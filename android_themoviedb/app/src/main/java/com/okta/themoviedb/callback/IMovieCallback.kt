package com.okta.themoviedb.callback

import com.okta.themoviedb.models.MovieData

interface IMovieCallback {

    fun selectedMovie(position: Int, movieData: MovieData)

}