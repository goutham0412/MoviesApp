package com.example.newsapp.domain.usecaseimpl

import com.example.newsapp.data.model.movies.Item

import com.example.newsapp.domain.repository.GetMoviesRepository
import com.example.newsapp.domain.usecase.GetMoviesUseCase
import com.example.newsapp.utils.Response
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCaseImpl(private val getNewsRepository: GetMoviesRepository):GetMoviesUseCase {


    override fun getMovies(apiKey:String): Flow<Response<List<Item>>> {
        return getNewsRepository.getMovies(apiKey = apiKey)
    }

}