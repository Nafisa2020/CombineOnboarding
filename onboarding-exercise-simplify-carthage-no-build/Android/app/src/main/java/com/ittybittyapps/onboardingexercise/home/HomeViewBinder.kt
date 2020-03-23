package com.ittybittyapps.onboardingexercise.home

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ittybittyapps.onboardingexercise.R
import com.ittybittyapps.onboardingexercise.data.model.Item
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import io.reactivex.Observable

class HomeViewBinder(view: View) {

    private val refreshLayout: SwipeRefreshLayout = view.findViewById(R.id.home_swipeRefresher)
    private val adapter = ListItemAdapter()

    init {
        val recycler: RecyclerView = view.findViewById(R.id.home_recycler)
        recycler.layoutManager = LinearLayoutManager(view.context)
        recycler.adapter = adapter
    }

    fun observeRefreshes(): Observable<Unit> = refreshLayout.refreshes()

    fun showRefreshing(refreshing: Boolean) {
        refreshLayout.isRefreshing = refreshing
    }

    fun display(items: List<Item>) {
        adapter.set(items)
    }
}
