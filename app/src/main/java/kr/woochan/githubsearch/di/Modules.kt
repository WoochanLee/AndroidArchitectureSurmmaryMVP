package kr.woochan.githubsearch.di

import kr.woochan.githubsearch.data.local.UserRepository
import kr.woochan.githubsearch.data.local.UserRepositoryImpl
import kr.woochan.githubsearch.data.remote.GithubApi
import kr.woochan.githubsearch.data.remote.GithubApiImpl
import kr.woochan.githubsearch.main.MainContract
import kr.woochan.githubsearch.main.MainPresenter
import kr.woochan.githubsearch.main.api.ApiContract
import kr.woochan.githubsearch.main.api.ApiPresenter
import kr.woochan.githubsearch.main.local.LocalContract
import kr.woochan.githubsearch.main.local.LocalPresenter
import org.koin.dsl.module

val appModule = module {

    single<UserRepository> { UserRepositoryImpl }
    single<GithubApi> { GithubApiImpl }
    factory { MainPresenter() as MainContract.Presenter }
    factory { ApiPresenter(get(), get()) as ApiContract.Presenter }
    factory { LocalPresenter(get()) as LocalContract.Presenter }
}