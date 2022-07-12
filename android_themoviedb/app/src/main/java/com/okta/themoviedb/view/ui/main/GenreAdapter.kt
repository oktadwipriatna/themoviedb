package com.okta.themoviedb.view.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.okta.themoviedb.R
import com.okta.themoviedb.callback.IGenreCallback
import com.okta.themoviedb.models.GenreData

class GenreAdapter(
    private val genreDataList: List<GenreData>
) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private var genreCallback: IGenreCallback? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genre, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genreList = genreDataList[position]
        holder.apply {
            if (genreList.selected) {
                listGenre.setBackgroundResource(R.drawable.bg_card_primary)
                tvGenre.setTextColor(Color.parseColor("#ffffff"))
            } else {
                listGenre.setBackgroundResource(R.drawable.bg_card_white_stroke)
                tvGenre.setTextColor(Color.parseColor("#0d253f"))
            }

            tvGenre.text = genreList.name
            listGenre.setOnClickListener {
                genreCallback?.selectedList(position, genreList)
            }
        }
    }

    override fun getItemCount(): Int {
        return genreDataList.size
    }

    fun setListener(genreCallback: IGenreCallback) {
        this.genreCallback = genreCallback
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvGenre: TextView = view.findViewById<View>(R.id.tv_genre) as TextView
        val listGenre: LinearLayout = view.findViewById<View>(R.id.list_genre) as LinearLayout
    }

}