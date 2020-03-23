package com.ittybittyapps.onboardingexercise

import com.ittybittyapps.onboardingexercise.data.networking.HackerNewsManager
import com.ittybittyapps.onboardingexercise.data.networking.HackerNewsManagerFactory
import io.appflate.restmock.RESTMockServer

class TestApplication : BaseApplication() {
    override fun getHackerNewsManager(): HackerNewsManager =
        HackerNewsManagerFactory.createHackerNewsManager(
            RESTMockServer.getUrl(),
            RESTMockServer.getSSLSocketFactory(),
            RESTMockServer.getTrustManager()
        )
}