package com.sberkozd.videogames.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShortScreenshot(
    val id: Int,
    val image: String
)