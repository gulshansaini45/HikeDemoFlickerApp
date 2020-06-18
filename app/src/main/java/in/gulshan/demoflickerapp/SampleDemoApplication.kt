package `in`.gulshan.demoflickerapp

import `in`.gulshan.demoflickerapp.injections.components.AppComponent
import `in`.gulshan.demoflickerapp.injections.components.DaggerAppComponent
import android.app.Activity
import android.content.Context
import android.os.StrictMode
import androidx.lifecycle.LifecycleObserver
import androidx.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class SampleDemoApplication : MultiDexApplication(), HasActivityInjector, LifecycleObserver {
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private var appComponent: AppComponent? = null


    override fun onCreate() {
        enableStrictMode()
        super.onCreate()
        setUpLeakCanary()
        createAppComponent()
    }


    private fun setUpLeakCanary() {
        LeakCanary.install(this)
    }

    private fun enableStrictMode() {
        //Strict Mode Thread Policy
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )

        //Strict Mode VM policy
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    fun setComponent(appComponent: AppComponent?) {
        this.appComponent = appComponent
    }

    private fun createAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
        appComponent?.inject(this)
    }

    companion object {

        fun get(context: Context): SampleDemoApplication {
            return context.applicationContext as SampleDemoApplication
        }
    }
}

