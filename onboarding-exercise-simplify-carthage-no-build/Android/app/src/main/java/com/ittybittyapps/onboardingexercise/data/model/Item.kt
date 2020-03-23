package com.ittybittyapps.onboardingexercise.data.model

sealed class Item {

    enum class Type(val value: String) {
        STORY("story"),
        JOB("job")
    }

    data class Story(
        val by: String,
        val descendants: Int = 0,
        val id: Int,
        val kids: List<Int> = listOf(),
        val score: Int,
        val time: Long,
        val title: String,
        val type: Type,
        val url: String
    ) : Item()

    data class Job(
        val by: String,
        val id: Int,
        val score: Int,
        val text: String,
        val time: Long,
        val title: String,
        val type: Type,
        val url: String
    ) : Item()
}