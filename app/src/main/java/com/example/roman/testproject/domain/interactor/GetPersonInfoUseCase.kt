package com.example.roman.testproject.domain.interactor

import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.domain.IOThreadFactory
import com.example.roman.testproject.domain.UIThreadFactory
import com.example.roman.testproject.domain.interactor.utils.single.SingleUseCase
import com.example.roman.testproject.domain.repository.PersonInfoRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPersonInfoUseCase @Inject constructor(
        io: IOThreadFactory,
        ui: UIThreadFactory,
        private val repository: PersonInfoRepository
) : SingleUseCase<List<PersonInfo>, String>(io, ui) {

    override fun buildUseCase(param : String): Single<List<PersonInfo>> = repository.getPersonInfo(param)

}