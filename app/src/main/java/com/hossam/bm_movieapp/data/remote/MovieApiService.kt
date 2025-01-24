package com.hossam.bm_movieapp.data.remote

import com.hossam.bm_movieapp.data.model.MovieDetailsResponse
import com.hossam.bm_movieapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Header("Authorization") token: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") token: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Header("Authorization") token: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/{movie_id}?language=en-US")
    suspend fun getMovieDetails(
        @Header("Authorization") token: String,
        @Path("movie_id") movieId: Int): MovieDetailsResponse
}
