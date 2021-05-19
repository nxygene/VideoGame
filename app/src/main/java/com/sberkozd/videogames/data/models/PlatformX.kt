package com.sberkozd.videogames.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlatformX(
    val platform: PlatformXX,
    val released_at: String?,
    val requirements_en: Any?,
    val requirements_ru: Any?
)