package `in`.gulshan.demoflickerapp.base

import `in`.gulshan.data.local.sharedpref.SampleDemoSharedPref
import `in`.gulshan.demoflickerapp.R
import `in`.gulshan.demoflickerapp.interceptors.LiveNetworkInterceptor
import `in`.gulshan.demoflickerapp.utils.ViewUtil
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var sampleSharedPreference: SampleDemoSharedPref
    @Inject
    lateinit var mDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    protected abstract fun onHandleBack()

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                //  onBackPressed()
                onHandleBack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setUpToolbar(toolbar: Toolbar?) {
        setSupportActionBar(toolbar)
//        supportActionBar?.let {
//            it.setDisplayHomeAsUpEnabled(true)
//            it.setDisplayShowTitleEnabled(false)
//        }
    }

    fun disableToolBarTitle() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun handleNetworkError(e: Throwable?, view: View) {
        Timber.e(e)

        when (e) {
            is LiveNetworkInterceptor.NoNetworkException -> ViewUtil.showSnackBar(
                view,
                getString(R.string.error_no_internet)
            )
            else -> ViewUtil.showSnackBar(
                view,
                getString(R.string.default_response_message)
            )
        }
    }
}