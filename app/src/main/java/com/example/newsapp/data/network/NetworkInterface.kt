package com.example.newsapp.data.network

import com.example.newsapp.data.model.moviedetails.MovieDetails
import com.example.newsapp.data.model.movies.MoviesList

import com.example.newsapp.data.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkInterface {

    @GET("Top250Movies/{key}")
    suspend fun getMovies(
        @Path("key") key:String
    ):MoviesList

    @GET("Title/{key}/{movieId}")
    suspend fun getMovieDetails(
        @Path("key")key: String,
        @Path("movieId")movieId:String
    ):MovieDetails

}