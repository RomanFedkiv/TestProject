package com.example.roman.testproject.cache.mapper

import com.example.roman.testproject.cache.model.PersonInfoCacheEntity
import com.example.roman.testproject.data.model.PersonInfo
import javax.inject.Inject

class PersonInfoCacheMapper @Inject constructor() : Mapper<PersonInfoCacheEntity, PersonInfo> {

    override fun mapFromCache(cache: PersonInfoCacheEntity) = with(cache) {
        PersonInfo(idPerson,firstname,lastname,placeOfWork,position,linkPDF,comment)
    }

    override fun mapToCache(data: PersonInfo) = with(data) {
        PersonInfoCacheEntity(id!!,firstname!!,lastname!!,placeOfWork!!,position!!,linkPDF!!,comment!!)
    }
}