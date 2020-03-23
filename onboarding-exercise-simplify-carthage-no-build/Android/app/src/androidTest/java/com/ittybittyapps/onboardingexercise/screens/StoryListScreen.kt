package com.ittybittyapps.onboardingexercise.screens

import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import com.ittybittyapps.onboardingexercise.R
import org.hamcrest.CoreMatchers

class StoryListScreen {

    val topStoriesPath = "/v0/topstories.json"
    val topStoriesResponse = "topstories.json"

    val firstItemPath = "/v0/item/18430922.json"
    val firstItemResponse = "18430922.json"
    val firstItem = Espresso.onView(
        CoreMatchers.allOf(
            ViewMatchers.withId(R.id.story_title),
            ViewMatchers.withText("Aardman Animation Is Giving the Company to Their Employees")
        )
    )

    val secondItemPath = "/v0/item/18435834.json"
    val secondItemResponse = "18435834.json"
    val secondItem = Espresso.onView(
        CoreMatchers.allOf(
            ViewMatchers.withId(R.id.story_title),
            ViewMatchers.withText("How to self-publish a book: A handy list of resources")
        )
    )

    val thirdItemPath = "/v0/item/18434705.json"
    val thirdItemResponse = "18434705.json"
    val thirdItem = Espresso.onView(
        CoreMatchers.allOf(
            ViewMatchers.withId(R.id.story_title),
            ViewMatchers.withText("Web.dev by Google")
        )
    )

    val fourthItemPath = "/v0/item/18426164.json"
    val fourthItemResponse = "18426164.json"
    val fourthItem = Espresso.onView(
        CoreMatchers.allOf(
            ViewMatchers.withId(R.id.story_title),
            ViewMatchers.withText("Armistice Day: November 11, 1918 to November 11, 2018")
        )
    )

    val fifthItemPath = "/v0/item/18433548.json"
    val fifthItemResponse = "18433548.json"
    val fifthItem = Espresso.onView(
        CoreMatchers.allOf(
            ViewMatchers.withId(R.id.story_title),
            ViewMatchers.withText("The poetry and brief life of a Foxconn worker")
        )
    )

    val sixthItemPath = "/v0/item/18431382.json"
    val sixthItemResponse = "18431382.json"

    val seventhItemPath = "/v0/item/18432255.json"
    val seventhItemResponse = "18432255.json"

    val eighthItemPath = "/v0/item/18436735.json"
    val eighthItemResponse = "18436735.json"

    val ninthItemPath = "/v0/item/18433655.json"
    val ninthItemResponse = "18433655.json"

    val tenthItemPath = "/v0/item/18434639.json"
    val tenthItemResponse = "18434639.json"

    val eleventhItemPath = "/v0/item/18437065.json"
    val eleventhItemResponse = "18437065.json"

    val twelfthItemPath = "/v0/item/18438607.json"
    val twelfthItemResponse = "18438607.json"

    val thirteenthItemPath = "/v0/item/18434472.json"
    val thirteenthItemResponse = "18434472.json"

    val fourteenthItemPath = "/v0/item/18433144.json"
    val fourteenthItemResponse = "18433144.json"

    val fifteenthItemPath = "/v0/item/18436999.json"
    val fifteenthItemResponse = "18436999.json"

    val sixteenthItemPath = "/v0/item/18433883.json"
    val sixteenthItemResponse = "18433883.json"

    val seventeenthItemPath = "/v0/item/18437066.json"
    val seventeenthItemResponse = "18437066.json"

    val eighteenthItemPath = "/v0/item/18438295.json"
    val eighteenthItemResponse = "18438295.json"

    val nineteenthItemPath = "/v0/item/18434156.json"
    val nineteenthItemResponse = "18434156.json"

    val twentiethItemPath = "/v0/item/18434588.json"
    val twentiethItemResponse = "18434588.json"
}
