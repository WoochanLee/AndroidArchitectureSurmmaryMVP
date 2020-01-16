package kr.woochan.githubsearch.data.remote.github

import io.reactivex.disposables.Disposable
import kr.woochan.githubsearch.data.remote.github.dto.GithubUsersResponse
import kr.woochan.githubsearch.data.remote.makeNetworkRequestTail
import kr.woochan.githubsearch.data.remote.makeServiceRequest

object GithubApiImpl: GithubApi {

    private const val BASE_URL = "https://api.github.com/"

    private val githubServiceRequest by lazy {
        makeServiceRequest(BASE_URL)
    }

    override fun requestGithubUsers(
        name: String,
        onResponse: (GithubUsersResponse) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return makeNetworkRequestTail(githubServiceRequest.requestGitHubUsers(name), onResponse, onError)
    }
}