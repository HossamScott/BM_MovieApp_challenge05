package com.hossam.bm_movieapp.data.repository

import com.hossam.bm_movieapp.data.model.MovieDetailsResponse
import com.hossam.bm_movieapp.data.remote.MovieApiService
import com.hossam.bm_movieapp.data.model.MovieResponse
import com.hossam.bm_movieapp.util.Constants

class MovieRepository(private val apiService: MovieApiService) {

    // Function to fetch Now Playing Movies
    suspend fun getNowPlayingMovies(page: Int): MovieResponse {
        return apiService.getNowPlayingMovies(
            token = Constants.API_KEY,
            page = page
        )
    }

    // Function to fetch Popular Movies
    suspend fun getPopularMovies(page: Int): MovieResponse {
        return apiService.getPopularMovies(
            token = Constants.API_KEY,
            page = page
        )
    }

    // Function to fetch Upcoming Movies
    suspend fun getUpcomingMovies(page: Int): MovieResponse {
        return apiService.getUpcomingMovies(
            token = Constants.API_KEY,
            page = page
        )
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse {
        return apiService.getMovieDetails(token = Constants.API_KEY,movieId)
    }
}
