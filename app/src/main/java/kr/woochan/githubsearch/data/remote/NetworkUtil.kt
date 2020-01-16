package kr.woochan.githubsearch.data.remote

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun<T> makeNetworkRequestTail(networkFlowable: Flowable<T>, onResponse: (T) -> Unit, onError: (Throwable) -> Unit): Disposable{
    return networkFlowable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            onResponse(it)
        }, {
            onError(it)
        })
}