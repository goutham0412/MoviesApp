package com.example.newsapp.presentation.detailsScreen

import com.example.newsapp.data.model.moviedetails.MovieDetails
import retrofit2.Response

data class DetailsState(
    var loading:Boolean=false,
    var movieDetails: MovieDetails?=null,

)
