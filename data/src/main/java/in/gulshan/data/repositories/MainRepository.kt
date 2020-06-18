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
        q: String,
        from: String,
        sortBy: String,
        apiKey: String
    ): Single<SearchResponse> {
        return hikeServices.getFlickerImages(
            apiKey = apiKey,
            q = q,
            from = from,
            sortBy = sortBy
        )
    }
}