package com.example.moviesapp.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.moviesapp.ui.theme.MoviesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
                // A surface container using the 'background' color from the theme
                val viewModel: MainViewModel = viewModel()
                val state = viewModel.state.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyVerticalGrid(columns = GridCells.Adaptive(180.dp)) {
                        items(state.value.characters) { character ->
                            Column(modifier = Modifier.padding(8.dp)) {
                                val httpsImgUrl = character.thumbnail.path.replace("http", "https")
                                Text(
                                    text = character.name,
                                    fontSize = 16.sp,
                                    maxLines = 1,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                                AsyncImage(

                                    model = "${httpsImgUrl}.jpg",
                                    contentDescription = character.name,
                                    onError = { error ->
                                        Log.i("e", error.toString())
                                    },
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier.size(300.dp, 300.dp)
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}

