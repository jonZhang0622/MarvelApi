package com.example.marvelapi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapi.data.model.Result
import com.example.marvelapi.data.repos.MarvelRepo
import com.example.marvelapi.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MarvelViewModel @Inject constructor(private val marvelRepo: MarvelRepo) : ViewModel() {

    private var _marvelResource = MutableLiveData<Resource<List<Result>>>()
    val marvelResource get() = _marvelResource

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
}