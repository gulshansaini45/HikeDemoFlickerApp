package `in`.gulshan.demoflickerapp.injections.modules

import `in`.gulshan.demoflickerapp.SampleViewModelFactory
import `in`.gulshan.demoflickerapp.features.main.MainViewModel
import `in`.gulshan.demoflickerapp.injections.scopes.ViewModelKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: SampleViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindLoginViewModule(loginViewModel: MainViewModel): ViewModel


}
