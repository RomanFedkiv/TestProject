package com.example.roman.testproject.presentation.favourite_person

import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.domain.interactor.DeleteFromFavouriteUseCase
import com.example.roman.testproject.domain.interactor.GetFavouritePersonUseCase
import com.example.roman.testproject.domain.interactor.UpdatePersonUseCase
import com.example.roman.testproject.domain.interactor.utils.completable.Complete
import com.example.roman.testproject.domain.interactor.utils.single.Success
import javax.inject.Inject

class FavouritePersonPresenter @Inject constructor(
        private val view : FavouritePersonContract.View,
        private val getFavouritePersonUseCase: GetFavouritePersonUseCase,
        private val deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase,
        private val updatePersonUseCase: UpdatePersonUseCase
) : FavouritePersonContract.Presenter {
    override fun changeComment(personInfo: PersonInfo) {
        updatePersonUseCase.execute(personInfo) {
            when(it) {
                is Complete -> view.sucessUpdate()
                is Error -> view.showError()
            }
        }
    }

    override fun start() {
        getFavouritePersonUseCase.execute(Unit) {
            when (it){
                is Success -> view.showFavouritePerson(it.result)
                is Error -> view.showError()
            }
        }
    }

    override fun deleteFromFavourite(id: String) {
        deleteFromFavouriteUseCase.execute(id) {
            when (it) {
                is Complete -> {
                    start()
                    view.sucessDelete()
                }
                is Error -> view.showError()
            }
        }
    }

    override fun stop() {
        updatePersonUseCase.dispose()
        getFavouritePersonUseCase.dispose()
        deleteFromFavouriteUseCase.dispose()
    }
}