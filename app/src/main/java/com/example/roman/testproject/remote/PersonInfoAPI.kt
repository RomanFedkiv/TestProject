package com.example.roman.testproject.remote

import com.example.roman.testproject.remote.model.PersonInfoRemoteEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonInfoAPI {

    @GET("declaration/")
    fun getPersonInfo(@Query("q") query: String)
            : Single<Responce<List<PersonInfoRemoteEntity>>>
}