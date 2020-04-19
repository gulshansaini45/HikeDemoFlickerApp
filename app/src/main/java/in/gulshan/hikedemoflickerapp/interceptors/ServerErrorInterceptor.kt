package `in`.gulshan.hikedemoflickerapp.interceptors


import `in`.gulshan.hikedemoflickerapp.BuildConfig
import `in`.gulshan.remote.exceptions.ServerException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ServerErrorInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalResponse = chain.proceed(originalRequest)
        val contentTypeHeader = originalResponse.header(HEADER_CONTENT_TYPE)

        if (originalResponse.code() == 401) throw ServerException.UserUnAuthorizedResponse("Session expired")
        else {
            if (originalRequest.url().url().toString().startsWith(BuildConfig.BASE_URL) && contentTypeHeader != null && contentTypeHeader.equals(
                    APPLICATION_JSON,
                    ignoreCase = true
                )
            ) {
                if (originalResponse.code() in 400..599) {
                    throw ServerException.DefaultErrorResponse("OOP's something went wrong ,please try again!")
                }
            }
        }
        return originalResponse
    }

    companion object {
        private const val HEADER_CONTENT_TYPE = "Content-Type"
        private const val APPLICATION_JSON = "application/json"

        @Retention(AnnotationRetention.SOURCE)
        internal annotation class Error

    }
}
