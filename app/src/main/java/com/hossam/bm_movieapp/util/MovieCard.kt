package com.hossam.bm_movieapp.util


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController
import com.hossam.bm_movieapp.data.model.Movie
import com.skydoves.landscapist.glide.GlideImage
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MovieCard(movie: Movie, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("movie_details/${movie.id}") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Movie Poster
            GlideImage(
                imageModel = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                contentDescription = "Movie Poster",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

            // Movie Info (Title, Overview, etc.)
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Movie Title
                Text(
                    text = movie.originalTitle,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onSurface
                )

                // Movie Overview
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.body2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                // Movie Release Date and Vote Average
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Release Date
                    Text(
                        text = formatReleaseDate(movie.releaseDate),
                        style = MaterialTheme.typography.body2,
                        color = Color.Gray
                    )

                    // Vote Average
                    Text(
                        text = "Rating: ${movie.voteAverage} (${movie.voteCount} votes)",
                        style = TextStyle(color = Color.Gray)
                    )
                }
            }
        }
    }
}

@Composable
fun formatReleaseDate(releaseDate: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        val date = inputFormat.parse(releaseDate)
        outputFormat.format(date)
    } catch (e: Exception) {
        releaseDate
    }
}

