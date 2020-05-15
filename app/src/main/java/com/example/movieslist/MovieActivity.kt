package com.example.movieslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        val id  = intent.getStringExtra("ID")


        GlobalScope.launch(Dispatchers.Main) {
            val response  = withContext(Dispatchers.IO){
                Client.api.getPopularMovies()
            }
            if(response.isSuccessful){
                Log.d("Response Movie Activity","$response")
            }
        }
    }
}
