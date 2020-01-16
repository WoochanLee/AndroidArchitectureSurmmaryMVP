package kr.woochan.githubsearch.data.remote.github

import io.reactivex.disposables.Disposable
import kr.woochan.githubsearch.data.remote.github.dto.GithubUsersResponse

interface GithubApi {

    fun requestGithubUsers(
        name: String,
        onResponse: (GithubUsersResponse) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable
}