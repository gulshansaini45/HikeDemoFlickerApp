package `in`.gulshan.demoflickerapp.utils

import `in`.gulshan.demoflickerapp.R
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import java.lang.ref.WeakReference


class ViewUtil private constructor() {
    init {
        throw AssertionError("Cant instantiate this class")
    }

    companion object {
        @JvmStatic
        fun showSnackBar(view: View, message: String) {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
        }

        @JvmStatic
        fun hideKeyboard(activity: Activity) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        }


        @JvmStatic
        fun hideKeyboard(view: View, context: Context?) {
            context?.let {
                val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        @JvmStatic
        fun convertTimeStampToDate(dateTime: String): String {
            try {
                return dateTime.substring(0, 10)
            } catch (e: Exception) {
                Timber.e("Getting error in converting timestamp")
            }
            return ""
        }

        fun loadImage(context: WeakReference<Context>, view: ImageView, imageUrl: String) {
            view.setImageResource(R.drawable.ic_launcher_background)
            context.get().let {
                Glide.with(it!!)
                    .load(imageUrl)
                    .apply(RequestOptions.centerCropTransform().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            view.setImageResource(R.drawable.ic_launcher_background)
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable>?,
                            dataSource: com.bumptech.glide.load.DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            view.setImageDrawable(resource)
                            return true
                        }
                    })
                    .into(view)
            }

        }

    }
}
