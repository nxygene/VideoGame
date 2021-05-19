package com.sberkozd.videogames.data.responses

import com.sberkozd.videogames.data.models.Filters
import com.sberkozd.videogames.data.models.Game
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GamesListResponse(
    val count: Int,
    val description: String,
    val filters: Filters,
    val next: String,
    val nofollow: Boolean,
    val nofollow_collections: List<String>,
    val noindex: Boolean,
    val previous: Any?,
    val results: List<Game>,
    val seo_description: String,
    val seo_h1: String,
    val seo_keywords: String,
    val seo_title: String
)