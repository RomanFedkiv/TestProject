package com.example.roman.testproject.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.roman.testproject.cache.db.dao.PersonInfoDao
import com.example.roman.testproject.cache.model.PersonInfoCacheEntity

@Database(entities = [
PersonInfoCacheEntity::class
], version = 1)
abstract class AppDB : RoomDatabase() {

    abstract fun personInfoDao(): PersonInfoDao

}