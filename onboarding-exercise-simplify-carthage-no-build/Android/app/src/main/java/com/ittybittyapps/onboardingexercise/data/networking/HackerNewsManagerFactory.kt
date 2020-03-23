package com.ittybittyapps.onboardingexercise.data.networking

import com.ittybittyapps.onboardingexercise.data.networking.adapter.ItemAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

object HackerNewsManagerFactory {

    private val moshi = Moshi.Builder()
        .add(ItemAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()

    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    fun createHackerNewsManager(
        baseUrl: String,
        sslSocketFactory: SSLSocketFactory? = null,
        trustManager: X509TrustManager? = null
    ): HackerNewsManager {
        val okHttpClient = createOkHttpClient(sslSocketFactory, trustManager)
        val retrofitService = createRetrofitService(baseUrl, okHttpClient)
        val hackerNewsService = retrofitService.create(HackerNewsInterface::class.java)
        return HackerNewsManager(hackerNewsService)
    }

    private fun createOkHttpClient(
        sslSocketFactory: SSLSocketFactory? = null,
        trustManager: X509TrustManager? = null
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addSSLSocketFactory(sslSocketFactory, trustManager)
        .build()

    private fun OkHttpClient.Builder.addSSLSocketFactory(
        sslSocketFactory: SSLSocketFactory?,
        trustManager: X509TrustManager?
    ): OkHttpClient.Builder {
        val socketFactoryInstance = sslSocketFactory ?: return this
        val trustManagerInstance = trustManager ?: return this
        return sslSocketFactory(socketFactoryInstance, trustManagerInstance)
    }

    private fun createRetrofitService(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
}
