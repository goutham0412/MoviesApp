package com.example.newsapp.presentation.composescreen

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import org.koin.androidx.compose.getViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.data.model.movies.Item
import com.example.newsapp.presentation.detailsScreen.DetailsScreenActivity

@Composable
fun HomeScreen(

){
    val viewModel= getViewModel<HomeScreenViewModel>()
  val items=viewModel.moviesDetails.collectAsState()

    if(viewModel.loadingState.value)
    {
        Log.d("loading",viewModel.loadingState.toString())
        Column() {
            CircularProgressIndicator()
        }

    }
    else {
        LazyColumn() {
            items(items = items.value)
            {
                Movies(it)
            }
        }
    }
}

@Composable
fun Movies(movieDetails:Item) {
    val context= LocalContext.current
    if(movieDetails!=null) {
       Card(elevation = 3.dp,modifier = Modifier
           .padding(5.dp)
           .heightIn(max = 120.dp).clickable {
               val intent=Intent(context, DetailsScreenActivity::class.java)
               intent.putExtra("movieId",movieDetails.id)
               context.startActivity(intent)
           },shape = RoundedCornerShape(3.dp)) {
           Row(modifier = Modifier.fillMaxWidth()) {
               androidx.compose.material.Surface(shape = CircleShape,modifier = Modifier.size(70.dp).padding(5.dp)) {
                   AsyncImage(model = ImageRequest.Builder(LocalContext.current).
                   data("${movieDetails.image}").build()
                       , contentDescription = "",
                       contentScale = ContentScale.Crop)
               }
               Column {
                Text(text = "${movieDetails.title}",fontWeight = FontWeight.Bold)
                   Text(text = buildAnnotatedString {
                       withStyle(style = SpanStyle(fontWeight = FontWeight.Bold))
                       {
                           append("Year: ")
                       }
                       withStyle(style = SpanStyle(fontWeight = FontWeight.Normal,color = Color.Black))
                       {
                           append("${movieDetails.year}")
                       }

                   })
                   Text(text = buildAnnotatedString {
                       withStyle(style = SpanStyle(fontWeight = FontWeight.Bold))
                       {
                           append("Imdb: ")
                       }
                       withStyle(style = SpanStyle(fontWeight = FontWeight.Normal,color = Color.Black))
                       {
                           append("${movieDetails.imDbRating}")
                       }

                   })
               }

           }



       }
    }
}
