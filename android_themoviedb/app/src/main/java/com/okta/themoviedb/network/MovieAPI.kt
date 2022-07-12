package com.okta.themoviedb.network

import androidx.lifecycle.LiveData
import com.okta.themoviedb.models.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") api_key: String): Genre

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("api_key") api_key: String,
        @Query("with_genres") with_genres: Int,
        @Query("page") page: Int
    ): Movie

    @GET("movie/{movie_id}/keywords&")
    suspend fun getKeywords(@Path("movie_id") id: Int, @Query("api_key") api_key: String): Keyword

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(@Path("movie_id") id: Int, @Query("api_key") api_key: String): Video

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(@Path("movie_id") id: Int, @Query("api_key") api_key: String): Review
}