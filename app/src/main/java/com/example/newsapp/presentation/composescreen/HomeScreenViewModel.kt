package com.example.newsapp.presentation.composescreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.movies.Item
import com.example.newsapp.data.utils.Constants

import com.example.newsapp.domain.usecase.GetMoviesUseCase
import com.example.newsapp.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val getNewsUseCase: GetMoviesUseCase):ViewModel() {

    private val _moviesDetails= MutableStateFlow(emptyList<Item>())
    val moviesDetails=_moviesDetails.asStateFlow()
    private val _loadingState= mutableStateOf(false)
    val loadingState=_loadingState



    init {
        viewModelScope.launch {
            getNewsUseCase.getMovies(Constants.apiKey).collect {
                Log.d("it---",it.toString())
                when (it) {
                    is Response.Success -> {
                        Log.d("success",it.data.toString())
                        _loadingState.value=false
                    _moviesDetails.value=it.data?: moviesDetails.value
                        Log.d("successs1",_moviesDetails.value.toString())
                    }
                    is Response.Failure->{
Log.d("error",it.message.toString())
                    }
                    is Response.Loading->{
                        _loadingState.value=true
                    }
                }
            }
        }

    }

}