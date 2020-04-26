package com.example.inspiringpersonfragment

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mOnButtonClicked: OnButtonClicked? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
    }
    private fun setUpUi() {
        viewPager.adapter = FragmentAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    fun setOnButtonClicked(c: OnButtonClicked) {
        mOnButtonClicked = c
    }

    fun refreshData(){
        viewPager.currentItem = 0
        mOnButtonClicked!!.displayData()
    }
    interface OnButtonClicked {
        fun displayData()
    }
}
