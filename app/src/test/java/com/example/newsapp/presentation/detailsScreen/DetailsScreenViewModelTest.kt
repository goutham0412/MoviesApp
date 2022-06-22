package com.example.newsapp.presentation.detailsScreen

import android.util.Log
import app.cash.turbine.test
import com.example.newsapp.data.model.moviedetails.MovieDetails
import com.example.newsapp.domain.usecase.GetMovieDetailsUseCase
import com.example.newsapp.domain.usecaseimpl.GetMovieDetailsUseCaseImpl
import com.example.newsapp.utils.Response
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class DetailsScreenViewModelTest{
    val useCase= mock<GetMovieDetailsUseCase> ()
    private lateinit var detailsScreenViewModel: DetailsScreenViewModel
    private val mainThread = newSingleThreadContext("UI thread")
    @Before
    fun setUp()
    {
        Dispatchers.setMain(mainThread)

     detailsScreenViewModel= DetailsScreenViewModel(useCase)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThread.close()
    }
    @Test
    fun `loading state is true`(){
        runBlocking (){
            launch(Dispatchers.Main) {
                whenever(useCase.getMovieDetails("any")) doReturn (flow { emit(Response.Loading<MovieDetails>())
              })
                detailsScreenViewModel.loadMovieData("any")
//
                detailsScreenViewModel.movieDetails.test {

//                    assertEquals(false,awaitItem().loading)
                    assertEquals(true,awaitItem().loading)

//

                }
            }

        }
    }
    @Test
    fun `Response is of type Success`()
    {
        runBlocking {
            launch(Dispatchers.Main) {
                whenever(useCase.getMovieDetails("correctMovieId")) doReturn (
                        flowOf (
                            Response.Loading<MovieDetails>(),
                            Response.Success<MovieDetails>(data = MovieDetails(title = "Avengers")))

                        )
                detailsScreenViewModel.loadMovieData("correctMovieId")
                detailsScreenViewModel.movieDetails.test{

                    assertEquals(true,awaitItem().loading)
                    val details=awaitItem()
                    assertEquals(false,details.loading)
                    assertEquals("Avengers",details.movieDetails?.title)

//                    assertEquals(false,awaitItem().loading)
//                    assertEquals(true,awaitItem().loading)
//                    awaitItem()

//                    awaitComplete()

            }

            }
        }
    }
}