package com.sberkozd.videogames.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rating(
    val count: Int,
    val id: Int,
    val percent: Double,
    val title: String
)