package com.example.inspiringpersonfragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class FragmentAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    private val fragments = arrayOf(
        ShowFragment.newInstance(),
        AddFragment.newInstace()
    )
    private val titles = arrayOf("Persons", "Add")

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
    override fun getCount(): Int {
        return fragments.size
    }
}