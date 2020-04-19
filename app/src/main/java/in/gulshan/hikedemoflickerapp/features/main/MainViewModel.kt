package `in`.gulshan.hikedemoflickerapp.features.main

import `in`.gulshan.data.repositories.MainRepository
import `in`.gulshan.data.results.MainActivityResult
import `in`.gulshan.hikedemoflickerapp.BuildConfig
import `in`.gulshan.hikedemoflickerapp.base.BaseViewModel
import `in`.gulshan.hikedemoflickerapp.utils.SingleLiveEvent
import androidx.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    private val mainRepository: MainRepository

) : BaseViewModel() {

    private val result = SingleLiveEvent<MainActivityResult>()
    fun setMainResult(): LiveData<MainActivityResult> = result

    fun getFlickerImages(text: String) {
        addDisposable(
            mainRepository
                .getFlickerImage(
                    search = text,
                    apiKey = BuildConfig.API_KEY,
                    format = BuildConfig.FORMAT,
                    method = BuildConfig.METHOD,
                    nojsonCallback = BuildConfig.NOJSONCALLBACK
                )
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    result.postValue(MainActivityResult.ImageResponse(it))
                }, {
                    result.postValue(MainActivityResult.Error(it))
                })
        )
    }


}