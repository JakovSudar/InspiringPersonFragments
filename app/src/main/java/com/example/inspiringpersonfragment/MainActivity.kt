package com.example.inspiringpersonfragment

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mOnUpdateList: OnUpdateList? = null
    private var mOnEditPerson: OnEditPerson? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
    }
    private fun setUpUi() {
        viewPager.adapter = FragmentAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    fun setOnUpdateList(c: OnUpdateList) {
        mOnUpdateList = c
    }
    fun setOnEdit(c :OnEditPerson){
        mOnEditPerson = c
    }

    //pozivaju se iz fragmenata
    fun refreshData(){
        viewPager.currentItem = 0
        mOnUpdateList!!.displayData()
    }
    fun editPerson(id: Int){
        viewPager.currentItem = 1
        mOnEditPerson!!.editPerson(id)
    }

    //Implemetiraju ih fragmenti
    interface OnUpdateList {
        fun displayData()
    }
    interface OnEditPerson{
        fun editPerson(id:Int);
    }


}
