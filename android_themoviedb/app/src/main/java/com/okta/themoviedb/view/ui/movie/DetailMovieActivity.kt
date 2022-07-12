package com.okta.themoviedb.view.ui.movie

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.okta.themoviedb.BuildConfig
import com.okta.themoviedb.R
import com.okta.themoviedb.databinding.ActivityDetailMovieBinding
import com.okta.themoviedb.models.MovieData
import com.okta.themoviedb.models.ReviewData
import com.okta.themoviedb.models.VideoData
import com.okta.themoviedb.view.ui.main.ReviewAdapter
import com.okta.themoviedb.view.ui.main.VideoAdapter
import com.okta.themoviedb.viewmodel.DetailMovieViewModel
import kotlinx.android.synthetic.main.layout_movie_detail_body.*

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    private lateinit var viewModel: DetailMovieViewModel
    private lateinit var movieData: MovieData

    private lateinit var videoAdapter: VideoAdapter
    private lateinit var reviewAdapter: ReviewAdapter

    private var videoList: MutableList<VideoData> = ArrayList()
    private var reviewList: MutableList<ReviewData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieData = intent.getParcelableExtra(MovieData::class.java.simpleName)!!
        viewModel = ViewModelProvider(this).get(DetailMovieViewModel::class.java)

        initData()
        initUi()

        getVideo()
        getReview()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == android.R.id.home) onBackPressed()
        return false
    }

    private fun initData() {
        videoAdapter = VideoAdapter(videoList)
        reviewAdapter = ReviewAdapter(reviewList)
    }

    private fun initUi() {
        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.mipmap.ic_arrow_back_white_24dp)
                title = movieData.title
            }

            Glide.with(this@DetailMovieActivity)
                .load(BuildConfig.BASE_POSTER_PATH + movieData.poster_path)
                .listener(
                    GlidePalette.with(BuildConfig.BASE_POSTER_PATH + movieData.poster_path)
                        .use(BitmapPalette.Profile.VIBRANT)
                        .crossfade(true)
                )
                .into(imgMovie)

            detailHeader.tvTitle.text = movieData.title
            detailHeader.tvRelease.text = "Release Date : " + movieData.release_date
            detailHeader.rbStar.rating = (movieData.vote_average / 2)

            val layoutHorizontal = LinearLayoutManager(this@DetailMovieActivity, LinearLayoutManager.HORIZONTAL, false)
            rvVideo.apply {
                layoutManager = layoutHorizontal
                itemAnimator = DefaultItemAnimator()
                adapter = videoAdapter
                overScrollMode = View.OVER_SCROLL_NEVER
            }
            tvSummary.text = movieData.overview

            val layoutVertical = LinearLayoutManager(this@DetailMovieActivity)
            rvReview.apply {
                layoutManager = layoutVertical
                itemAnimator = DefaultItemAnimator()
                adapter = reviewAdapter
                overScrollMode = View.OVER_SCROLL_NEVER
            }
        }
    }

    private fun getVideo() {
        viewModel.video.observe(this, Observer { t ->
            videoList.clear()
            videoList.addAll(t.results)
            videoAdapter.notifyDataSetChanged()
        })
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) {
            }
        })
        viewModel.getVideo(movieData.id)
    }

    private fun getKeyword() {
        viewModel.keyword.observe(this, Observer { t ->
        })
        viewModel.getKeyword(movieData.id)
    }

    private fun getReview() {
        viewModel.review.observe(this, Observer { t ->
            reviewList.clear()
            reviewList.addAll(t.results)
            reviewAdapter.notifyDataSetChanged()
        })
        viewModel.getReview(movieData.id)
    }
}