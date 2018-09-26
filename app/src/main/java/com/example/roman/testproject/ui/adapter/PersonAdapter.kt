package com.example.roman.testproject.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.roman.testproject.R
import com.example.roman.testproject.data.model.PersonInfo



class PersonAdapter(
        private val clickListener: (PersonInfo) -> Unit
) : RecyclerView.Adapter<PersonHolder>(){

    private var listNews : List<PersonInfo> = listOf()

    fun updateList(list : List<PersonInfo>){
        listNews = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.person_info_item, parent, false)
        return PersonHolder(view, clickListener)
    }

    override fun getItemCount(): Int = listNews.size

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.bind(listNews[position])
    }


}