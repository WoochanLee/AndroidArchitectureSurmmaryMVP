package kr.woochan.githubsearch.data.local

import io.reactivex.disposables.Disposable
import kr.woochan.githubsearch.data.local.dao.User
import kr.woochan.githubsearch.data.remote.github.dto.GithubUsersResponse

interface UserRepository {

    fun selectUser(str: String): List<User>

    fun hasUser(name: String): Boolean

    fun subscribeDataChanged(onNext: () -> Unit): Disposable

    fun insertUser(item: GithubUsersResponse.Item)

    fun deleteUser(name: String)
}