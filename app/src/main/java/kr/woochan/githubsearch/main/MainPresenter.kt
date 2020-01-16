package kr.woochan.githubsearch.main

import io.reactivex.disposables.CompositeDisposable

class MainPresenter : MainContract.Presenter {
    override lateinit var view: MainContract.View
    override var compositeDisposable = CompositeDisposable()
}