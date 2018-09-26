package com.example.roman.news.di.modules


import com.example.roman.testproject.di.modules.FavouritePersonModule
import com.example.roman.testproject.ui.SearchPersonActivity
import com.example.roman.testproject.di.modules.SearchPersonModule
import com.example.roman.testproject.di.scopes.PerActivity
import com.example.roman.testproject.ui.FavouritePersonActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [SearchPersonModule::class])
    @PerActivity
    abstract fun bindSearchPersonActivity(): SearchPersonActivity

    @ContributesAndroidInjector(modules = [FavouritePersonModule::class])
    @PerActivity
    abstract fun bindFavouritePersonActivity(): FavouritePersonActivity

}
