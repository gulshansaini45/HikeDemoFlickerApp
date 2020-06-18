package `in`.gulshan.demoflickerapp.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModel : ViewModel() {
    protected val errorMutable: MutableLiveData<Throwable> = MutableLiveData()
    protected val compositeDisposable = CompositeDisposable()

    val isDataLoading = ObservableBoolean(false)
    val errorLive: LiveData<Throwable> get() = errorMutable

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
        super.onCleared()
    }

    protected fun setIsDataLoading(loading: Boolean) {
        isDataLoading.set(loading)
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun <T> applySchedulersWithDataLoading(): SingleTransformer<T, T> {
        return SingleTransformer {
            it.subscribeOn(Schedulers.io())
                .doOnSubscribe { isDataLoading.set(true) }
                .doFinally { isDataLoading.set(false) }
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
