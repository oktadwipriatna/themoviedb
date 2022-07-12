package com.okta.themoviedb.view.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.okta.themoviedb.R
import com.okta.themoviedb.callback.IGenreCallback
import com.okta.themoviedb.callback.IMovieCallback
import com.okta.themoviedb.databinding.ActivityMainBinding
import com.okta.themoviedb.models.GenreData
import com.okta.themoviedb.models.Movie
import com.okta.themoviedb.models.MovieData
import com.okta.themoviedb.view.ui.movie.DetailMovieActivity
import com.okta.themoviedb.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), IGenreCallback, IMovieCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var genreListAdapter: GenreAdapter
    private lateinit var movieListAdapter: MovieAdapter

    private var genreList: MutableList<GenreData> = ArrayList()
    private var discoverMovieList: MutableList<MovieData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initUi()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        getGenre()
    }

    private fun initData() {
        genreListAdapter = GenreAdapter(genreList)
        genreListAdapter.setListener(this)

        movieListAdapter = MovieAdapter(discoverMovieList)
        movieListAdapter.setListener(this)
    }

    private fun initUi() {
        val layoutHorizontal = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        val layoutVertical = LinearLayoutManager(this@MainActivity)
        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(false)
                title = getString(R.string.app_name)
            }

            rvGenre.apply {
                layoutManager = layoutHorizontal
                itemAnimator = DefaultItemAnimator()
                adapter = genreListAdapter
                overScrollMode = View.OVER_SCROLL_NEVER
            }
            rvMovie.apply {
                layoutManager = layoutVertical
                itemAnimator = DefaultItemAnimator()
                adapter = movieListAdapter
                overScrollMode = View.OVER_SCROLL_NEVER
            }
        }
    }

    private fun getGenre() {
        viewModel.genres.observe(this, Observer { t ->
            genreList.clear()
            genreList.addAll(t.genres)
            genreList[0].selected = true
            genreListAdapter.notifyDataSetChanged()

            getDiscoverMovie(genreList[0].id)
        })
        viewModel.isLoading.observe(this, Observer {
        })
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) {
            }
        })

        viewModel.getGenres()
    }

    private fun getDiscoverMovie(id: Int) {
        viewModel.discover_movie.observe(this, Observer { t ->
            discoverMovieList.clear()
            discoverMovieList.addAll(t.results)
            movieListAdapter.notifyDataSetChanged()
        })

        viewModel.getDiscoverMovie(id, 1)
    }

    override fun selectedList(position: Int, genreData: GenreData) {
        for (i in genreList.indices) {
            this.genreList[i].selected = false
        }
        genreList[position].selected = true
        genreListAdapter.notifyDataSetChanged()

        getDiscoverMovie(genreData.id)
    }

    override fun selectedMovie(position: Int, movieData: MovieData) {
        startActivity(Intent(this, DetailMovieActivity::class.java)
            .putExtra(MovieData::class.java.simpleName, movieData))
    }
}