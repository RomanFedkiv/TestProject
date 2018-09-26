package com.example.roman.testproject.cache.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.roman.testproject.cache.model.PersonInfoCacheEntity

@Dao
interface PersonInfoDao {

    companion object {
        const val TABLE_NAME = "personInfo"
    }

    @Insert
    fun insertPersonInfo(personCache: PersonInfoCacheEntity)

    @Query("DELETE FROM $TABLE_NAME")
    fun deletePersonInfo()

    @Query("DELETE FROM $TABLE_NAME WHERE idPerson = :id")
    fun deleteById(id : String)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getPersonsInfo(): List<PersonInfoCacheEntity>
}