package com.example.roman.testproject.remote.mapper

interface Mapper<in RemoteType, out DataType> {

    fun map(remote: RemoteType): DataType
}
