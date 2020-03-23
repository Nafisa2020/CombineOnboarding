package com.ittybittyapps.onboardingexercise

import android.app.Application
import com.ittybittyapps.onboardingexercise.data.HackerNewsRepository
import com.ittybittyapps.onboardingexercise.data.networking.HackerNewsManager
import com.ittybittyapps.onboardingexercise.data.networking.HackerNewsManagerFactory

open class BaseApplication : Application() {

    lateinit var serviceContainer: ServiceContainer

    override fun onCreate() {
        super.onCreate()

        val repository = HackerNewsRepository(getHackerNewsManager())
        serviceContainer = ServiceContainer(repository)
    }

    protected open fun getHackerNewsManager(): HackerNewsManager =
        HackerNewsManagerFactory.createHackerNewsManager(getString(R.string.api_base_url))
}