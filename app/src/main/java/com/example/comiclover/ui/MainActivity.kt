package com.example.comiclover.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.comiclover.network.NetworkHandler
import com.example.comiclover.network.TodoApi
import com.example.comiclover.network.UserRepositoryImpl
import com.example.comiclover.network.ktorHttpClient
import com.example.comiclover.ui.theme.ComicLoverTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repos = UserRepositoryImpl(
            networkHandler = NetworkHandler(this),
            todoApi = TodoApi(
                ktorHttpClient
            )
        )
        lifecycleScope.launch(Dispatchers.IO) {
            val result = repos.getUserKtor()
            Log.d("TODO_LOG", result.toString())
        }
        setContent {
            ComicLoverTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComicLoverTheme {
        Greeting("Android")
    }
}