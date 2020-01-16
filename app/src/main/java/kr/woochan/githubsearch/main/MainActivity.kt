package kr.woochan.githubsearch.main

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kr.woochan.githubsearch.R
import kr.woochan.githubsearch.common.base.BaseActivity
import kr.woochan.githubsearch.main.api.ApiFragment
import kr.woochan.githubsearch.main.local.LocalFragment
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {
    override val layoutId = R.layout.activity_main
    override val contractView = this
    override val presenter by inject<MainContract.Presenter>()

    private val apiFragment: ApiFragment by inject()
    private val localFragment: LocalFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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