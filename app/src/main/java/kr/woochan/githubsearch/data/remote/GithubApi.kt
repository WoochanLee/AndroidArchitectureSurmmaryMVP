package kr.woochan.githubsearch.data.remote

import io.reactivex.disposables.Disposable
import kr.woochan.githubsearch.data.remote.dto.GithubUsersResponse

interface GithubApi {

    fun requestGithubUsers(
        name: String,
        onResponse: (GithubUsersResponse) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable
}