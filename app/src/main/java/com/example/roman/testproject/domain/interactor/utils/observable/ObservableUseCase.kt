package com.example.roman.testproject.domain.interactor.utils.observable



import com.example.roman.testproject.domain.IOThreadFactory
import com.example.roman.testproject.domain.UIThreadFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

abstract class ObservableUseCase<ResultType, in ParameterType>(
        private val io: IOThreadFactory,
        private val ui: UIThreadFactory
) {

    private val compositeDisposable = CompositeDisposable()

    protected abstract fun buildUseCase(param: ParameterType): Observable<ResultType>

    fun execute(param: ParameterType, result: (Result<ResultType>) -> Unit) = buildUseCase(param)
            .subscribeOn(io.get())
            .observeOn(ui.get())
            .subscribe(
                    { result(Next(it)) },
                    { result(Error(it)) },
                    { result(Complete()) }
            ).also { compositeDisposable.add(it) }

    fun dispose() = compositeDisposable.dispose()
}
