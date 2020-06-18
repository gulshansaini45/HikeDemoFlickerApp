package `in`.gulshan.demoflickerapp.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url().url().toString()
        val newRequestBuilder = originalRequest.newBuilder()

        newRequestBuilder.addHeader("Content-Type", "application/json")

        return chain.proceed(newRequestBuilder.build())
    }
}
