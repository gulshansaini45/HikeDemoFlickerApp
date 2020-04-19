package `in`.gulshan.data.results

import `in`.gulshan.remote.domain.response.SearchResponse

sealed class MainActivityResult {
    data class ImageResponse(val response: SearchResponse) : MainActivityResult()
    data class Error(val throwable: Throwable?) : MainActivityResult()
}