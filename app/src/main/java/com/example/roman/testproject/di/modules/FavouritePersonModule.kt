package com.example.roman.testproject.di.modules

import com.example.roman.testproject.di.scopes.PerActivity
import com.example.roman.testproject.domain.interactor.AddToFavouriteUseCase
import com.example.roman.testproject.domain.interactor.DeleteFromFavouriteUseCase
import com.example.roman.testproject.domain.interactor.GetFavouritePersonUseCase
import com.example.roman.testproject.domain.interactor.GetPersonInfoUseCase
import com.example.roman.testproject.presentation.favourite_person.FavouritePersonContract
import com.example.roman.testproject.presentation.favourite_person.FavouritePersonPresenter
import com.example.roman.testproject.presentation.search_person.SearchPersonContract
import com.example.roman.testproject.presentation.search_person.SearchPersonPresenter
import com.example.roman.testproject.ui.FavouritePersonActivity
import com.example.roman.testproject.ui.SearchPersonActivity
import dagger.Module
import dagger.Provides

@Module
class FavouritePersonModule {

    @Provides
    @PerActivity
    fun provideView(activity: FavouritePersonActivity) = activity as FavouritePersonContract.View

    @Provides
    @PerActivity
    fun providePresenter(view: FavouritePersonContract.View,
                         getFavouritePersonUseCase: GetFavouritePersonUseCase,
                            deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase) =
            FavouritePersonPresenter(view, getFavouritePersonUseCase,deleteFromFavouriteUseCase) as FavouritePersonContract.Presenter
}