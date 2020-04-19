package `in`.gulshan.hikedemoflickerapp.injections.modules

import `in`.gulshan.hikedemoflickerapp.HikeViewModelFactory
import `in`.gulshan.hikedemoflickerapp.features.main.MainViewModel
import `in`.gulshan.hikedemoflickerapp.injections.scopes.ViewModelKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: HikeViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindLoginViewModule(loginViewModel: MainViewModel): ViewModel


}
