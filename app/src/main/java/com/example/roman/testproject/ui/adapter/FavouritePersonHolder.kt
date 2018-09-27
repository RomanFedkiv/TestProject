package com.example.roman.testproject.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.ui.Config
import kotlinx.android.synthetic.main.dialog_comment.*
import kotlinx.android.synthetic.main.favourite_person_item.view.*

class FavouritePersonHolder(
        private val view: View,
        private val clickListener: (PersonInfo,String) -> Unit
) : RecyclerView.ViewHolder(view) {

    fun bind(personInfo: PersonInfo): Unit = with(itemView) {
        first_name.text = personInfo.firstname + " " + personInfo.lastname
        place_of_work.text = personInfo.placeOfWork
        comment.setText(personInfo.comment,TextView.BufferType.EDITABLE)
        favourite_button.setOnClickListener{
            clickListener(personInfo, Config.FAVOURITE_BUTTON_PRESSED)
        }
        pdf_button.setOnClickListener{
            clickListener(personInfo, Config.PDF_BUTTON_PRESSED)
        }
        comment.setOnFocusChangeListener{ view: View, b: Boolean ->
            if (!b)
            {
                personInfo.comment = comment.text.toString()
                Log.i("of", personInfo.comment)
                clickListener(personInfo, Config.COMMENT)
            }
        }
    }
}