package kr.woochan.githubsearch.common.base

import io.reactivex.disposables.CompositeDisposable

interface BaseActivityContract {
    interface BaseView {

    }

    interface BasePresenter<V : BaseView> {
        var view: V
        var compositeDisposable: CompositeDisposable
    }
}