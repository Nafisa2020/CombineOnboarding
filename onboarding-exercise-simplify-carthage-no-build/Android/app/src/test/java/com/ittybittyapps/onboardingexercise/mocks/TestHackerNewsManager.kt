package com.ittybittyapps.onboardingexercise.mocks

import com.ittybittyapps.onboardingexercise.data.model.Item
import com.ittybittyapps.onboardingexercise.data.networking.HackerNewsManagerInterface
import io.reactivex.Single

class TestHackerNewsManager : HackerNewsManagerInterface {
    override fun getStory(itemId: String): Single<Item> {
        val item = when (itemId) {
            "2001" -> createStory("Adam")
            "2002" -> createStory("Sally")
            "2003" -> createStory("Oliver")
            "2004" -> createStory("Luke")
            "2005" -> createStory("Vlas")
            else -> createStory("")
        }
        return Single.just(item)
    }

    private fun createStory(by: String): Item.Story =
        Item.Story(
        by = by,
        descendants = 0,
        id = 0,
        kids = listOf(),
        score = 0,
        time = 0,
        title = "",
        type = Item.Type.STORY,
        url = ""
    )

    override fun getTopStoryIds(): Single<List<String>> {
        val storyIds = listOf(
            "2001", "2002", "2003", "2004", "2005",
            "2005", "2005", "2005", "2005", "2005",
            "2005", "2005", "2005", "2005", "2005",
            "2005", "2005", "2005", "2005", "2005"
        )
        return Single.just(storyIds)
    }
}