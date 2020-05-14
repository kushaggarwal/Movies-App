package com.example.movieslist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RvMovie.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        GlobalScope.launch(Dispatchers.Main) {
            val response  = withContext(Dispatchers.IO){
                Client.api.getPopularMovies()
            }
            if (response.isSuccessful) {
                val responseBody = response.body()
                Log.d("Response","$responseBody")
                response.body()?.let { adapter.swapData(responseBody.movies) }

            }
        }
    }
}