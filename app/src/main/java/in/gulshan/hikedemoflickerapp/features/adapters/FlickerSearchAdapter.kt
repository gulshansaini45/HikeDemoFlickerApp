package `in`.gulshan.hikedemoflickerapp.features.adapters

import `in`.gulshan.hikedemoflickerapp.R
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

class FlickerSearchAdapter :
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
            return searchResponse.photos.photo.size
        } else {
            return 0
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ImagesViewHolder
        viewHolder.ivFlickerImage.setImageDrawable(null)
        loadImage(context = context, view = viewHolder.ivFlickerImage, imageUrl = getImageUrl(position))

    }

    private fun getImageUrl(position: Int): String {
        val photo = searchResponse.photos.photo.get(position)
        val image = context.getString(R.string.imageUrlPath, photo.farm, photo.server, photo.id, photo.secret)
        return image
    }


    private fun loadImage(context: Context, view: ImageView, imageUrl: String) {
        Glide.with(context)
            .load(imageUrl)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA))
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
    val ivFlickerImage = view.ivFlickerImages
}
