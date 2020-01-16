package kr.woochan.githubsearch.data.remote

import kr.woochan.githubsearch.BuildConfig
import kr.woochan.githubsearch.data.remote.github.GithubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val NETWORK_TIME_OUT = 10L

private fun createOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.connectTimeout(NETWORK_TIME_OUT, java.util.concurrent.TimeUnit.SECONDS)
    builder.readTimeout(NETWORK_TIME_OUT, java.util.concurrent.TimeUnit.SECONDS)
    builder.writeTimeout(NETWORK_TIME_OUT, java.util.concurrent.TimeUnit.SECONDS)
    if (BuildConfig.DEBUG) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
    }
    return builder.build()
}

fun makeServiceRequest(baseUrl: String): GithubService  {
    return Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(createOkHttpClient())
        .build()
        .create(GithubService::class.java)
}