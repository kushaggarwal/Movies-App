package com.example.movieslist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(var data : List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

//    private var data : List<Movie> =ArrayList()
//    private var new_data : List<GetMoviesResponse> = ArrayList()
    var onItemClick: ((login: String) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        )
  }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }

//    fun swapData(new_data: List<GetMoviesResponse>) {
//    Log.d("Response","$new_data")
////        this.data = new_data.movies
//        //notifyDataSetChanged()
//    }

    class MovieViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Movie) = with(itemView) {
            textView1.text = item.title
            textView2.text = "Description"
            //Picasso.get().load(item.avatarUrl).into(imageView)
            setOnClickListener {
                onItemClick?.invoke(item.login!!)
            }

        }
    }

}