package kr.woochan.githubsearch.main.local

import io.reactivex.disposables.CompositeDisposable
import kr.woochan.githubsearch.data.local.RealmRepository

class LocalPresenter(override var view: LocalContract.View) : LocalContract.Presenter {

    override var compositeDisposable = CompositeDisposable()

    var filterStr = ""

    override fun subscribeRealmRepository() {
        val disposable = RealmRepository.subscribeDataChanged {
            refreshData(filterStr)
        }
        compositeDisposable.add(disposable)
    }

    override fun refreshData(str: String) {
        filterStr = str
        view.refreshData(RealmRepository.selectUser(str).sortedBy { it.name })
    }
}