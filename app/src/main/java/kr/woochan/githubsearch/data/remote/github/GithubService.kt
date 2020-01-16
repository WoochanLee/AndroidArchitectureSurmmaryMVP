package kr.woochan.githubsearch.data.remote.github

import io.reactivex.Flowable
import kr.woochan.githubsearch.data.remote.github.dto.GithubUsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("search/users")
    fun requestGitHubUsers(
        @Query("q") name: String
    ): Flowable<GithubUsersResponse>

}