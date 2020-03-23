package com.ittybittyapps.onboardingexercise.data.networking

import com.ittybittyapps.onboardingexercise.data.model.Item
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsInterface {

    @GET("v0/item/{item}.json")
    fun getStory(@Path("item") itemId: String): Single<Item>

    @GET("v0/topstories.json")
    fun getTopStories(): Single<List<String>>
}