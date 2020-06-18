package `in`.gulshan.demoflickerapp.features.main

import `in`.gulshan.data.models.views.DetailResponse
import `in`.gulshan.demoflickerapp.R
import `in`.gulshan.demoflickerapp.base.BaseActivity
import `in`.gulshan.demoflickerapp.utils.ViewUtil
import android.os.Bundle
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_full_details.*
import java.lang.ref.WeakReference

class FullDetailActivity : BaseActivity() {
    companion object {
        const val EXTRA_DETAILS = "extra_details"
    }

    private val detailResponse by lazy { intent.getParcelableExtra(EXTRA_DETAILS) as DetailResponse }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_details)
        detailResponse?.let { detail ->
            tvAuthor?.text = detail.author
            tvTitle?.text = detail.title
            tvDescription?.text = detail.description
            tvPublishedAt?.text = detail.publishedAt.substring(0, 10)
            detail.urlToImage?.let { ViewUtil.loadImage(WeakReference(this), ivBg, it) }
        }

    }


    override fun onHandleBack() {

    }
}