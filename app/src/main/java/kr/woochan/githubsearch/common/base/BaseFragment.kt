package kr.woochan.githubsearch.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : BaseFragmentContract.BaseView, P : BaseFragmentContract.BasePresenter<V>>
    : Fragment(), BaseFragmentContract.BaseView {
    abstract val layoutId: Int
    abstract val contractView: V
    abstract val presenter: P

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(layoutId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view = contractView
    }

    override fun onDestroy() {
        presenter.compositeDisposable.dispose()
        super.onDestroy()
    }

    override fun makeToast(content: String) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }
}