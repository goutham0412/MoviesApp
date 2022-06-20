package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.movies.Item

import com.example.newsapp.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase{
    fun getMovies(apiKey:String)  :Flow<Response<List<Item>>>

}