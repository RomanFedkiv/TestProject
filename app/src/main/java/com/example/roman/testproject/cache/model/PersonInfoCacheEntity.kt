package com.example.roman.testproject.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.roman.testproject.cache.db.dao.PersonInfoDao


@Entity(tableName = PersonInfoDao.TABLE_NAME)
data class PersonInfoCacheEntity (
        val idPerson : String,
        val firstname : String,
        val lastname : String,
        val placeOfWork : String,
        val position: String,
        val linkPDF : String,
        val comment: String,
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0)