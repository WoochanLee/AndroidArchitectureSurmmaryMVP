package kr.woochan.githubsearch.main.api

import io.reactivex.disposables.CompositeDisposable
import kr.woochan.githubsearch.data.local.UserRepository
import kr.woochan.githubsearch.data.remote.github.GithubApi

class ApiPresenter(private val githubApi: GithubApi, private val userRepository: UserRepository) : ApiContract.Presenter {
    override lateinit var view: ApiContract.View
    override var compositeDisposable = CompositeDisposable()

    override fun requestGithubUsers(name: String) {
        val disposable = githubApi.requestGithubUsers(name, {
            view.refreshGithubUsers(it.items.sortedBy { item ->
                item.login
            })
        }, {
            view.makeToast("network error")
        })
        compositeDisposable.add(disposable)
    }

    override fun subscribeRealmRepository() {
        val disposable = userRepository.subscribeDataChanged {
            view.notifyDataChanged()
        }
        compositeDisposable.add(disposable)
    }
}