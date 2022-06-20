package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.movies.Item
import com.example.newsapp.data.model.movies.MoviesList
import com.example.newsapp.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetMoviesRepository {
    fun getMovies(apiKey:String):Flow<Response<List<Item>>>
}