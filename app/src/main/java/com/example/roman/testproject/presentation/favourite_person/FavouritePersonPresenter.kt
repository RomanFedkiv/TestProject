package com.example.roman.testproject.presentation.favourite_person

import com.example.roman.testproject.domain.interactor.DeleteFromFavouriteUseCase
import com.example.roman.testproject.domain.interactor.GetFavouritePersonUseCase
import com.example.roman.testproject.domain.interactor.utils.completable.Complete
import com.example.roman.testproject.domain.interactor.utils.single.Success
import javax.inject.Inject

class FavouritePersonPresenter @Inject constructor(
        private val view : FavouritePersonContract.View,
        private val getFavouritePersonUseCase: GetFavouritePersonUseCase,
        private val deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase
) : FavouritePersonContract.Presenter {

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}