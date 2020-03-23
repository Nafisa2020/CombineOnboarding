package com.ittybittyapps.onboardingexercise.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemJson(
    val id: Int = 0,
    val deleted: Boolean = false,
    val type: String = "",
    val by: String = "",
    val time: Long = 0,
    val text: String = "",
    val dead: Boolean = false,
    val kids: List<Int> = listOf(),
    val url: String = "",
    val score: Int = 0,
    val title: String = "",
    val descendants: Int = 0
)