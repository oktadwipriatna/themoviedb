package com.okta.themoviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okta.themoviedb.BuildConfig
import com.okta.themoviedb.models.*
import com.okta.themoviedb.network.RetrofitInstance
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailMovieViewModel : ViewModel() {
    // Video
    private val _video: MutableLiveData<Video> = MutableLiveData()
    val video: LiveData<Video>
        get() = _video

    // Keywordd
    private val _keyword: MutableLiveData<Keyword> = MutableLiveData()
    val keyword: LiveData<Keyword>
        get() = _keyword

    // Review
    private val _review: MutableLiveData<Review> = MutableLiveData()
    val review: LiveData<Review>
        get() = _review

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    fun getVideo(id: Int) {
        viewModelScope.launch {
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val fetchedVideo =
                    RetrofitInstance.api.getVideos(id, BuildConfig.API_KEY)
                _video.value = fetchedVideo
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getKeyword(id: Int) {
        viewModelScope.launch {
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val fetchedKeyword =
                    RetrofitInstance.api.getKeywords(id, BuildConfig.API_KEY)
                _keyword.value = fetchedKeyword
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getReview(id: Int) {
        viewModelScope.launch {
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val fetchedReview =
                    RetrofitInstance.api.getReviews(id, BuildConfig.API_KEY)
                _review.value = fetchedReview
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
