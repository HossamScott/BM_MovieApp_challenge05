package com.hossam.bm_movieapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hossam.bm_movieapp.ui.viewmodel.MovieState
import com.hossam.bm_movieapp.ui.viewmodel.MovieViewModel
import com.hossam.bm_movieapp.util.MovieCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavigationGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.NowPlaying,
        BottomNavItem.Popular,
        BottomNavItem.Upcoming
    )
    BottomNavigation(
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        // Get the current route
        val currentRoute by navController.currentBackStackEntryAsState()
        items.forEach { item ->
            val isSelected = currentRoute?.destination?.route == item.route
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (isSelected) MaterialTheme.colors.secondary else MaterialTheme.colors.onSurface
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (isSelected) MaterialTheme.colors.secondary else MaterialTheme.colors.onSurface
                    )
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = true
            )
        }
    }
}



sealed class BottomNavItem(val title: String, val route: String, val icon: ImageVector) {
    object NowPlaying : BottomNavItem("Now Playing", "now_playing", Icons.Default.Favorite)
    object Popular : BottomNavItem("Popular", "popular", Icons.Default.Star)
    object Upcoming : BottomNavItem("Upcoming", "upcoming", Icons.Default.DateRange)
}

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController,
        startDestination = BottomNavItem.NowPlaying.route,
        modifier = modifier
    ) {
        composable(BottomNavItem.NowPlaying.route) { NowPlayingScreen(navController) }
        composable(BottomNavItem.Popular.route) { PopularScreen(navController) }
        composable(BottomNavItem.Upcoming.route) { UpcomingScreen(navController) }
        composable(
            "movie_details/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            movieId?.let {
                MovieDetailsScreen(
                    movieId = it,
                    navController = navController
                )
            }
        }
    }
}



@Composable
fun NowPlayingScreen(navController: NavHostController) {
    // Getting the ViewModel instance properly through Koin
    val viewModel: MovieViewModel = koinViewModel()
    // Collecting state from the ViewModel
    val moviesState = viewModel.movies.collectAsState().value
    // Launching the data fetch when the screen is shown
    LaunchedEffect(Unit) {
        viewModel.loadNowPlayingMovies()
    }

    when (moviesState) {
        is MovieState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
        }
        is MovieState.Error -> {
            Text("Error: ${moviesState.message}", modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center).padding(horizontal = 16.dp))
        }
        is MovieState.MovieDetailsSuccess -> {
            // Handle MovieDetailsSuccess state if needed (if this screen needs it)
            Text("Movie Details: ${moviesState.movie.title}", modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
        }
        is MovieState.Success -> {
            LazyColumn {
                items(moviesState.movies) { movie ->
                    MovieCard(movie = movie, navController = navController)
                }
            }
        }
    }
}

@Composable
fun PopularScreen(navController: NavHostController) {
    // Getting the ViewModel instance properly through Koin
    val viewModel: MovieViewModel = koinViewModel()
    // Collecting state from the ViewModel
    val moviesState = viewModel.movies.collectAsState().value
    // Launching the data fetch when the screen is shown
    LaunchedEffect(Unit) {
        viewModel.loadPopularMovies()
    }

    when (moviesState) {
        is MovieState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
        }
        is MovieState.Error -> {
            Text("Error: ${moviesState.message}", modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center).padding(horizontal = 16.dp))
        }
        is MovieState.MovieDetailsSuccess -> {
            // Handle MovieDetailsSuccess state if needed (if this screen needs it)
            Text("Movie Details: ${moviesState.movie.title}", modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
        }
        is MovieState.Success -> {
            LazyColumn {
                items(moviesState.movies) { movie ->
                    MovieCard(movie = movie, navController = navController)
                }
            }
        }
    }
}

@Composable
fun UpcomingScreen(navController: NavHostController) {
    // Getting the ViewModel instance properly through Koin
    val viewModel: MovieViewModel = koinViewModel()
    // Collecting state from the ViewModel
    val moviesState = viewModel.movies.collectAsState().value
    // Launching the data fetch when the screen is shown
    LaunchedEffect(Unit) {
        viewModel.loadUpcomingMovies()
    }

    when (moviesState) {
        is MovieState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
        }
        is MovieState.Error -> {
            Text("Error: ${moviesState.message}", modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center).padding(horizontal = 16.dp))
        }
        is MovieState.MovieDetailsSuccess -> {
            // Handle MovieDetailsSuccess state if needed (if this screen needs it)
            Text("Movie Details: ${moviesState.movie.title}", modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
        }
        is MovieState.Success -> {
            LazyColumn {
                items(moviesState.movies) { movie ->
                    MovieCard(movie = movie, navController = navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage()
}
