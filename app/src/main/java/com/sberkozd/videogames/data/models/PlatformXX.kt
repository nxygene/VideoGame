package com.sberkozd.videogames.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlatformXX(
    val games_count: Int?,
    val id: Int,
    val image: Any?,
    val image_background: String?,
    val name: String,
    val slug: String?,
    val year_end: Any?,
    val year_start: Int?
)