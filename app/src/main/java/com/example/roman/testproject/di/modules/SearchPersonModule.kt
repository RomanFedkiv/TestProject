package com.example.roman.testproject.di.modules

import com.example.roman.testproject.ui.search.SearchPersonActivity
import com.example.roman.testproject.di.scopes.PerActivity
import com.example.roman.testproject.domain.interactor.AddToFavouriteUseCase
import com.example.roman.testproject.domain.interactor.DeleteFromFavouriteUseCase
import com.example.roman.testproject.domain.interactor.GetPersonInfoUseCase
import com.example.roman.testproject.presentation.search_person.SearchPersonContract
import com.example.roman.testproject.presentation.search_person.SearchPersonPresenter
import dagger.Module
import dagger.Provides

@Module
class SearchPersonModule {
    @Provides
    @PerActivity
    fun provideMainView(activity: SearchPersonActivity) = activity as SearchPersonContract.View

    @Provides
    @PerActivity
    fun providePresenter(view: SearchPersonContract.View,
                         getPersonInfoUseCase: GetPersonInfoUseCase,
                         addToFavouriteUseCase: AddToFavouriteUseCase,
                         deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase) =
            SearchPersonPresenter(view, getPersonInfoUseCase, addToFavouriteUseCase,deleteFromFavouriteUseCase) as SearchPersonContract.Presenter
}