package com.okta.themoviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okta.themoviedb.BuildConfig
import com.okta.themoviedb.models.Genre
import com.okta.themoviedb.models.Movie
import com.okta.themoviedb.network.RetrofitInstance
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {
    // Genre
    private val _genres: MutableLiveData<Genre> = MutableLiveData()
    val genres: LiveData<Genre>
        get() = _genres

    // Discover Movie
    private val _discover_movie: MutableLiveData<Movie> = MutableLiveData()
    val discover_movie: LiveData<Movie>
        get() = _discover_movie

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    fun getGenres() {
        viewModelScope.launch {
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val fetchedGenres = RetrofitInstance.api.getGenres(BuildConfig.API_KEY)
                _genres.value = fetchedGenres
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getDiscoverMovie(genre_id: Int, page: Int) {
        viewModelScope.launch {
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val fetchedDiscoverMovie =
                    RetrofitInstance.api.getDiscoverMovie(BuildConfig.API_KEY, genre_id, page)
                _discover_movie.value = fetchedDiscoverMovie
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
