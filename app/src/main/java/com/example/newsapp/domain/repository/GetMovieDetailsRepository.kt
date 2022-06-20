package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.moviedetails.MovieDetails
import com.example.newsapp.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailsRepository {
    fun getMovieDetails(movieId:String):Flow<Response<MovieDetails>>
}