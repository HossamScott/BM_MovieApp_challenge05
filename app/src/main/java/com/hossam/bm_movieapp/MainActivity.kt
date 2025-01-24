package com.hossam.bm_movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.hossam.bm_movieapp.util.BMMovieAppTheme
import com.hossam.bm_movieapp.ui.HomePage

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMMovieAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HomePage()
                }
            }
        }
    }
}
