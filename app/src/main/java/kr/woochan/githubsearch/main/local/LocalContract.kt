package kr.woochan.githubsearch.main.local

import kr.woochan.githubsearch.common.base.BaseFragmentContract
import kr.woochan.githubsearch.data.local.dao.User

interface LocalContract {
    interface View : BaseFragmentContract.BaseView {
        fun refreshData(data: List<User>)
    }

    interface Presenter : BaseFragmentContract.BasePresenter<View> {
        fun subscribeRealmRepository()
        fun refreshData(str: String = "")
    }
}