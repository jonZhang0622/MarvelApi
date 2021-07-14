package com.example.marvelapi.data.repos

import com.example.marvelapi.BuildConfig
import com.example.marvelapi.data.model.Result
import com.example.marvelapi.data.remote.MarvelService
import com.example.marvelapi.utils.Resource
import com.example.marvelapi.utils.toMD5
import javax.inject.Inject

class MarvelRepo @Inject constructor(private val marvelApi: MarvelService) {
    suspend fun getComics(): Resource<List<Result>> {
        return try {
            val timeStamp = (System.currentTimeMillis() / 1000).toString()
            val hash =
                (timeStamp + BuildConfig.MARVEL_PRIVATE_API_KEY + BuildConfig.MARVEL_PUBLIC_API_KEY).toMD5()
            val marvelResponse = marvelApi.getComics(timeStamp = timeStamp, hash = hash)
            if (marvelResponse.isSuccessful && marvelResponse.body() != null) {
                Resource.Success(marvelResponse.body()!!.data.results.filter { it.format.isNotBlank() && it.format.isNotBlank() }
                )
            } else {
                Resource.Error("No Comics found")
            }
        } catch (ex: Exception) {
            Resource.Error(ex.toString())
        }
    }
}