package `in`.gulshan.hikedemoflickerapp.injections.bindings

import `in`.gulshan.hikedemoflickerapp.base.BaseActivity
import `in`.gulshan.hikedemoflickerapp.features.SplashActivity
import `in`.gulshan.hikedemoflickerapp.features.main.HikeDemoMainActivity
import `in`.gulshan.hikedemoflickerapp.injections.scopes.ActivityScoped
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
    abstract fun mainActivity(): HikeDemoMainActivity

}
