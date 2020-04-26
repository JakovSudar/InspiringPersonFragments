package com.example.inspiringpersonfragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.add_person_fragment.*


class AddFragment : Fragment() {

    private var mMain: MainActivity? = null

    companion object{
        fun newInstace(): AddFragment{
            return AddFragment()
        }
    }

    override fun onAttach(a: Activity) {
        super.onAttach(a)
        mMain = a as MainActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_person_fragment, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addBtn.setOnClickListener{addPerson()}
    }

    private fun addPerson() {
        val name = newName.text.toString()
        val date = newDate.text.toString()
        val descr = newDescription.text.toString()
        val img = newImage.text.toString()
        if(name.isNotBlank() && date.isNotBlank() && descr.isNotBlank()){
            val newPeson = Person(InspiringPersonRepository.persons.size,name,date,descr,img, listOf("New persons doesn't have quotes."))
            InspiringPersonRepository.add(newPeson)
            Toast.makeText(context,"Added!", Toast.LENGTH_SHORT).show()
            newName.text.clear()
            newDate.text.clear()
            newDescription.text.clear()
            newImage.text.clear()

            mMain!!.refreshData()
        }else Toast.makeText(context,"Empty field!", Toast.LENGTH_SHORT).show()

    }




}