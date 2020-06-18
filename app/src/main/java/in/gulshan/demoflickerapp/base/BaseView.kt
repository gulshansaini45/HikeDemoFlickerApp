package `in`.gulshan.demoflickerapp.base

interface BaseView {
    fun showProgressbar()
    fun hideProgressBar()
    fun showError(throwable: Throwable?)
}