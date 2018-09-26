package com.example.roman.testproject.di.modules

import com.example.roman.testproject.di.NameConfig
import com.example.roman.testproject.di.scopes.PerApplication
import com.example.roman.testproject.remote.PersonInfoAPI
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class APIModule {

    @Provides @PerApplication
    @Named(NameConfig.BASE_URL)
    fun provideBaseUrl() = "https://public-api.nazk.gov.ua/v1/"

    @Provides @PerApplication
    fun provideGsonFactory() = GsonConverterFactory.create()

    @Provides @PerApplication
    fun provideRxAdapterFactory() = RxJava2CallAdapterFactory.create()

    @Provides @PerApplication
    fun provideRetrofit(@Named(NameConfig.BASE_URL) baseUrl: String,
                        converter: GsonConverterFactory,
                        adapter: RxJava2CallAdapterFactory) =
            Retrofit.Builder()
                    .addConverterFactory(converter)
                    .addCallAdapterFactory(adapter)
                    .baseUrl(baseUrl)
                    .build()

    @Provides @PerApplication
    fun provideRozkladAPI(retrofit: Retrofit) = retrofit.create(PersonInfoAPI::class.java)
}
