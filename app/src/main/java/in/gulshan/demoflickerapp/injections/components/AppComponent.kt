package `in`.gulshan.demoflickerapp.injections.components


import `in`.gulshan.demoflickerapp.SampleDemoApplication
import `in`.gulshan.demoflickerapp.injections.bindings.ActivityBindingModule
import `in`.gulshan.demoflickerapp.injections.modules.ApplicationModule
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
interface AppComponent : AndroidInjector<SampleDemoApplication> {

    override fun inject(application: SampleDemoApplication)

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
