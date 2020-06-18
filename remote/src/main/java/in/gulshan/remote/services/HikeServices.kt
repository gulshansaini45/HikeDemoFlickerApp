package `in`.gulshan.remote.services

import `in`.gulshan.remote.domain.response.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HikeServices {

    @GET("everything/")
    fun getFlickerImages(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String
    ): Single<SearchResponse>


}