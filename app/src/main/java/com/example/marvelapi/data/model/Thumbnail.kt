package com.example.marvelapi.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Thumbnail(
    val path : String,
    val extension : String
)
