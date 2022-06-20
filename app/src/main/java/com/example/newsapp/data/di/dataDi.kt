package com.example.newsapp.data.di

import com.example.newsapp.data.network.NetworkInterface
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val dataDi= module {
    single {
        Retrofit.Builder().baseUrl(
         "https://imdb-api.com/en/API/"
        ).addConverterFactory(GsonConverterFactory.create())
            .build().create(NetworkInterface::class.java)
    }
}