package com.example.roman.testproject.presentation.search_person

import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.domain.interactor.AddToFavouriteUseCase
import com.example.roman.testproject.domain.interactor.DeleteFromFavouriteUseCase
import com.example.roman.testproject.domain.interactor.GetPersonInfoUseCase
import com.example.roman.testproject.domain.interactor.utils.completable.Complete
import com.example.roman.testproject.domain.interactor.utils.single.Success
import com.example.roman.testproject.presentation.search_person.SearchPersonContract
import javax.inject.Inject

class SearchPersonPresenter @Inject constructor(
        private val view : SearchPersonContract.View,
        private val getPersonInfoUseCase: GetPersonInfoUseCase,
        private val addToFavouriteUseCase: AddToFavouriteUseCase,
        private val deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase
) : SearchPersonContract.Presenter{

    override fun start() {

    }

    override fun initSearch(query: String) {
        getPersonInfoUseCase.execute(query) {
            when (it) {
                is Success -> view.showPersonInfo(it.result)
                is Error -> view.showError()
            }
        }
    }

    override fun addToFavourite(personInfo: PersonInfo) {
        addToFavouriteUseCase.execute(personInfo) {
            when (it) {
                is Complete -> view.successAdd()
                is Error -> view.showError()
            }
        }
    }
    override fun deleteFromFavourite(id: String) {
        deleteFromFavouriteUseCase.execute(id) {
            when (it) {
                is Complete -> { view.sucessDelete() }
                is Error -> view.showError()
            }
        }
    }

    override fun stop() {

    }

}