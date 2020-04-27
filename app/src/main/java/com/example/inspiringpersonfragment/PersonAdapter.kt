package com.example.inspiringpersonfragment

import com.example.inspiringpersonfragment.Person

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_person.view.*
import java.util.concurrent.ThreadLocalRandom
class PersonAdapter(): RecyclerView.Adapter<PersonHolder>()  {
    private val persons: MutableList<Person> = mutableListOf()
    private var onPersonDelete : OnPersonDelete? = null
    private var onEditPerson : OnEditPerson? = null

    public fun setOnPersonDeleteListener(l : OnPersonDelete){
        this.onPersonDelete = l
    }
    public fun setOnPersonEditListener(l: OnEditPerson){
        this.onEditPerson = l
    }

    init {
        this.persons.addAll(persons)
    }

    public interface OnEditPerson{
        fun onEditPerson(id:Int)
    }
    public interface OnPersonDelete{
        fun onPersonDelete()
    }

    fun refreshData(persons: MutableList<Person>){
        this.persons.clear()
        this.persons.addAll(persons)
        this.persons.sortBy { p-> p.id }
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        val personView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person,parent,false)
        return PersonHolder(personView)
    }

    override fun getItemCount(): Int {
        return this.persons.size
    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        val person = persons[position]
        holder.bind(person, onPersonDelete, onEditPerson)
    }
}

class PersonHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bind (person: Person,onDelete: PersonAdapter.OnPersonDelete?, onEditPerson: PersonAdapter.OnEditPerson?){
        itemView.personName.text = person.name
        itemView.personDate.text = "Data of birth: "+ person.date
        itemView.personDescr.text = person.descr

        Glide.with(itemView.context).load(person.image)
            .error(R.drawable.noimage)
            .into(itemView.personImage)
        itemView.personImage.setOnClickListener() {
            Toast.makeText(it.context,getRandomQoute(person),Toast.LENGTH_LONG).show()
        }
        itemView.deleteBtn.setOnClickListener(){
            InspiringPersonRepository.remove(person.id )
            onDelete!!.onPersonDelete()
        }
        itemView.editBtn.setOnClickListener(){
            onEditPerson!!.onEditPerson(person.id)
        }
    }
    fun getRandomQoute(person: Person): String{
        val randomInteger = (person.qoutes.indices).shuffled().first()
        return person.qoutes[randomInteger]
    }
}

