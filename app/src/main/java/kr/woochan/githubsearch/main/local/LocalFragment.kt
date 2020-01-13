package kr.woochan.githubsearch.main.local

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import kr.woochan.githubsearch.R
import kr.woochan.githubsearch.common.base.BaseFragment
import kr.woochan.githubsearch.data.local.dao.User

class LocalFragment : BaseFragment<LocalContract.View, LocalContract.Presenter>(), LocalContract.View {

    override val layoutId = R.layout.fragment_main
    override val contractView = this
    override val presenter = LocalPresenter(this)

    private lateinit var localRecyclerViewAdapter: LocalRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        localRecyclerViewAdapter = LocalRecyclerViewAdapter()
        rv_main.layoutManager = LinearLayoutManager(context)
        rv_main.adapter = localRecyclerViewAdapter

        et_search.doAfterTextChanged {
            presenter.refreshData(it.toString())
        }

        presenter.subscribeRealmRepository()
        presenter.refreshData(et_search.text.toString())
    }

    override fun refreshData(data: List<User>) {
        localRecyclerViewAdapter.refreshData(data)
    }
}
