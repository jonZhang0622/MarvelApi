package com.example.marvelapi.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    val resourceURI: String,
    val name: String
)
