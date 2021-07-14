package com.example.marvelapi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapi.data.model.Result
import com.example.marvelapi.data.repos.MarvelRepo
import com.example.marvelapi.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelViewModel @Inject constructor(private val marvelRepo: MarvelRepo) : ViewModel() {

    private var _marvelResource = MutableLiveData<Resource<List<Result>>>()
    val marvelResource get() = _marvelResource

    private var _selectedComic = MutableLiveData<Result>()
    val selectedComic get() = _selectedComic

    init {
        getComics()
    }

    private fun getComics() {
        _marvelResource.value = Resource.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val marvelResource = marvelRepo.getComics()
            _marvelResource.postValue(marvelResource)
        }
    }

    fun setSelectedComic(result: Result) {
        _selectedComic.value = result
    }
}