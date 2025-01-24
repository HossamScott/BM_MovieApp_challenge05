package com.hossam.bm_movieapp.di

import com.hossam.bm_movieapp.data.remote.MovieApiService
import com.hossam.bm_movieapp.data.repository.MovieRepository
import com.hossam.bm_movieapp.ui.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.hossam.bm_movieapp.util.Constants

val appModule = module {
    // Provide MovieApiService
    single<MovieApiService> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    // Provide MovieRepository
    single { MovieRepository(get()) }

    // Provide MovieViewModel
    viewModel { MovieViewModel(get(), get()) }
}
