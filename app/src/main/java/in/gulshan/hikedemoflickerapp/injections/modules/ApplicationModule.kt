package `in`.gulshan.hikedemoflickerapp.injections.modules


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, ViewModelModule::class])
class ApplicationModule {
    @Provides
    fun providesContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideSharedPreference(application: Application): SharedPreferences {
        return application.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }


    @Provides
    fun provideLinearLayoutManager(application: Application): RecyclerView.LayoutManager {
        return LinearLayoutManager(application)
    }

    @Provides
    fun provideGridLayoutManager(application: Application): GridLayoutManager {
        return GridLayoutManager(application.applicationContext, SPAN_COUNT)
    }

    companion object {
        const val PREF_FILE_NAME = "hikedemo_pref_file"
        const val SPAN_COUNT = 2
    }
}
