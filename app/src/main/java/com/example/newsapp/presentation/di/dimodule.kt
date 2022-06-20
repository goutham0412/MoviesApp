package com.example.newsapp.presentation.di


import com.example.newsapp.presentation.composescreen.HomeScreenViewModel
import com.example.newsapp.presentation.detailsScreen.DetailsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dimodule= module {
    viewModel {
        HomeScreenViewModel(get())
    }
    viewModel {
        DetailsScreenViewModel(get())
    }
}