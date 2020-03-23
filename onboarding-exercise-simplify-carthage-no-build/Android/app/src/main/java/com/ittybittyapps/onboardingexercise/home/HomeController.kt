package com.ittybittyapps.onboardingexercise.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.ittybittyapps.onboardingexercise.BaseApplication
import com.ittybittyapps.onboardingexercise.R
import com.ittybittyapps.onboardingexercise.util.ScopedController
import com.ittybittyapps.onboardingexercise.util.inflate
import com.ittybittyapps.onboardingexercise.util.plusAssign
import io.reactivex.disposables.CompositeDisposable

class HomeController : ScopedController<HomeViewModel, HomeViewBinder>() {

    private val disposables = CompositeDisposable()

    override fun onCreateViewModel(context: Context): HomeViewModel {
        val app = context.applicationContext as BaseApplication
        val repo = app.serviceContainer.hackerNewsRepository
        return HomeViewModel(repo)
    }

    override fun onCreateView(container: ViewGroup): View = container.inflate(R.layout.home)

    override fun onCreateViewBinder(view: View, viewModel: HomeViewModel) = HomeViewBinder(view)

    override fun onAttach(view: View, binder: HomeViewBinder, viewModel: HomeViewModel) {
        disposables += viewModel.isRefreshing.subscribe(binder::showRefreshing)
        disposables += viewModel.stories.subscribe(binder::display)
        disposables += binder.observeRefreshes().subscribe { viewModel.loadTopStories() }
    }

    override fun onDetach(view: View, binder: HomeViewBinder, viewModel: HomeViewModel) {
        disposables.clear()
    }

    override fun onDestroy(viewModel: HomeViewModel?) {
        viewModel?.destroy()
    }
}
