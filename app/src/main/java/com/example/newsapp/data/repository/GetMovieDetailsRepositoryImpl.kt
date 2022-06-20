package com.example.newsapp.data.repository

import android.util.Log
import com.example.newsapp.data.model.moviedetails.MovieDetails
import com.example.newsapp.data.network.NetworkInterface
import com.example.newsapp.data.utils.Constants
import com.example.newsapp.domain.repository.GetMovieDetailsRepository
import com.example.newsapp.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieDetailsRepositoryImpl(private val networkInterface: NetworkInterface) :GetMovieDetailsRepository{
    override fun getMovieDetails(movieId: String): Flow<Response<MovieDetails>> {
        return flow<Response<MovieDetails>> {
            emit(Response.Loading())
            val data=networkInterface.getMovieDetails(Constants.apiKey,movieId = movieId)
            Log.d("data",data.toString())
            if(data.errorMessage.isNullOrEmpty())
                emit(Response.Success(data = data))
            else
                emit(Response.Failure(data.errorMessage))

        }
    }
}