package com.okta.themoviedb.view.ui.main

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.okta.themoviedb.BuildConfig
import com.okta.themoviedb.R
import com.okta.themoviedb.models.VideoData

class VideoAdapter(
    private val videoList: List<VideoData>
) :
    RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = videoList[position]
        holder.apply {
            tvTitle.text = video.name
            Glide.with(holder.itemView.context)
                .load(BuildConfig.YOUTUBE_THUMBNAIL_URL + video.key + "/default.jpg")
                .listener(
                    GlidePalette.with(BuildConfig.YOUTUBE_THUMBNAIL_URL + video.key + "/default.jpg")
                        .use(BitmapPalette.Profile.VIBRANT)
                        .crossfade(true)
                )
                .into(imgVideo)
            listVideo.setOnClickListener(View.OnClickListener {
                holder.itemView.context.startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.YOUTUBE_VIDEO_URL + video.key))
                )
            })
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgVideo: AppCompatImageView =
            view.findViewById<View>(R.id.img_video_cover) as AppCompatImageView
        val tvTitle: TextView = view.findViewById<View>(R.id.tv_title) as TextView
        val listVideo: RelativeLayout = view.findViewById<View>(R.id.rel_list) as RelativeLayout
    }

}