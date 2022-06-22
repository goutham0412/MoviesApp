package com.example.newsapp.data.repository

import app.cash.turbine.test
import com.example.newsapp.data.model.movies.Item
import com.example.newsapp.data.model.movies.MoviesList
import com.example.newsapp.data.network.NetworkInterface
import com.example.newsapp.data.utils.Constants
import com.example.newsapp.utils.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class GetMovieDetailsRepositoryImplTest{
    val mockInterface=mock<NetworkInterface>()

    private lateinit var repositoryImpl: GetMoviesRepositoryImpl
    @Before
    fun setUp()
    {
      repositoryImpl=GetMoviesRepositoryImpl(mockInterface)
    }
    @Test
    fun `should return Success if apiKey is valid`()
    {
        runBlocking {
            whenever(mockInterface.getMovies(Constants.apiKey)) doReturn MoviesList("", emptyList())
            repositoryImpl=GetMoviesRepositoryImpl(mockInterface)

            repositoryImpl.getMovies(Constants.apiKey).test{

                assert(awaitItem() is Response.Loading)
                assert(awaitItem() is Response.Success)
                awaitComplete()
        }
        }

    }
    @Test
    fun `return Failure if apiKey is invalid`()
    {
        runBlocking {
        whenever(mockInterface.getMovies("random")) doReturn MoviesList("Invalid ApiKey",
            emptyList())
            val repositoryImpl1=GetMoviesRepositoryImpl(mockInterface)
            repositoryImpl1.getMovies("random").test {
                assert(awaitItem() is Response.Loading)
                assert(awaitItem() is Response.Failure)
                awaitComplete()
            }
        }
    }
//    spy and mock

}