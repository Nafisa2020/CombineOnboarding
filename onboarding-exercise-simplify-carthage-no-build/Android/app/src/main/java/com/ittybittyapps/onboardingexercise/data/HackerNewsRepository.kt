package com.ittybittyapps.onboardingexercise.data

import android.annotation.SuppressLint
import com.ittybittyapps.onboardingexercise.data.model.Item
import com.ittybittyapps.onboardingexercise.data.networking.HackerNewsManagerInterface
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class HackerNewsRepository(private val apiManager: HackerNewsManagerInterface) {

    private val latestStoriesSubject = BehaviorSubject.create<List<Item>>()
    val latestStoriesObservable: Observable<List<Item>> =
        latestStoriesSubject.observeOn(AndroidSchedulers.mainThread())

    private val isRefreshingSubject = BehaviorSubject.create<Boolean>()
    val isRefreshingObservable: Observable<Boolean> =
        isRefreshingSubject.observeOn(AndroidSchedulers.mainThread())

    @SuppressLint("CheckResult")
    fun getTopStories() {
        apiManager.getTopStoryIds()
            .toObservable()
            .flatMap { retrieveFirstTwentyStories(it) }
            .bindRefreshState()
            .subscribeOn(Schedulers.io())
            .subscribe(latestStoriesSubject::onNext)
    }

    private fun Observable<List<Item>>.bindRefreshState(): Observable<List<Item>> =
        doAfterTerminate { isRefreshingSubject.onNext(false) }
            .doOnSubscribe { isRefreshingSubject.onNext(true) }

    private fun retrieveFirstTwentyStories(storyIds: List<String>): Observable<List<Item>> {
        val listToLoad = storyIds.subList(fromIndex = 0, toIndex = 20)
        val storyList = arrayListOf<Item>()
        return Observable.fromIterable(listToLoad)
            .flatMapSingle { getStory(it) }
            .scan(storyList) { accumulator, story ->
                accumulator.add(story)
                return@scan accumulator
            }
            .map { it.toList() }
    }

    private fun getStory(storyId: String): Single<Item> = apiManager.getStory(storyId)
}
