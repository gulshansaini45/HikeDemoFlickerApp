package `in`.gulshan.demoflickerapp.injections.bindings

import `in`.gulshan.demoflickerapp.base.BaseActivity
import `in`.gulshan.demoflickerapp.features.SplashActivity
import `in`.gulshan.demoflickerapp.features.main.FullDetailActivity
import `in`.gulshan.demoflickerapp.features.main.SampleDemoMainActivity
import `in`.gulshan.demoflickerapp.injections.scopes.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * An abstract class that has abstract methods returning the root where dependency
 * must be injected in Activities
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun baseActivity(): BaseActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun mainActivity(): SampleDemoMainActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun fulldetailActivity(): FullDetailActivity
}
