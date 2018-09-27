package com.example.roman.testproject.domain.repository

import com.example.roman.testproject.data.model.PersonInfo
import io.reactivex.Completable
import io.reactivex.Single

interface PersonInfoRepository {

    fun getPersonInfo(query : String) : Single<List<PersonInfo>>

    fun addToDB(personInfo: PersonInfo) : Completable

    fun getFavouritePerson() : Single<List<PersonInfo>>

    fun deleteFromFavourite(id : String) : Completable

    fun updatePersonByComment(comment : String, id : String) : Completable
}