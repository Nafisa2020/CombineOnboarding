package com.ittybittyapps.onboardingexercise

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.ittybittyapps.onboardingexercise.screens.StoryListScreen
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers.pathContains
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StoryListActivityTest {

    @Rule
    @JvmField
    val activityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, true, false)

    private val responseCodeOK = 200

    private val storyListScreen = StoryListScreen()

    @Before
    fun setup() {
        RESTMockServer.reset()

        RESTMockServer.whenGET(pathContains(storyListScreen.topStoriesPath))
            .thenReturnFile(responseCodeOK, storyListScreen.topStoriesResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.firstItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.firstItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.secondItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.secondItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.thirdItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.thirdItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.fourthItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.fourthItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.fifthItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.fifthItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.sixthItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.sixthItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.seventhItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.seventhItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.eighthItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.eighthItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.ninthItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.ninthItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.tenthItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.tenthItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.eleventhItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.eleventhItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.twelfthItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.twelfthItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.thirteenthItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.thirteenthItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.fourteenthItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.fourteenthItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.fifteenthItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.fifteenthItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.sixteenthItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.sixteenthItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.seventeenthItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.seventeenthItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.eighteenthItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.eighteenthItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.nineteenthItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.nineteenthItemResponse)

        RESTMockServer.whenGET(pathContains(storyListScreen.twentiethItemPath))
            .thenReturnFile(responseCodeOK, storyListScreen.twentiethItemResponse)
        activityRule.launchActivity(null)
    }

    @Test
    fun testListLoadsFirstItem() {
        storyListScreen.firstItem.check(matches(isDisplayed()))
    }

    @Test
    fun testListLoadsSecondItem() {
        storyListScreen.secondItem.check(matches(isDisplayed()))
    }

    @Test
    fun testListLoadsThirdItem() {
        storyListScreen.thirdItem.check(matches(isDisplayed()))
    }

    @Test
    fun testListLoadsFourthItem() {
        storyListScreen.fourthItem.check(matches(isDisplayed()))
    }

    @Test
    fun testListLoadsFifthItem() {
        storyListScreen.fifthItem.check(matches(isDisplayed()))
    }
}
