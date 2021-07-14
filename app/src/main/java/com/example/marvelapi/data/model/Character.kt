package com.example.marvelapi.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(
    val available : Int,
    val collectionURI : String,
    val items : List<Item>,
    val returned : Int
)
