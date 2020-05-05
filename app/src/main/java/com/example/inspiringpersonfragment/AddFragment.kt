package com.example.inspiringpersonfragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.add_person_fragment.*
import java.lang.Exception


class AddFragment : Fragment(), MainActivity.OnEditPerson {

    private var mMain: MainActivity? = null
    private var newPersonId : Int = -1
    companion object{
        fun newInstace(): AddFragment{
            return AddFragment()
        }
    }

    override fun onActivityCreated(savedState: Bundle?) {
        super.onActivityCreated(savedState)
        mMain = activity as MainActivity?
        mMain!!.setOnEdit(this)
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
        val quotes = newQuotes.text.toString().split(";")

        if(name.isNotBlank() && date.isNotBlank() && descr.isNotBlank()){
            val personId = if(newPersonId==-1)InspiringPersonRepository.persons.maxBy { p -> p.id }!!.id + 1 else newPersonId
            val newPerson = Person(personId,name,date,descr,img, quotes)
            try{InspiringPersonRepository.remove(personId)} catch (e : Exception){}
            InspiringPersonRepository.add(newPerson)
            Toast.makeText(context,"Added!", Toast.LENGTH_SHORT).show()
            clearFields()
            mMain!!.refreshData()
        }else Toast.makeText(context,"Empty field!", Toast.LENGTH_SHORT).show()
    }

    private fun clearFields() {
        newName.text.clear()
        newDate.text.clear()
        newDescription.text.clear()
        newImage.text.clear()
        newQuotes.text.clear()
        newPersonId = -1
    }

    override fun editPerson(id: Int) {
       var person =  InspiringPersonRepository.get(id)
        newName.setText(person!!.name)
        newDate.setText(person!!.date)
        newDescription.setText(person.descr)
        newImage.setText(person.image)
        newQuotes.setText(person.qoutes.joinToString(separator = " ; "))
        newPersonId = id
    }


}