package com.example.newsapp.presentation.detailsScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.moviedetails.MovieDetails
import com.example.newsapp.domain.usecase.GetMovieDetailsUseCase
import com.example.newsapp.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
class DetailsScreenViewModel(private val getMovieDetailsUseCase: GetMovieDetailsUseCase):ViewModel() {

    private val _movieDetails= MutableSharedFlow<DetailsState>()
    val movieDetails=_movieDetails

    fun loadMovieData(movieID:String)
    {
        viewModelScope.launch(Dispatchers.Main) {
            getMovieDetailsUseCase.getMovieDetails(movieId = movieID).collect{movie->
                when(movie)
                {
                    is Response.Loading->{
                       _movieDetails.emit(DetailsState(loading = true))
//                        Log.d("loading",_movieDetails.value.loading.toString())

                    }
                    is Response.Success->{
//                       _movieDetails.value.loading=false
//                        _movieDetails.value.movieDetails=movie.data
                        _movieDetails.emit(DetailsState(loading = false,movieDetails = movie.data))
//                        Log.d("movie",movieDetails.value.movieDetails.toString())
                    }
                    is Response.Failure->{
                        Log.d("failure",movie.message.toString())
                    }
                }
            }
        }
    }


}
//class DetailsScreenViewModel(private val getMovieDetailsUseCase: GetMovieDetailsUseCase):ViewModel() {
//
//    private val _movieDetails= MutableStateFlow(DetailsState())
//    val movieDetails=_movieDetails.asStateFlow()
//
//    fun loadMovieData(movieID:String)
//    {
//        viewModelScope.launch(Dispatchers.Main) {
//            getMovieDetailsUseCase.getMovieDetails(movieId = movieID).collect{movie->
//                when(movie)
//                {
//                    is Response.Loading->{
//                        _movieDetails.value=movieDetails.value.copy(loading = true)
////                        Log.d("loading",_movieDetails.value.loading.toString())
//
//                    }
//                    is Response.Success->{
////                       _movieDetails.value.loading=false
////                        _movieDetails.value.movieDetails=movie.data
//                        _movieDetails.value=movieDetails.value.copy(
//                            loading = false,
//                            movieDetails = movie.data
//
//                        )
//                        Log.d("movie",movieDetails.value.movieDetails.toString())
//                    }
//                    is Response.Failure->{
//                        Log.d("failure",movie.message.toString())
//                    }
//                }
//            }
//        }
//    }
//
//
//}