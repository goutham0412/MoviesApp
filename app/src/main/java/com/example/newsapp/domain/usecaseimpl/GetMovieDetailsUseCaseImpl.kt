package com.example.newsapp.domain.usecaseimpl

import com.example.newsapp.data.model.moviedetails.MovieDetails
import com.example.newsapp.domain.repository.GetMovieDetailsRepository
import com.example.newsapp.domain.usecase.GetMovieDetailsUseCase
import com.example.newsapp.utils.Response
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCaseImpl(private val getMovieDetailsRepository:GetMovieDetailsRepository) :GetMovieDetailsUseCase{
    override fun getMovieDetails(movieId: String): Flow<Response<MovieDetails>> {
        return getMovieDetailsRepository.getMovieDetails(movieId = movieId)
    }

}