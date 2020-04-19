package `in`.gulshan.hikedemoflickerapp.features

import `in`.gulshan.hikedemoflickerapp.R
import `in`.gulshan.hikedemoflickerapp.base.BaseActivity
import `in`.gulshan.hikedemoflickerapp.features.main.HikeDemoMainActivity
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
            startActivity(Intent(this, HikeDemoMainActivity::class.java))
            finish()
        }, 3000)
    }

    override fun onHandleBack() {
        onBackPressed()
    }
}