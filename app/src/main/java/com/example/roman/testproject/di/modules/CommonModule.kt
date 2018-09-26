package com.example.roman.news.di.modules


import com.example.roman.testproject.di.scopes.PerApplication
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class CommonModule {

    @Provides @PerApplication
    fun provideGson() = Gson()
}