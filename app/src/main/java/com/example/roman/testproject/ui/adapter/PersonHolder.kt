package com.example.roman.testproject.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.roman.testproject.data.model.PersonInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.person_info_item.view.*

class PersonHolder(
        private val view: View,
        private val clickListener: (PersonInfo) -> Unit
) : RecyclerView.ViewHolder(view) {

    fun bind(personInfo: PersonInfo): Unit = with(itemView) {
        first_name.text = personInfo.firstname + " " + personInfo.lastname
        place_of_work.text = personInfo.placeOfWork
        favourite_button.setOnClickListener{
            clickListener(personInfo)
        }
    }
}