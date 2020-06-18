package `in`.gulshan.demoflickerapp.features.main

import `in`.gulshan.data.models.views.DetailResponse
import `in`.gulshan.data.results.MainActivityResult
import `in`.gulshan.demoflickerapp.R
import `in`.gulshan.demoflickerapp.base.BaseActivity
import `in`.gulshan.demoflickerapp.features.adapters.MainListAdapter
import `in`.gulshan.remote.domain.response.SearchResponse
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_appbar.*
import javax.inject.Inject
import javax.inject.Provider

class SampleDemoMainActivity : BaseActivity(), SampleDemoView {


    @Inject
    lateinit var linearLayoutManager: Provider<RecyclerView.LayoutManager>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }
    private val flickerAdapter by lazy(LazyThreadSafetyMode.NONE) {
        MainListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolbar(toolbar)
        initRecyclerView()
        setFlickerResult()
    }

    override fun initRecyclerView() {
        rvImages.apply {
            setHasFixedSize(true)
            adapter = flickerAdapter
            layoutManager = linearLayoutManager.get()
        }
    }

    override fun showProgressbar() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar?.visibility = View.GONE
    }

    override fun showError(throwable: Throwable?) {
        handleNetworkError(throwable, rootContainer)
    }


    override fun getFlickerResult() {
        showProgressbar()
        viewModel.getImagesDataImages()
    }

    override fun setFlickerResult() {
        getFlickerResult()
        viewModel.setMainResult().observe(this, Observer { response ->
            hideProgressBar()
            response?.let {
                when (it) {
                    is MainActivityResult.ImageResponse -> showResponse(it.response)
                    is MainActivityResult.Error -> showError(it.throwable)
                }
            }
        })
    }

    private fun showResponse(response: SearchResponse) {
        flickerAdapter.addResponseData(response)
    }

    override fun openFullScreen(result: SearchResponse.Articles) {
        val intent = Intent(this, FullDetailActivity::class.java)
        intent.putExtra(FullDetailActivity.EXTRA_DETAILS, DetailResponse.mapResponse(result))
        startActivity(intent)

    }

    override fun onHandleBack() {
        onBackPressed()
    }

}
