package kr.woochan.githubsearch.common.base

import io.reactivex.disposables.CompositeDisposable

interface BaseFragmentContract {
    interface BaseView {
        fun makeToast(content: String)
    }

    interface BasePresenter<V : BaseView> {
        var view: V
        var compositeDisposable: CompositeDisposable
    }
}