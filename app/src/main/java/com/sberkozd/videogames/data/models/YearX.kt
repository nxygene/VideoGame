package com.sberkozd.videogames.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class YearX(
    val count: Int,
    val nofollow: Boolean,
    val year: Int
)