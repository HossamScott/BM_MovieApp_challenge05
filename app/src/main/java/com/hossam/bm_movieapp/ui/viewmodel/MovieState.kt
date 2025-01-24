package com.hossam.bm_movieapp.ui.viewmodel

import com.hossam.bm_movieapp.data.model.Movie
import com.hossam.bm_movieapp.data.model.MovieDetailsResponse

// Sealed class to represent movie states
sealed class MovieState {
    object Loading : MovieState()
    data class Success(val movies: List<Movie>) : MovieState()
    data class MovieDetailsSuccess(val movie: MovieDetailsResponse) : MovieState()
    data class Error(val message: String) : MovieState()
}
