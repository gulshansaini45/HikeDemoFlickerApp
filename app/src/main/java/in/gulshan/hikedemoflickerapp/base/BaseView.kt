package `in`.gulshan.hikedemoflickerapp.base

interface BaseView {
    fun showProgressbar()
    fun hideProgressBar()
    fun showError(throwable: Throwable?)
}