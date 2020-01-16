package kr.woochan.githubsearch

import androidx.multidex.MultiDexApplication
import io.realm.Realm
import kr.woochan.githubsearch.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubSearchApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        startKoin {
            androidContext(this@GithubSearchApplication)
            modules(appModule)
        }
    }
}