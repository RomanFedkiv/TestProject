package com.example.roman.testproject.domain

import io.reactivex.android.schedulers.AndroidSchedulers

object UIThreadFactory {

    fun get() = AndroidSchedulers.mainThread()
}
