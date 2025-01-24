package com.hossam.bm_movieapp.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossam.bm_movieapp.data.repository.MovieRepository
import com.hossam.bm_movieapp.util.isNetworkAvailable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.State

class MovieViewModel(private val context: Context, private val movieRepository: MovieRepository) : ViewModel() {

    private val _movies = MutableStateFlow<MovieState>(MovieState.Loading)
    val movies: StateFlow<MovieState> get() = _movies

    private val _movieDetails = mutableStateOf<MovieState>(MovieState.Loading)
    val movieDetails: State<MovieState> = _movieDetails

    private var currentPage = 1
    private var isLoading = false

    // Now Playing movies
    fun loadNowPlayingMovies() {
        if (!isNetworkAvailable(context)) {
            _movies.value = MovieState.Error("No internet connection. Please check your network.")
            return
        }
        if (isLoading) return

        isLoading = true
        viewModelScope.launch {
            try {
                val response = movieRepository.getNowPlayingMovies(currentPage)
                _movies.value = MovieState.Success(response.results)
                currentPage++
            } catch (e: Exception) {
                _movies.value = MovieState.Error("Failed to load movies: ${e.message}")
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    // Popular movies
    fun loadPopularMovies() {
        if (!isNetworkAvailable(context)) {
            _movies.value = MovieState.Error("No internet connection. Please check your network.")
            return
        }
        if (isLoading) return

        isLoading = true
        viewModelScope.launch {
            try {
                val response = movieRepository.getPopularMovies(currentPage)
                _movies.value = MovieState.Success(response.results)
                currentPage++
            } catch (e: Exception) {
                _movies.value = MovieState.Error("Failed to load movies: ${e.message}")
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    // Upcoming movies
    fun loadUpcomingMovies() {
        if (!isNetworkAvailable(context)) {
            _movies.value = MovieState.Error("No internet connection. Please check your network.")
            return
        }
        if (isLoading) return

        isLoading = true
        viewModelScope.launch {
            try {
                val response = movieRepository.getUpcomingMovies(currentPage)
                _movies.value = MovieState.Success(response.results)
                currentPage++
            } catch (e: Exception) {
                _movies.value = MovieState.Error("Failed to load movies: ${e.message}")
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }



    fun fetchMovieDetails(movieId: Int) {
        if (!isNetworkAvailable(context)) {
            _movieDetails.value = MovieState.Error("No internet connection. Please check your network.")
            return
        }
        if (isLoading) return

        isLoading = true
        viewModelScope.launch {
            try {
                val response = movieRepository.getMovieDetails(movieId) // Single movie
                _movieDetails.value = MovieState.MovieDetailsSuccess(response) // Update with movie details
            } catch (e: Exception) {
                _movieDetails.value = MovieState.Error("Failed to load movie details: ${e.message}")
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }





}
