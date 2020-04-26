package com.example.inspiringpersonfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.show_persons_fragment.*

class ShowFragment : Fragment(), MainActivity.OnButtonClicked {

    private var mMain: MainActivity? = null

    companion object{
        fun newInstance():ShowFragment{
            return ShowFragment()
        }
    }

    override fun onActivityCreated(savedState: Bundle?) {
        super.onActivityCreated(savedState)
        mMain = activity as MainActivity?
        mMain!!.setOnButtonClicked(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):View?{
        val view = inflater.inflate(R.layout.show_persons_fragment, container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        displayData()
    }

    override fun displayData() {
        val adapter = PersonAdapter()
        recycler.adapter = adapter
        adapter.refreshData(InspiringPersonRepository.persons)
    }
}