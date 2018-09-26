package com.example.roman.testproject.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.roman.testproject.R
import com.example.roman.testproject.data.model.PersonInfo

class FavouritePersonAdapter (
        private val clickListener: (PersonInfo, String) -> Unit
) : RecyclerView.Adapter<FavouritePersonHolder>(){

    private var listNews : List<PersonInfo> = listOf()

    fun updateList(list : List<PersonInfo>){
        listNews = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritePersonHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.favourite_person_item, parent, false)
        return FavouritePersonHolder(view, clickListener)
    }

    override fun getItemCount(): Int = listNews.size

    override fun onBindViewHolder(holder: FavouritePersonHolder, position: Int) {
        holder.bind(listNews[position])
    }


}