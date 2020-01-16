package kr.woochan.githubsearch.main.api

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import kr.woochan.githubsearch.common.base.BaseFragment
import kr.woochan.githubsearch.data.remote.github.dto.GithubUsersResponse
import org.koin.android.ext.android.inject


class ApiFragment : BaseFragment<ApiContract.View, ApiContract.Presenter>(), ApiContract.View {

    override val layoutId = kr.woochan.githubsearch.R.layout.fragment_main
    override val contractView = this
    override val presenter by inject<ApiContract.Presenter>()

    private lateinit var apiRecyclerViewAdapter: ApiRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apiRecyclerViewAdapter = ApiRecyclerViewAdapter()
        rv_main.layoutManager = LinearLayoutManager(context)
        rv_main.adapter = apiRecyclerViewAdapter

        et_search.setOnEditorActionListener { textView, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                presenter.requestGithubUsers(textView.text.toString())
                false
            } else {
                true
            }
        }

        presenter.subscribeRealmRepository()
    }

    override fun notifyDataChanged() {
        apiRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun refreshGithubUsers(users: List<GithubUsersResponse.Item>) {
        apiRecyclerViewAdapter.refreshData(users)
    }
}