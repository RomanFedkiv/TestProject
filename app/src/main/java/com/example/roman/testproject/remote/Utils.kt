package com.example.roman.testproject.remote

import com.google.gson.annotations.SerializedName

data class Responce<T>(
        @SerializedName("items") val data: T)