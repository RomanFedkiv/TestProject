package com.example.roman.testproject.domain.interactor.utils.single



import com.example.roman.testproject.domain.IOThreadFactory
import com.example.roman.testproject.domain.UIThreadFactory
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable


abstract class SingleUseCase<ResultType, in ParameterType>(
        private val io: IOThreadFactory,
        private val ui: UIThreadFactory
) {

    private val compositeDisposable = CompositeDisposable()

    protected abstract fun buildUseCase(param: ParameterType): Single<ResultType>

    fun execute(param: ParameterType, result: (Result<ResultType>) -> Unit) = buildUseCase(param)
            .subscribeOn(io.get())
            .observeOn(ui.get())
            .subscribe(
                    { result(Success(it)) },
                    { result(Error(it)) }
            ).also { compositeDisposable.add(it) }

    fun dispose() = compositeDisposable.dispose()
}
