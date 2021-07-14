package com.example.marvelapi.data.remote

import com.example.marvelapi.BuildConfig
import com.example.marvelapi.data.model.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("ts") timeStamp : String,
        @Query("apikey") apiKey : String = BuildConfig.MARVEL_PUBLIC_API_KEY,
        @Query("hash") hash : String
    ) : Response<MarvelResponse>
}