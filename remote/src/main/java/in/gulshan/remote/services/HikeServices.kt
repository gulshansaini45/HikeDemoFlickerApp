package `in`.gulshan.remote.services

import `in`.gulshan.remote.domain.response.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HikeServices {

    @GET("services/rest/")
    fun getFlickerImages(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: String,
        @Query("text") text: String
    ): Single<SearchResponse>


}