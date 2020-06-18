package `in`.gulshan.demoflickerapp.injections.modules


import `in`.gulshan.demoflickerapp.BuildConfig
import `in`.gulshan.demoflickerapp.interceptors.HeaderInterceptor
import `in`.gulshan.demoflickerapp.interceptors.LiveNetworkInterceptor
import `in`.gulshan.demoflickerapp.interceptors.ServerErrorInterceptor
import `in`.gulshan.remote.services.HikeServices
import android.app.Application
import android.os.Build
import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.security.KeyStore
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

@Module
class NetworkModule {
    private val CACHE_SIZE = 10 * 1024 * 1024

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        @Named("base_url") url: String
    ): HikeServices {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(HikeServices::class.java)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        stethoInterceptor: StethoInterceptor,
        cache: Cache,
        @Named("header") headerInterceptor: Interceptor,
        @Named("server") serverErrorInterceptor: Interceptor,
        @Named("network") liveNetworkInterceptor: Interceptor,
        chuckInterceptor: ChuckInterceptor
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)

        httpClientBuilder.cache(cache)
        httpClientBuilder.addInterceptor(headerInterceptor)
        httpClientBuilder.addInterceptor(liveNetworkInterceptor)
        httpClientBuilder.addNetworkInterceptor(serverErrorInterceptor)
        httpClientBuilder.enableTls12()

        //Only for Debug Builds
        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(httpLoggingInterceptor)
            httpClientBuilder.addInterceptor(chuckInterceptor)
            httpClientBuilder.addNetworkInterceptor(stethoInterceptor)
        }
        return httpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideCache(application: Application): Cache {
        return Cache(File(application.applicationContext.cacheDir, "okhttpCache"), CACHE_SIZE.toLong())
    }


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    @Provides
    @Singleton
    @Named("header")
    fun provideHeaderInterceptor(): Interceptor {
        return HeaderInterceptor()
    }

    @Provides
    @Singleton
    @Named("network")
    fun provideNetworkInterceptor(application: Application): Interceptor {
        return LiveNetworkInterceptor(application.applicationContext)
    }

    @Provides
    @Singleton
    @Named("server")
    fun provideServerErrorInterceptor(): Interceptor {
        return ServerErrorInterceptor()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message -> Log.d("OKHTTP", message) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideStethoInterceptor(): StethoInterceptor {
        return StethoInterceptor()
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(application: Application): ChuckInterceptor {
        return ChuckInterceptor(application)
    }

    companion object {
        /**
         * @return [X509TrustManager] from [TrustManagerFactory]
         *
         * @throws [NoSuchElementException] if not found. According to the Android docs for [TrustManagerFactory], this
         * should never happen because PKIX is the only supported algorithm
         */
        private val trustManager by lazy {
            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(null as KeyStore?)
            trustManagerFactory.trustManagers
                .first { it is X509TrustManager } as X509TrustManager
        }

        /**
         * If on [Build.VERSION_CODES.LOLLIPOP] or lower, sets [OkHttpClient.Builder.sslSocketFactory] to an instance of
         * [Tls12SocketFactory] that wraps the default [SSLContext.getSocketFactory] for [TlsVersion.TLS_1_2].
         *
         * Does nothing when called on [Build.VERSION_CODES.LOLLIPOP_MR1] or higher.
         *
         * For some reason, Android supports TLS v1.2 from [Build.VERSION_CODES.JELLY_BEAN], but the spec only has it
         * enabled by default from API [Build.VERSION_CODES.KITKAT]. Furthermore, some devices on
         * [Build.VERSION_CODES.LOLLIPOP] don't have it enabled, despite the spec saying they should.
         *
         * @return the (potentially modified) [OkHttpClient.Builder]
         */
        @JvmStatic
        fun OkHttpClient.Builder.enableTls12() = apply {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
                try {
                    val sslContext = SSLContext.getInstance(TlsVersion.TLS_1_2.javaName())
                    sslContext.init(null, arrayOf(trustManager), null)

                    sslSocketFactory(Tls12SocketFactory(sslContext.socketFactory), trustManager)
                } catch (e: Exception) {
                    Timber.e(e, "Error while setting TLS 1.2 compatibility")
                }
            }
        }
    }
}
