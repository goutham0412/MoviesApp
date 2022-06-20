package com.example.newsapp.domain.di

import com.example.newsapp.data.repository.GetMovieDetailsRepositoryImpl
import com.example.newsapp.data.repository.GetMoviesRepositoryImpl
import com.example.newsapp.domain.repository.GetMovieDetailsRepository
import com.example.newsapp.domain.repository.GetMoviesRepository
import com.example.newsapp.domain.usecase.GetMovieDetailsUseCase
import com.example.newsapp.domain.usecase.GetMoviesUseCase
import com.example.newsapp.domain.usecaseimpl.GetMovieDetailsUseCaseImpl
import com.example.newsapp.domain.usecaseimpl.GetMoviesUseCaseImpl
import org.koin.dsl.module

val domainModule= module {
    single<GetMoviesRepository> {
        GetMoviesRepositoryImpl(get())
    }
    single<GetMoviesUseCase>{
        GetMoviesUseCaseImpl(get())
    }

    single<GetMovieDetailsUseCase>{
        GetMovieDetailsUseCaseImpl(get())
    }
    single<GetMovieDetailsRepository> {
        GetMovieDetailsRepositoryImpl(get())
    }

}