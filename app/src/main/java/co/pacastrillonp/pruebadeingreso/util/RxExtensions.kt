package co.pacastrillonp.pruebadeingreso.util

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

fun <T> Single<T>.observeOnComputationThread(): Single<T> = observeOn(Schedulers.computation())

fun <T> Flowable<T>.observeOnComputationThread(): Flowable<T> = observeOn(Schedulers.computation())

fun <T> Flowable<T>.subscribeOnComputationThread(): Flowable<T> =
    subscribeOn(Schedulers.computation())
        .observeOnComputationThread()

fun <T> Single<T>.subscribeOnComputationThread(): Single<T> = subscribeOn(Schedulers.computation())
    .observeOnComputationThread()

fun <T> Observable<T>.subscribeOnComputationThread(): Observable<T> =
    subscribeOn(Schedulers.computation())
        .observeOnComputationThread()

fun <T> Observable<T>.observeOnComputationThread(): Observable<T> =
    observeOn(Schedulers.computation())

fun <T> Observable<T>.subscribeOnMainThread(
    disposable: CompositeDisposable,
    consumer: ((Any: T) -> Unit)
): Disposable {
    return subscribeOnMainThread(disposable, consumer, null, null)
}

fun <T> Observable<T>.subscribeOnMainThread(
    disposable: CompositeDisposable,
    consumer: ((Any: T) -> Unit)?,
    errorHandler: ((Throwable) -> Unit)?,
    completionHandler: (() -> Unit)?
): Disposable {
    return observeOnMainThread()
        .subscribe(
            consumer ?: {},
            errorHandler ?: {
                println("❌ Uncaught error on main thread: $it")
                println("❌ Trace ->")
                it.printStackTrace()
                println("<- ❌")
            },
            completionHandler ?: {}
        ).addTo(disposable)
}

fun <T> Observable<T>.observeOnMainThread(): Observable<T> =
    observeOn(AndroidSchedulers.mainThread())
