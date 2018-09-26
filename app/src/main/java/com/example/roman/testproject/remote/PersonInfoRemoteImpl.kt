package com.example.roman.testproject.remote


import android.util.Log
import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.data.repository.PersonInfoRemote
import com.example.roman.testproject.remote.mapper.PersonInfoMapper
import com.example.roman.testproject.remote.model.PersonInfoRemoteEntity
import io.reactivex.Single
import javax.inject.Inject

class PersonInfoRemoteImpl  @Inject constructor(
        private val api: PersonInfoAPI,
        private val mapper : PersonInfoMapper
) : PersonInfoRemote {

    override fun getPersonInfo(query : String): Single<List<PersonInfo>> =
            api.getPersonInfo(query)
                    .map { it.data}
                    .map { it.map(mapper::map) }  }
