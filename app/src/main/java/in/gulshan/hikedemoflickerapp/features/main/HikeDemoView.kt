package `in`.gulshan.hikedemoflickerapp.features.main

import `in`.gulshan.hikedemoflickerapp.base.BaseView

interface HikeDemoView : BaseView {
    fun initRecyclerView()
    fun getFlickerResult()
    fun setFlickerResult()
}