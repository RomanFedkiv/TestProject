package com.example.roman.testproject.data.repository

import com.example.roman.testproject.data.model.PersonInfo
import io.reactivex.Single

interface PersonInfoRemote {

    fun getPersonInfo(query : String) : Single<List<PersonInfo>>
}