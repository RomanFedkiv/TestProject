package com.example.roman.testproject.domain.interactor.utils.completable

sealed class Result

class Complete : Result()

data class Error(val e: Throwable) : Result()
