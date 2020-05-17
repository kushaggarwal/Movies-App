package com.example.movieslist

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(),MovieAdapter.OnItemClickListener {
    override fun OnitemClick(movie: Movie) {

        var intent = Intent(this@MainActivity,MovieActivity::class.java)
        intent.putExtra("title",movie.title)
        startActivity(intent)

    }

    val list : ArrayList<Movie> = arrayListOf<Movie>()

    private val adapter = MovieAdapter(list,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        adapter.onItemClick = {
//
//            val intent = Intent(this,MovieActivity::class.java)
//            //
//            // intent.putExtra("ID",it)
//            startActivity(intent)
//
//        }

        RvMovie.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        GlobalScope.launch(Dispatchers.Main) {
            val response  = withContext(Dispatchers.IO){
                Client.api.getPopularMovies()
            }
            if (response.isSuccessful) {
                //val responseBody = response.body()
                //Log.d("Response","$responseBody")
                response.body()?.let {res->
                    res.movies.let{
                        list.addAll(it)
                        Log.d("response","$list")

                    }
                    runOnUiThread { adapter.notifyDataSetChanged() }
                }

            }
        }
    }
}