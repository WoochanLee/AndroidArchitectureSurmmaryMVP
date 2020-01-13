package kr.woochan.githubsearch.data.local

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import io.realm.Realm
import kr.woochan.githubsearch.data.local.dao.User
import kr.woochan.githubsearch.data.remote.dto.GithubUsersResponse

object RealmRepository {
    private val realm = Realm.getDefaultInstance()

    private val subjectDataChanged = PublishSubject.create<Unit>()

    fun selectUser(str: String): List<User> {
        return realm.where(User::class.java).contains("name", str).findAll()
    }

    fun hasUser(name: String): Boolean = realm.where(User::class.java).equalTo("name", name).findAll().size > 0

    fun subscribeDataChanged(onNext: () -> Unit): Disposable {
        return subjectDataChanged.subscribe { onNext() }
    }

    fun insertUser(item: GithubUsersResponse.Item) {
        val user = User()
        user.name = item.login
        user.profileUrl = item.avatar_url

        realm.executeTransaction {
            realm.copyToRealm(user)
        }
        subjectDataChanged.onNext(Unit)
    }

    fun removeUser(name: String) {
        val results = realm.where(User::class.java).equalTo("name", name).findAll()
        realm.executeTransaction {
            results.deleteAllFromRealm()
        }
        subjectDataChanged.onNext(Unit)
    }
}