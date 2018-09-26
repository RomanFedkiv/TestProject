package com.example.roman.news.di.modules

import android.app.Application
import android.content.Context
import com.example.roman.testproject.cache.PersonInfoCacheImpl
import com.example.roman.testproject.cache.db.dao.PersonInfoDao
import com.example.roman.testproject.cache.mapper.PersonInfoCacheMapper
import com.example.roman.testproject.data.PersonInfoRepositoryImpl
import com.example.roman.testproject.data.repository.PersonInfoCache
import com.example.roman.testproject.data.repository.PersonInfoRemote

import com.example.roman.testproject.di.scopes.PerApplication
import com.example.roman.testproject.domain.IOThreadFactory
import com.example.roman.testproject.domain.UIThreadFactory
import com.example.roman.testproject.domain.repository.PersonInfoRepository
import com.example.roman.testproject.remote.PersonInfoAPI
import com.example.roman.testproject.remote.PersonInfoRemoteImpl
import com.example.roman.testproject.remote.mapper.PersonInfoMapper
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides @PerApplication
    fun provideContext(application: Application) = application as Context

    @Provides @PerApplication
    fun provideIOThreadFactory() = IOThreadFactory

    @Provides @PerApplication
    fun provideUIThreadFactory() = UIThreadFactory

    @Provides @PerApplication
    fun providePersonInfoRemoteImpl(
            api: PersonInfoAPI,
            mapper : PersonInfoMapper
    ) = PersonInfoRemoteImpl(api, mapper) as PersonInfoRemote

    @Provides @PerApplication
    fun providePersonInfoCacheImpl(
            personInfoDao: PersonInfoDao,
            mapper: PersonInfoCacheMapper
    ) = PersonInfoCacheImpl(personInfoDao, mapper) as PersonInfoCache

    @Provides @PerApplication
    fun providePersonInfoRepositoryImpl(
            remote: PersonInfoRemote,
            cache: PersonInfoCache
    ) = PersonInfoRepositoryImpl(remote,cache) as PersonInfoRepository

}
