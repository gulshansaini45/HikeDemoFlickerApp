package `in`.gulshan.hikedemoflickerapp.injections.components


import `in`.gulshan.hikedemoflickerapp.HikeDemoApplication
import `in`.gulshan.hikedemoflickerapp.injections.bindings.ActivityBindingModule
import `in`.gulshan.hikedemoflickerapp.injections.modules.ApplicationModule
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<HikeDemoApplication> {

    override fun inject(application: HikeDemoApplication)

    /**
     * Component Builder for App component
     */
    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun baseUrl(@Named("base_url") baseUrl: String): Builder

    }
}
