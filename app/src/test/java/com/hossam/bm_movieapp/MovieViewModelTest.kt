package com.hossam.bm_movieapp


import com.hossam.bm_movieapp.data.model.MovieResponse
import com.hossam.bm_movieapp.data.model.Movie
import com.hossam.bm_movieapp.ui.viewmodel.MovieViewModel
import com.hossam.bm_movieapp.data.repository.MovieRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import io.mockk.mockk

class MovieViewModelTest {

    private lateinit var movieRepository: MovieRepository
    private lateinit var viewModel: MovieViewModel


    @Before
    fun setUp() {
        // Initialize the mock repository with MockK
        movieRepository = mockk()

        // Initialize the ViewModel (you can mock dependencies here too)
        viewModel = MovieViewModel(mockk(), movieRepository)
    }

    @Test
    fun testLoadNowPlayingMovies(): Unit = runBlocking {
        // Create a mock response with sample movie data, including all necessary fields
        val mockResponse = MovieResponse(
            results = listOf(
                Movie(
                    id = 1,
                    title = "Movie 1",
                    originalTitle = "Movie 1 Original",
                    voteAverage = 7.5,
                    voteCount = 120,
                    posterPath = "/path/to/poster1.jpg",
                    overview = "Overview of movie 1",
                    releaseDate = "2024-01-01"
                ),
                Movie(
                    id = 2,
                    title = "Movie 2",
                    originalTitle = "Movie 2 Original",
                    voteAverage = 8.0,
                    voteCount = 150,
                    posterPath = "/path/to/poster2.jpg",
                    overview = "Overview of movie 2",
                    releaseDate = "2024-02-01"
                )
            ),
            page = 1,
            totalPages = 10
        )

        // Mock the repository call to return the mock response
        `when`(movieRepository.getNowPlayingMovies(1)).thenReturn(mockResponse)

        // Call the ViewModel function
        viewModel.loadNowPlayingMovies()

        // Verify that the repository method was called with the expected argument
        verify(movieRepository).getNowPlayingMovies(1)

        // Optionally: Assert if the ViewModel's state has been updated correctly
        // Here, you could check if the _movies flow was updated to MovieState.Success
        // E.g., assert that the state has been set to MovieState.Success with the expected movies.
    }
}