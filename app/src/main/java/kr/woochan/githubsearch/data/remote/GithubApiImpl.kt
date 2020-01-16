package kr.woochan.githubsearch.data.remote

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kr.woochan.githubsearch.BuildConfig
import kr.woochan.githubsearch.data.remote.dto.GithubUsersResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GithubApiImpl: GithubApi {

    private const val BASE_URL = "https://api.github.com/"
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

    private val githubServiceRequest by lazy {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .build()
            .create(GithubService::class.java)
    }

    override fun requestGithubUsers(
        name: String,
        onResponse: (GithubUsersResponse) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return githubServiceRequest
            .requestGitHubUsers(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onResponse(it)
            }, {
                onError(it)
            })
    }
}