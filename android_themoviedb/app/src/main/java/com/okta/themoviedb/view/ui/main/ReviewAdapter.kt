package com.okta.themoviedb.view.ui.main

import android.content.Intent
import android.graphics.Color
import android.net.Uri
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
import com.okta.themoviedb.models.ReviewData
import com.okta.themoviedb.models.Video
import com.okta.themoviedb.models.VideoData
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(
    private val reviewList: List<ReviewData>
) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_review, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = reviewList[position]
        holder.apply {
            itemView.run {
                item_review_title.text = review.author
                item_review_content.text = review.content
            }
        }
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

}