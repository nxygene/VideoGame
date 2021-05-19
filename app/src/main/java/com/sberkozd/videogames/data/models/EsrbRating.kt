package com.sberkozd.videogames.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EsrbRating(
    val id: Int,
    val name: String,
    val slug: String
)