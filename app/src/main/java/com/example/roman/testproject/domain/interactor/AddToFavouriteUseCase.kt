package com.example.roman.testproject.domain.interactor

import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.domain.IOThreadFactory
import com.example.roman.testproject.domain.UIThreadFactory
import com.example.roman.testproject.domain.interactor.utils.completable.CompletableUseCase
import com.example.roman.testproject.domain.repository.PersonInfoRepository
import io.reactivex.Completable
import javax.inject.Inject


class AddToFavouriteUseCase @Inject constructor(
        io: IOThreadFactory,
        ui: UIThreadFactory,
        private val repository: PersonInfoRepository
) : CompletableUseCase<PersonInfo>(io, ui) {
    override fun buildUseCase(param: PersonInfo) = repository.addToDB(param)
}