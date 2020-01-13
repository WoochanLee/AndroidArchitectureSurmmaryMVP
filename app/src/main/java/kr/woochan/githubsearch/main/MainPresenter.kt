package kr.woochan.githubsearch.main

import io.reactivex.disposables.CompositeDisposable

class MainPresenter(override var view: MainContract.View) : MainContract.Presenter {
    override var compositeDisposable = CompositeDisposable()
}