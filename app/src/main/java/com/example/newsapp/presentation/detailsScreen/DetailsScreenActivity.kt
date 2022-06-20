package com.example.newsapp.presentation.detailsScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.example.newsapp.R
import com.example.newsapp.data.model.moviedetails.MovieDetails
import com.example.newsapp.databinding.ActivityDetailsScreenBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DetailsScreenActivity : AppCompatActivity() {
    private var binding:ActivityDetailsScreenBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbar)
        val actionBar=supportActionBar
        if(actionBar!=null)
        {
            actionBar.title="MovieDetails"
            actionBar.setDisplayHomeAsUpEnabled(true)

        }
        binding?.toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
        val detailsScreenViewModel=getViewModel<DetailsScreenViewModel>()
        if(intent.hasExtra("movieId"))
        {
            Log.d("movieId",intent.getStringExtra("movieId").toString())
            val movieId=intent.getStringExtra("movieId")
            movieId?.let {
                detailsScreenViewModel.loadMovieData(movieId)
            }

        }
        lifecycleScope.launch {

//            repeatOnLifecycle(Lifecycle.State.STARTED)
            Log.d("details---",detailsScreenViewModel.movieDetails.value.toString())
                detailsScreenViewModel.movieDetails.collect{details->

                    if(details.loading){

                    }
                    else{
                        details.movieDetails?.let {
                            setMovieData(it)
                        }
                    }
                }


        }


    }

    private fun setMovieData(movieDetails: MovieDetails) {
        Log.d("details-----",movieDetails.toString())
        binding?.movieImage?.load(movieDetails.image)
        binding?.duration?.text=movieDetails.runtimeStr
        binding?.genre?.text=movieDetails.genres
        binding?.releaseDate?.text=movieDetails.releaseDate
        binding?.plot?.text=movieDetails.plot
        binding?.director?.text=movieDetails.directors
        binding?.cast?.text=movieDetails.stars
    }
}