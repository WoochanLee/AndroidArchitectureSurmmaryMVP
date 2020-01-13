package kr.woochan.githubsearch.main.api

import kr.woochan.githubsearch.common.base.BaseFragmentContract
import kr.woochan.githubsearch.data.remote.dto.GithubUsersResponse

interface ApiContract {
    interface View : BaseFragmentContract.BaseView {
        fun refreshGithubUsers(users: List<GithubUsersResponse.Item>)
        fun notifyDataChanged()
    }

    interface Presenter : BaseFragmentContract.BasePresenter<View> {
        fun requestGithubUsers(name: String)
        fun subscribeRealmRepository()
    }
}