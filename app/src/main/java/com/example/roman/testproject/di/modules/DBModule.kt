package com.example.roman.testproject.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.example.roman.testproject.cache.db.AppDB
import com.example.roman.testproject.di.NameConfig
import com.example.roman.testproject.di.scopes.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DBModule {

    @Provides
    @PerApplication
    @Named(NameConfig.DB_NAME)
    fun provideDBName() = DB_NAME

    @Provides
    @PerApplication
    fun provideDB(context: Context,
                  @Named(NameConfig.DB_NAME) dbName: String) =
            Room.databaseBuilder(context, AppDB::class.java, dbName)
                    .build()

    @Provides
    @PerApplication
    fun provideNewsDao(db: AppDB) = db.personInfoDao()


    companion object {
        const val DB_NAME = "person.db"
    }

}