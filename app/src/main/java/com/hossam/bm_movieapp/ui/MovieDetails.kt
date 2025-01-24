package com.hossam.bm_movieapp.ui

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hossam.bm_movieapp.data.model.MovieDetailsResponse
import com.hossam.bm_movieapp.ui.viewmodel.MovieState
import com.hossam.bm_movieapp.ui.viewmodel.MovieViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailsScreen(navController: NavHostController, movieId: Int) {
    val viewModel: MovieViewModel = koinViewModel()
    val movieDetailsState = viewModel.movieDetails.value

    // Trigger fetching movie details when the screen is opened
    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = movieDetailsState.let {
                            if (it is MovieState.MovieDetailsSuccess) it.movie.title else "Loading..."
                        },
                        color = Color.White,
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.secondary
            )
        }
    ) { padding ->
        when (movieDetailsState) {
            is MovieState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
            is MovieState.Error -> {
                Text(
                    text = "Error: ${movieDetailsState.message}",
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Red
                )
            }
            is MovieState.MovieDetailsSuccess -> {
                MovieDetailsContent(
                    movieDetails = movieDetailsState.movie,
                    modifier = Modifier.padding(padding)
                )
            }
            is MovieState.Success -> {
            }
        }
    }
}

@Composable
fun MovieDetailsContent(movieDetails: MovieDetailsResponse, modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        // Backdrop Image
        movieDetails.backdrop_path?.let { backdropPath ->
            GlideImage(
                imageUrl = "https://image.tmdb.org/t/p/w780$backdropPath",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Movie Details Section
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = movieDetails.title,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface
            )

            if (movieDetails.tagline.isNotEmpty()) {
                Text(
                    text = "\"${movieDetails.tagline}\"",
                    style = MaterialTheme.typography.body1,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Genres as Chips
            LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
                items(movieDetails.genres) { genre ->
                    GenreChip(name = genre.name)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movieDetails.overview,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Additional Info
            Text(
                text = "Release Date: ${movieDetails.release_date}",
                style = MaterialTheme.typography.body2,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Ratings
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Rating: ${movieDetails.vote_average}/10",
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "Votes: ${movieDetails.runtime}",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun GenreChip(name: String) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.body2,
            color = Color.DarkGray
        )
    }
}

@Composable
fun GlideImage(imageUrl: String, modifier: Modifier, shape: Shape) {
    val context = LocalContext.current
    val imageView = remember { ImageView(context) }

    Glide.with(context)
        .load(imageUrl)
        .apply(RequestOptions().centerCrop())
        .into(imageView)

    AndroidView(
        factory = { imageView },
        modifier = modifier
    )
}
