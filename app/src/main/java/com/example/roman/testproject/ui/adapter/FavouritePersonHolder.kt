package com.example.roman.testproject.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.roman.testproject.data.model.PersonInfo
import kotlinx.android.synthetic.main.favourite_person_item.view.*

class FavouritePersonHolder(
        private val view: View,
        private val clickListener: (PersonInfo,String) -> Unit
) : RecyclerView.ViewHolder(view) {

    fun bind(personInfo: PersonInfo): Unit = with(itemView) {
        first_name.text = personInfo.firstname + " " + personInfo.lastname
        place_of_work.text = personInfo.placeOfWork
        comment.text = personInfo.comment
        favourite_button.setOnClickListener{
            clickListener(personInfo, "favourite_button")
        }
        pdf_button.setOnClickListener{
            clickListener(personInfo, "pdf_button")
        }
    }
}