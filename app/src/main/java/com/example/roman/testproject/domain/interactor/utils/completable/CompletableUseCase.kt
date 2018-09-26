package com.example.roman.testproject.domain.interactor.utils.completable


import com.example.roman.testproject.domain.IOThreadFactory
import com.example.roman.testproject.domain.UIThreadFactory
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable

abstract class CompletableUseCase<in ParameterType>(
        private val io: IOThreadFactory,
        private val ui: UIThreadFactory
) {

    private val compositeDisposable = CompositeDisposable()

    protected abstract fun buildUseCase(param: ParameterType): Completable

    fun execute(param: ParameterType, result: (Result) -> Unit) = buildUseCase(param)
            .subscribeOn(io.get())
            .observeOn(ui.get())
            .subscribe(
                    { result(Complete())},
                    { result(Error(it)) }
            ).also { compositeDisposable.add(it) }

    fun dispose() = compositeDisposable.dispose()
}
