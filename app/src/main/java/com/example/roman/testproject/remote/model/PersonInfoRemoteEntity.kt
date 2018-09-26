package com.example.roman.testproject.remote.model

import com.google.gson.annotations.SerializedName

data class PersonInfoRemoteEntity (
        @SerializedName("id") val id : String?,
        @SerializedName("firstname") val firstname : String?,
        @SerializedName("lastname") val lastname : String?,
        @SerializedName("placeOfWork") val placeOfWork : String?,
        @SerializedName("position") val position: String?,
        @SerializedName("linkPDF") val linkPDF : String?)
