package kr.woochan.githubsearch

import androidx.multidex.MultiDexApplication
import io.realm.Realm

class GithubSearchApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}