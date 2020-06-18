package `in`.gulshan.data.models.views

import `in`.gulshan.remote.domain.response.SearchResponse
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailResponse(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) : Parcelable {

    companion object {
        fun mapResponse(it: SearchResponse.Articles): DetailResponse {
            return DetailResponse(
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content
            )
        }
    }
}