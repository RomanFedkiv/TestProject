package com.example.roman.testproject.cache.mapper

interface Mapper<CacheType, DataType> {

    fun mapFromCache(cache: CacheType): DataType

    fun mapToCache(data: DataType): CacheType
}
