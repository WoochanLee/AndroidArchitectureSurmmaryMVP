package kr.woochan.githubsearch.main

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kr.woochan.githubsearch.R
import kr.woochan.githubsearch.common.base.BaseActivity
import kr.woochan.githubsearch.main.api.ApiFragment
import kr.woochan.githubsearch.main.local.LocalFragment

class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {
    override val layoutId = R.layout.activity_main
    override val contractView = this
    override val presenter = MainPresenter(this)

    lateinit var apiFragment: ApiFragment
    lateinit var localFragment: LocalFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            apiFragment = ApiFragment()
            localFragment = LocalFragment()
            supportFragmentManager.beginTransaction().add(apiFragment, "apiFragment")
            supportFragmentManager.beginTransaction().add(localFragment, "localFragment")
        } else {
            apiFragment = supportFragmentManager.findFragmentByTag("apiFragment") as ApiFragment
            localFragment = supportFragmentManager.findFragmentByTag("localFragment") as LocalFragment
        }

        vp_main.adapter = MainViewPagerAdapter(supportFragmentManager, listOf(apiFragment, localFragment))
        vp_main.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl_main))
        tl_main.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(p0: TabLayout.Tab?) {
                vp_main.currentItem = p0?.position ?: 0
            }
        })
    }
}