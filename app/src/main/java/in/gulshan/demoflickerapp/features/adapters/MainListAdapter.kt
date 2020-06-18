package `in`.gulshan.demoflickerapp.features.adapters

import `in`.gulshan.demoflickerapp.R
import `in`.gulshan.demoflickerapp.features.main.SampleDemoMainActivity
import `in`.gulshan.demoflickerapp.utils.ViewUtil
import `in`.gulshan.remote.domain.response.SearchResponse
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_rv_images.view.*
import java.lang.ref.WeakReference

class MainListAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var searchResponse: SearchResponse

    fun addResponseData(searchResponse: SearchResponse) {
        this.searchResponse = searchResponse
    }

    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = viewgroup.context
        return ImagesViewHolder(
            LayoutInflater.from(viewgroup.context).inflate(
                R.layout.layout_rv_images,
                viewgroup,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        if (::searchResponse.isInitialized) {
            return searchResponse.articles.size
        } else {
            return 0
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ImagesViewHolder
        val result = searchResponse.articles.get(position)
        viewHolder.ivBackground.setImageDrawable(null)
        viewHolder.tvAuthor.text = result.author
        viewHolder.tvTitle.text = result.title
        viewHolder.tvPublishedAt.text = ViewUtil.convertTimeStampToDate(result.publishedAt)
        result.urlToImage?.let {
            ViewUtil.loadImage(WeakReference(context), viewHolder.ivBackground, it)
        }
        viewHolder.rootContainer.setOnClickListener(onclick(result))

    }

    private fun onclick(result: SearchResponse.Articles): View.OnClickListener? {
        return View.OnClickListener {
            (context as SampleDemoMainActivity).openFullScreen(result)
        }
    }

    private fun loadImage(context: Context, view: ImageView, imageUrl: String) {
        Glide.with(context)
            .load(imageUrl)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).override(300, 300))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.setImageResource(R.mipmap.ic_launcher)
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

private class ImagesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivBackground = view.ivBackground
    val tvAuthor = view.tvAuthor
    val tvTitle = view.tvTitle
    val tvPublishedAt = view.tvPublishedAt
    val rootContainer = view.rootContainer
}
