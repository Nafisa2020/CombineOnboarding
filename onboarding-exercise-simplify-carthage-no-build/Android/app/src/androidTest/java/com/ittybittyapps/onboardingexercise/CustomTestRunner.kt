package com.ittybittyapps.onboardingexercise

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import io.appflate.restmock.android.RESTMockTestRunner
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

class CustomTestRunner : RESTMockTestRunner() {
    override fun newApplication(
        classLoader: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        val testApplicationClassName = TestApplication::class.java.canonicalName
        return super.newApplication(classLoader, testApplicationClassName, context)
    }

    override fun onStart() {
        super.onStart()
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }
    }

    override fun onDestroy() {
        super.onDestroy()
        RxJavaPlugins.reset()
    }
}