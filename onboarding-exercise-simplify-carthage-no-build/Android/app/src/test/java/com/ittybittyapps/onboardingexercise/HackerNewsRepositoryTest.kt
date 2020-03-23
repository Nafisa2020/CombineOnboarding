package com.ittybittyapps.onboardingexercise

import com.ittybittyapps.onboardingexercise.data.HackerNewsRepository
import com.ittybittyapps.onboardingexercise.mocks.TestHackerNewsManager
import org.junit.Test

class HackerNewsRepositoryTest {

    private val repository = HackerNewsRepository(TestHackerNewsManager())

    @Test
    fun testTopStoriesLoaded() {
        val testObservable = repository
            .latestStoriesObservable
            .test()
        repository.getTopStories()
        testObservable.awaitCount(21)
        testObservable.assertValueCount(21)
    }

    @Test
    fun testIsRefreshing() {
        val testObservable = repository
            .isRefreshingObservable
            .test()
        repository.getTopStories()
        testObservable.awaitCount(2)
        testObservable.assertValueCount(2)
    }
}
