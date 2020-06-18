package `in`.gulshan.demoflickerapp.features.main

import `in`.gulshan.data.repositories.MainRepository
import `in`.gulshan.data.results.MainActivityResult
import `in`.gulshan.demoflickerapp.BuildConfig
import `in`.gulshan.demoflickerapp.base.BaseViewModel
import `in`.gulshan.demoflickerapp.utils.SingleLiveEvent
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

    fun getImagesDataImages() {
        addDisposable(
            mainRepository
                .getFlickerImage(
                    apiKey = BuildConfig.API_KEY,
                    q = BuildConfig.Q,
                    from = BuildConfig.from,
                    sortBy = BuildConfig.sortBy

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