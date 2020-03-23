package com.ittybittyapps.onboardingexercise.data.networking

import com.ittybittyapps.onboardingexercise.data.model.Item
import io.reactivex.Single

interface HackerNewsManagerInterface {
    fun getStory(itemId: String): Single<Item>
    fun getTopStoryIds(): Single<List<String>>
}

class HackerNewsManager(
    private val hackerNewsService: HackerNewsInterface
) : HackerNewsManagerInterface {

    override fun getStory(itemId: String): Single<Item> = hackerNewsService.getStory(itemId)

    override fun getTopStoryIds(): Single<List<String>> = hackerNewsService.getTopStories()
}