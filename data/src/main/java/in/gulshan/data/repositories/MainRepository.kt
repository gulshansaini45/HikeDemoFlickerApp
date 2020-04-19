package `in`.gulshan.data.repositories

import `in`.gulshan.remote.domain.response.SearchResponse
import `in`.gulshan.remote.services.HikeServices
import io.reactivex.Single
import javax.inject.Inject

class MainRepository
@Inject constructor(
    private val hikeServices: HikeServices
) {

    fun getFlickerImage(
        search: String,
        apiKey: String,
        method: String,
        format: String,
        nojsonCallback: String
    ): Single<SearchResponse> {
        return hikeServices.getFlickerImages(
            method = method,
            apiKey = apiKey,
            format = format,
            nojsoncallback = nojsonCallback,
            text = search
        )
    }
}