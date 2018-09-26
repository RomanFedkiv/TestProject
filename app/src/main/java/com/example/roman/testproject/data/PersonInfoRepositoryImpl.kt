package com.example.roman.testproject.data

import android.util.Log
import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.data.repository.PersonInfoCache
import com.example.roman.testproject.data.repository.PersonInfoRemote
import com.example.roman.testproject.domain.repository.PersonInfoRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PersonInfoRepositoryImpl @Inject constructor(
        private val remote : PersonInfoRemote,
        private val cache : PersonInfoCache
): PersonInfoRepository {

    override fun getPersonInfo(query : String) = remote.getPersonInfo(query)
                .flatMap { Single.just(it) }

    override fun addToDB(personInfo: PersonInfo) = cache.savePersonInfo(personInfo)

    override fun getFavouritePerson() = cache.getPersonsInfo()

    override fun deleteFromFavourite(id: String) = cache.deleteById(id)
}
