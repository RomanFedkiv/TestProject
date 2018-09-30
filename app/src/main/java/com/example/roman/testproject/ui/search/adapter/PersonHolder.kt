package com.example.roman.testproject.ui.search.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.roman.testproject.R
import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.ui.Config
import kotlinx.android.synthetic.main.person_info_item.view.*

class PersonHolder(
        private val view: View,
        private val clickListener: (PersonInfo,String) -> Unit
) : RecyclerView.ViewHolder(view) {

    fun bind(personInfo: PersonInfo): Unit = with(itemView) {
        var flagButton  = false
        first_name.text = personInfo.firstname + " " + personInfo.lastname
        place_of_work.text = personInfo.placeOfWork
        favourite_button.setOnClickListener{
            if (!flagButton)
            {
                favourite_button.setImageResource(R.drawable.ic_star_black_24dp)
                flagButton = !flagButton
                clickListener(personInfo,Config.FAVOURITE_BUTTON_PRESSED)
            }
            else {
                favourite_button.setImageResource(R.drawable.ic_star_border_black_24dp)
                flagButton = !flagButton
                clickListener(personInfo,Config.FAVOURITE_BUTTON_DEFAULT)
            }
        }
        pdf_button.setOnClickListener{
            clickListener(personInfo,Config.PDF_BUTTON_PRESSED)
        }
    }
}