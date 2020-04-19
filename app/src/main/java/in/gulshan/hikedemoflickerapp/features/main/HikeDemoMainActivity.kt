package `in`.gulshan.hikedemoflickerapp.features.main

import `in`.gulshan.data.results.MainActivityResult
import `in`.gulshan.hikedemoflickerapp.R
import `in`.gulshan.hikedemoflickerapp.base.BaseActivity
import `in`.gulshan.hikedemoflickerapp.features.adapters.FlickerSearchAdapter
import `in`.gulshan.hikedemoflickerapp.utils.ViewUtil
import `in`.gulshan.remote.domain.response.SearchResponse
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Provider

class HikeDemoMainActivity : BaseActivity(), HikeDemoView, View.OnClickListener {


    @Inject
    lateinit var linearLayoutManager: Provider<RecyclerView.LayoutManager>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }
    private val flickerAdapter by lazy(LazyThreadSafetyMode.NONE) {
        FlickerSearchAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSearch?.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSearch -> {
                getFlickerResult()
            }
        }
    }

    override fun getFlickerResult() {
        ViewUtil.hideKeyboard(this)
        showProgressbar()
        viewModel.getFlickerImages(etSearch?.text.toString().trim())
    }

    override fun setFlickerResult() {
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

    override fun onHandleBack() {
        onBackPressed()
    }

}
