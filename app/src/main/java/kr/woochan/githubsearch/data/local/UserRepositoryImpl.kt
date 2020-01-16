package kr.woochan.githubsearch.data.local

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import io.realm.Realm
import kr.woochan.githubsearch.data.local.dao.User
import kr.woochan.githubsearch.data.remote.github.dto.GithubUsersResponse

object UserRepositoryImpl: UserRepository {
    private val realm = Realm.getDefaultInstance()

    private val subjectDataChanged = PublishSubject.create<Unit>()

    override fun selectUser(str: String): List<User> {
        return realm.where(User::class.java).contains("name", str).findAll()
    }

    override fun hasUser(name: String): Boolean = realm.where(User::class.java).equalTo("name", name).findAll().size > 0

    override fun subscribeDataChanged(onNext: () -> Unit): Disposable {
        return subjectDataChanged.subscribe { onNext() }
    }

    override fun insertUser(item: GithubUsersResponse.Item) {
        val user = User()
        user.name = item.login
        user.profileUrl = item.avatar_url

        realm.executeTransaction {
            realm.copyToRealm(user)
        }
        subjectDataChanged.onNext(Unit)
    }

    override fun deleteUser(name: String) {
        val results = realm.where(User::class.java).equalTo("name", name).findAll()
        realm.executeTransaction {
            results.deleteAllFromRealm()
        }
        subjectDataChanged.onNext(Unit)
    }
}