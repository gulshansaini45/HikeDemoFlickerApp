object Versions {
    const val kotlinVersion = "1.3.21"
    const val supportLibraryVersion = "28.0.0"
    const val appCompat = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val lifecycleVersion = "2.2.0"

}

object Android {
    const val compileSdkVersion = 28
    const val buildToolsVersion = "28.0.3"
    const val minSdkVersion = 21
    const val targetSdkVersion = 28
}

object Support {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val appCompactResource = "androidx.appcompat:appcompat-resources:${Versions.appCompat}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    const val multidex = "androidx.multidex:multidex:2.0.1"
    const val viewModelLifecycle ="androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycleVersion}"
    const val viewModelLifecycleExt ="androidx.lifecycle:lifecycle-extensions:2.1.0"

}
object Views {
    const val coreKotlin = "androidx.core:core-ktx:1.2.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}
object Testing {
    const val JUnit = "junit:junit:4.12"
    const val TestRunner = "androidx.test:runner:1.2.0"
    const val Expresso = "androidx.test.espresso:espresso-core:3.2.0"
}

object Dagger {
    const val dagger = "com.google.dagger:dagger:2.21"
    const val daggerAndroid = "com.google.dagger:dagger-android-support:2.21"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:2.21"
    const val daggerAndroidCompiler = "com.google.dagger:dagger-android-processor:2.21"
    const val javaxAnnotation = "org.glassfish:javax.annotation:10.0-b28"
    const val javaxInject = "javax.inject:javax.inject:1"
}

object Network {
    //Network (Retrofit, OkHttp, Logging interceptor)
    const val okhttp = "com.squareup.okhttp3:okhttp:3.12.1"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:3.12.1"
    const val retrofit = "com.squareup.retrofit2:retrofit:2.5.0"
    const val gson = "com.squareup.retrofit2:converter-gson:2.5.0"
    const val moshi = "com.squareup.retrofit2:converter-moshi:2.5.0"
    const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:2.5.0"
    const val persistentCookie = "com.github.franmontiel:PersistentCookieJar:v1.0.1"
}

object Debug {
    // Stetho
    const val stetho = "com.facebook.stetho:stetho:1.5.0"
    const val stethoOkhttp = "com.facebook.stetho:stetho-okhttp3:1.5.0"
    const val stethoNoOp = "net.igenius:stetho-no-op:1.1"

    //Chuck Interceptor
    const val chuck = "com.readystatesoftware.chuck:library:1.1.0"
    const val chuckNoOp = "com.readystatesoftware.chuck:library-no-op:1.1.0"

    //Leak Canary
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:1.6.3"
    const val leakcanaryNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:1.6.3"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:4.6.1"
    const val glidePallet = "com.github.florent37:glidepalette:2.1.1"
    const val gileOkhttp = "com.github.bumptech.glide:okhttp3-integration:4.6.1"
    const val glideCompiler = "com.github.bumptech.glide:compiler:4.6.1"
}


object Reactive {
    const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.6"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.0"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.2.0"
    const val rxBinding = "com.jakewharton.rxbinding2:rxbinding-kotlin:2.1.1"
    const val rxTraceur = "com.tspoon.traceur:traceur:1.0.1"
    const val rxProguard = "com.artemzin.rxjava:proguard-rules:1.3.3.0"
    const val rxDebug = "com.tspoon.traceur:traceur:1.0.1"
}


object Extras {
    //Logging
    const val timber = "com.jakewharton.timber:timber:4.7.1"
}