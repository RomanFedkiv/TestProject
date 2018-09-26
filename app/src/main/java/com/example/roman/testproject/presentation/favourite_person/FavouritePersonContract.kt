package com.example.roman.testproject.presentation.favourite_person

import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.presentation.BasePresenter
import com.example.roman.testproject.presentation.BaseView

interface FavouritePersonContract {

    interface View : BaseView<Presenter> {

        fun showFavouritePerson(list : List<PersonInfo>)

        fun showError()

        fun sucessDelete()
    }

    interface Presenter : BasePresenter {

        fun deleteFromFavourite(id : String)
    }
}