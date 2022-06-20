package com.example.newsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.presentation.composescreen.HomeScreen
import com.example.newsapp.presentation.ui.theme.NewsAppTheme

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)
        binding?.compseView?.apply {
            setContent {
                NewsAppTheme {
                    HomeScreen()
                }
            }
        }

    }
}