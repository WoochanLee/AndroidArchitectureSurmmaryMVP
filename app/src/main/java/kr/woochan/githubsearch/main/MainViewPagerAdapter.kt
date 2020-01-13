package kr.woochan.githubsearch.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MainViewPagerAdapter(fragmentManager: FragmentManager, private val fragment: List<Fragment>)
    : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }

    override fun getCount(): Int {
        return fragment.size
    }
}