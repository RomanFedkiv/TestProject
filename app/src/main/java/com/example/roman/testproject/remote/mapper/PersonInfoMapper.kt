package com.example.roman.testproject.remote.mapper

import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.remote.model.PersonInfoRemoteEntity
import javax.inject.Inject

class PersonInfoMapper @Inject constructor(): Mapper<PersonInfoRemoteEntity, PersonInfo>{

    override fun map(remote: PersonInfoRemoteEntity) = with(remote) {
        PersonInfo(id!!,firstname!!.toUpperCase(),lastname!!.toUpperCase(),placeOfWork!!.toUpperCase(),
                position,linkPDF, "") }
}