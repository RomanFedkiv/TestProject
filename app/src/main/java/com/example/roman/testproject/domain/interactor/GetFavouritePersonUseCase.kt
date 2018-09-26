package com.example.roman.testproject.domain.interactor

import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.domain.IOThreadFactory
import com.example.roman.testproject.domain.UIThreadFactory
import com.example.roman.testproject.domain.interactor.utils.single.SingleUseCase
import com.example.roman.testproject.domain.repository.PersonInfoRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFavouritePersonUseCase@Inject constructor(
        io: IOThreadFactory,
        ui: UIThreadFactory,
        private val repository: PersonInfoRepository
) : SingleUseCase<List<PersonInfo>, Unit>(io, ui) {

    override fun buildUseCase(param : Unit): Single<List<PersonInfo>> = repository.getFavouritePerson()

}