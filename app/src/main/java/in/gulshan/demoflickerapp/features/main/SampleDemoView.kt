package `in`.gulshan.demoflickerapp.features.main

import `in`.gulshan.demoflickerapp.base.BaseView
import `in`.gulshan.remote.domain.response.SearchResponse

interface SampleDemoView : BaseView {
    fun initRecyclerView()
    fun getFlickerResult()
    fun setFlickerResult()
    fun openFullScreen(result: SearchResponse.Articles)
}