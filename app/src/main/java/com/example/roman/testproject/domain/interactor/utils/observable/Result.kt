package com.example.roman.testproject.domain.interactor.utils.observable


sealed class Result<T>

data class Next<T>(val result: T) : Result<T>()

class Complete<T>() : Result<T>()

data class Error<T>(val e: Throwable) : Result<T>()
