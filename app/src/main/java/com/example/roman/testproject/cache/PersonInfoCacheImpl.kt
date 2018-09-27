package com.example.roman.testproject.cache

import com.example.roman.testproject.cache.db.dao.PersonInfoDao
import com.example.roman.testproject.cache.mapper.PersonInfoCacheMapper
import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.data.repository.PersonInfoCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PersonInfoCacheImpl @Inject constructor(
        private val personInfoDao: PersonInfoDao,
        private val mapper: PersonInfoCacheMapper
) : PersonInfoCache {
    override fun updatePersonById(comment: String, id: String) = completableCall {
        personInfoDao.updateCommentById(comment,id)
    }

    override fun savePersonInfo(personInfo: PersonInfo) = completableCall{
        personInfoDao.insertPersonInfo(mapper.mapToCache(personInfo))
    }

    override fun getPersonsInfo(): Single<List<PersonInfo>> = singleCall {
        personInfoDao.getPersonsInfo().map(mapper::mapFromCache)

    }

    override fun deleteById(id: String) = completableCall{
        personInfoDao.deleteById(id)
    }

}