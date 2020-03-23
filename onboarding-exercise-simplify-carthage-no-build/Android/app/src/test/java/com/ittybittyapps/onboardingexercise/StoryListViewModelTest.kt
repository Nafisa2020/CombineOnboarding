package com.ittybittyapps.onboardingexercise

import com.ittybittyapps.onboardingexercise.data.HackerNewsRepository
import com.ittybittyapps.onboardingexercise.data.model.Item
import com.ittybittyapps.onboardingexercise.home.HomeViewModel
import com.ittybittyapps.onboardingexercise.mocks.TestHackerNewsManager
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StoryListViewModelTest {

    private val repository = HackerNewsRepository(TestHackerNewsManager())
    private val viewModel = HomeViewModel(repository)

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun cleanUp() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testLoadStories() {
        viewModel.loadTopStories()
        assertEquals(viewModel.stories.blockingFirst().size, 20)
    }

    @Test
    fun testFirstLoadedStory() {
        viewModel.loadTopStories()
        val story = viewModel.stories.blockingFirst()[0] as Item.Story
        assertEquals(story.by, "Adam")
    }

    @Test
    fun testSecondLoadedStory() {
        viewModel.loadTopStories()
        val story = viewModel.stories.blockingFirst()[1] as Item.Story
        assertEquals(story.by, "Sally")
    }

    @Test
    fun testThirdLoadedStory() {
        viewModel.loadTopStories()
        val story = viewModel.stories.blockingFirst()[2] as Item.Story
        assertEquals(story.by, "Oliver")
    }

    @Test
    fun testFourthLoadedStory() {
        viewModel.loadTopStories()
        val story = viewModel.stories.blockingFirst()[3] as Item.Story
        assertEquals(story.by, "Luke")
    }

    @Test
    fun testFifthLoadedStory() {
        viewModel.loadTopStories()
        val story = viewModel.stories.blockingFirst()[4] as Item.Story
        assertEquals(story.by, "Vlas")
    }

    @Test
    fun testRefreshingEvents() {
        val observer = viewModel.isRefreshing.test()
        viewModel.loadTopStories()
        observer.assertValues(false, true, false)
    }
}
