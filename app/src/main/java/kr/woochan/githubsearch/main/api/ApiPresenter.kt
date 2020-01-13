package kr.woochan.githubsearch.main.api

import io.reactivex.disposables.CompositeDisposable
import kr.woochan.githubsearch.data.local.RealmRepository
import kr.woochan.githubsearch.data.remote.GithubAPI

class ApiPresenter(override var view: ApiContract.View) : ApiContract.Presenter {

    override var compositeDisposable = CompositeDisposable()

    override fun requestGithubUsers(name: String) {
        val disposable = GithubAPI.requestGithubUsers(name, {
            view.refreshGithubUsers(it.items.sortedBy { item ->
                item.login
            })
        }, {
            view.makeToast("network error")
        })
        compositeDisposable.add(disposable)
    }

    override fun subscribeRealmRepository() {
        val disposable = RealmRepository.subscribeDataChanged {
            view.notifyDataChanged()
        }
        compositeDisposable.add(disposable)
    }
}