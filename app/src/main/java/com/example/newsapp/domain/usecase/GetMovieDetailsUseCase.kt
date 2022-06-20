package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.moviedetails.MovieDetails
import com.example.newsapp.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailsUseCase {

    fun getMovieDetails(movieId:String):Flow<Response<MovieDetails>>
}