package `in`.gulshan.demoflickerapp.features

import `in`.gulshan.demoflickerapp.R
import `in`.gulshan.demoflickerapp.base.BaseActivity
import `in`.gulshan.demoflickerapp.features.main.SampleDemoMainActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        switchScreen()
    }


    private fun switchScreen() {
        Handler().postDelayed({
            startActivity(Intent(this, SampleDemoMainActivity::class.java))
            finish()
        }, 3000)
    }

    override fun onHandleBack() {
        onBackPressed()
    }
}