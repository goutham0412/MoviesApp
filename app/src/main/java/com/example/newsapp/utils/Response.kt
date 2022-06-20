package com.example.newsapp.utils

sealed class Response<T>(var data:T?=null,var message:String?=null)
{
     class Success<T>(data: T?):Response<T>(data)
    class Failure<T>(message: String?):Response<T>(message = message)
    class Loading<T>():Response<T>()
}
