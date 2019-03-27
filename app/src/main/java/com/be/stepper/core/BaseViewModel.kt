package com.be.stepper.core

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

open class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    protected fun <T : Any> addDisposable(observable: Observable<T>) {
        disposables.add(
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { Timber.e(it, it.toString()) })
        )
    }

    override fun onCleared() {
        disposables.clear()
    }

}