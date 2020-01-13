package kr.woochan.githubsearch.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<V : BaseActivityContract.BaseView, P : BaseActivityContract.BasePresenter<V>> : AppCompatActivity() {
    abstract val layoutId: Int
    abstract val contractView: V
    abstract val presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        presenter.view = contractView
    }

    override fun onDestroy() {
        presenter.compositeDisposable.dispose()
        super.onDestroy()
    }
}