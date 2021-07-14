package com.example.marvelapi.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val count : Int,
    val results: List<Result>
)
