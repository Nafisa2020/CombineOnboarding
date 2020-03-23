package com.ittybittyapps.onboardingexercise.home

import com.ittybittyapps.onboardingexercise.data.HackerNewsRepository
import com.ittybittyapps.onboardingexercise.data.model.Item
import com.ittybittyapps.onboardingexercise.util.plusAssign
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

class HomeViewModel(private val hackerNewsRepository: HackerNewsRepository) {

    private val disposables = CompositeDisposable()

    private val isRefreshingSubject = BehaviorSubject.create<Boolean>()
    val isRefreshing: Observable<Boolean> = isRefreshingSubject.hide()

    private val itemSubject: BehaviorSubject<List<Item>> =
        BehaviorSubject.createDefault(emptyList())
    val stories: Observable<List<Item>> = itemSubject.hide()

    init {
        disposables += hackerNewsRepository.isRefreshingObservable
            .subscribe(isRefreshingSubject::onNext)

        disposables += hackerNewsRepository.latestStoriesObservable
            .subscribe(itemSubject::onNext)

        loadTopStories()
    }

    fun destroy() {
        disposables.clear()
    }

    fun loadTopStories() {
        hackerNewsRepository.getTopStories()
    }
}
