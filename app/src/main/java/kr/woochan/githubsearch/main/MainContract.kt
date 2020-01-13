package kr.woochan.githubsearch.main

import kr.woochan.githubsearch.common.base.BaseActivityContract

interface MainContract {
    interface View : BaseActivityContract.BaseView {

    }

    interface Presenter : BaseActivityContract.BasePresenter<View> {

    }
}