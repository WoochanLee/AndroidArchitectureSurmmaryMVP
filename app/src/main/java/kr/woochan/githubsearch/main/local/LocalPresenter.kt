package kr.woochan.githubsearch.main.local

import io.reactivex.disposables.CompositeDisposable
import kr.woochan.githubsearch.data.local.UserRepository
import kr.woochan.githubsearch.data.local.UserRepositoryImpl

class LocalPresenter(private val userRepository: UserRepository) : LocalContract.Presenter {
    override lateinit var view: LocalContract.View
    override var compositeDisposable = CompositeDisposable()

    var filterStr = ""

    override fun subscribeRealmRepository() {
        val disposable = userRepository.subscribeDataChanged {
            refreshData(filterStr)
        }
        compositeDisposable.add(disposable)
    }

    override fun refreshData(str: String) {
        filterStr = str
        view.refreshData(UserRepositoryImpl.selectUser(str).sortedBy { it.name })
    }
}