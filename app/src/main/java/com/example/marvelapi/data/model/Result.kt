package com.example.marvelapi.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val id: Int,
    val digitalId: Int,
    val title: String,
    val issueNumber: Int,
    val description: String,
    val isbn: String,
    val format: String,
    val pageCount: Int,
    val dates: List<Dates>,
    val prices: List<Prices>,
    val thumbnail: Thumbnail,
    val characters: List<Character>
)