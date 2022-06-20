package com.example.newsapp.data.repository

import android.util.Log
import com.example.newsapp.data.model.movies.Item

import com.example.newsapp.data.network.NetworkInterface
import com.example.newsapp.data.utils.Constants
import com.example.newsapp.domain.repository.GetMoviesRepository
import com.example.newsapp.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception


class GetMoviesRepositoryImpl(private val networkInterface: NetworkInterface):GetMoviesRepository {
    override fun getMovies(apiKey:String): Flow<Response<List<Item>>>{

        return flow<Response<List<Item>>>{
            emit(Response.Loading())
               try {
                   val data=networkInterface.getMovies(apiKey)


                   if(data.errorMessage.isNullOrEmpty()) {

                       emit(Response.Success(data = data.items))
                   }
                   else
                       emit(Response.Failure(message = data.errorMessage))
               }
               catch(e:Exception){
                   emit(Response.Failure(message = e.message))
               }



        }
    }



}