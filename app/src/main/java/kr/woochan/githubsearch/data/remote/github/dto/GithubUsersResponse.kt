package kr.woochan.githubsearch.data.remote.github.dto

data class GithubUsersResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Item>
) {
    data class Item(
        val login: String,
        val id: Int,
        val node_id: String,
        val avatar_url: String,
        val gravatar_id: String,
        val url: String,
        val html_url: String,
        val followers_url: String,
        val subscriptions_url: String,
        val organizations_url: String,
        val repos_url: String,
        val received_events_url: String,
        val type: String,
        val score: Float
    )
}