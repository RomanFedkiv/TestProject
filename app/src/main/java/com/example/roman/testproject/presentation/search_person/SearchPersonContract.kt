package com.example.roman.testproject.presentation.search_person

import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.presentation.BasePresenter
import com.example.roman.testproject.presentation.BaseView

interface SearchPersonContract {

    interface View : BaseView<Presenter> {

        fun showPersonInfo(listPerson: List<PersonInfo>)

        fun showError()

        fun successAdd()

        fun showLoading()

        fun hideLoading()

        fun sucessDelete()

    }

    interface Presenter : BasePresenter {

        fun initSearch(query : String)

        fun addToFavourite(personInfo: PersonInfo)

        fun deleteFromFavourite(id : String)
    }
}