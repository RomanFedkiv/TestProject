package com.example.roman.testproject.domain.interactor

import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.domain.IOThreadFactory
import com.example.roman.testproject.domain.UIThreadFactory
import com.example.roman.testproject.domain.interactor.utils.completable.CompletableUseCase
import com.example.roman.testproject.domain.interactor.utils.single.SingleUseCase
import com.example.roman.testproject.domain.repository.PersonInfoRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class DeleteFromFavouriteUseCase @Inject constructor(
        io: IOThreadFactory,
        ui: UIThreadFactory,
        private val repository: PersonInfoRepository
) : CompletableUseCase<String>(io, ui) {

    override fun buildUseCase(param : String): Completable = repository.deleteFromFavourite(param)

}