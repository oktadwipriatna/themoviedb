package com.okta.themoviedb.view.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.okta.themoviedb.BuildConfig
import com.okta.themoviedb.R
import com.okta.themoviedb.callback.IGenreCallback
import com.okta.themoviedb.callback.IMovieCallback
import com.okta.themoviedb.models.MovieData

class MovieAdapter(
    private val discoverMovieList: List<MovieData>
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movieCallback: IMovieCallback? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val discoverMovie = discoverMovieList[position]
        holder.apply {
            tvTitle.text = discoverMovie.title
            listMovie.setOnClickListener {
                movieCallback?.selectedMovie(position, discoverMovie)
            }
            discoverMovie.poster_path?.let {
                Glide.with(holder.itemView.context)
                    .load(BuildConfig.BASE_POSTER_PATH+it)
                    .listener(
                        GlidePalette.with(BuildConfig.BASE_POSTER_PATH+it)
                        .use(BitmapPalette.Profile.VIBRANT)
                        .crossfade(true))
                    .into(imgMovie)
            }
        }
    }

    override fun getItemCount(): Int {
        return discoverMovieList.size
    }

    fun setListener(movieCallback: IMovieCallback) {
        this.movieCallback = movieCallback
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgMovie: AppCompatImageView = view.findViewById<View>(R.id.img_movie) as AppCompatImageView
        val tvTitle: TextView = view.findViewById<View>(R.id.tv_title) as TextView
        val listMovie: LinearLayout = view.findViewById<View>(R.id.list_movie) as LinearLayout
    }

}