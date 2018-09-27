package com.example.roman.testproject.data.repository

import com.example.roman.testproject.data.model.PersonInfo
import io.reactivex.Completable
import io.reactivex.Single

interface PersonInfoCache {

    fun savePersonInfo(personInfo: PersonInfo): Completable

    fun updatePersonById(comment : String, id: String) : Completable

    fun getPersonsInfo(): Single<List<PersonInfo>>

    fun deleteById(id : String) : Completable
}