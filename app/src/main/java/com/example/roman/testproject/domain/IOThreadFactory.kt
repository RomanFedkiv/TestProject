package com.example.roman.testproject.domain

import io.reactivex.schedulers.Schedulers

object IOThreadFactory {

    fun get() = Schedulers.io()
}
