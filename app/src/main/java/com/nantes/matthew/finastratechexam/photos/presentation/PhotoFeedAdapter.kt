package com.nantes.matthew.finastratechexam.photos.presentation

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.nantes.matthew.finastratechexam.R
import com.nantes.matthew.finastratechexam.databinding.ItemPhotoBinding
import com.nantes.matthew.finastratechexam.photos.domain.model.Photo

class PhotoFeedAdapter(private val context: Context) :
    ListAdapter<Photo, PhotoFeedAdapter.PhotoFeedViewHolder>(DIFF_UTIL) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoFeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        val binder = ItemPhotoBinding.bind(view)
        return PhotoFeedViewHolder(binder)

    }

    override fun onBindViewHolder(holder: PhotoFeedViewHolder, position: Int) {
        val photo = getItem(position)
        Glide.with(context).asBitmap().load(photo.url)
            .into(holder.binder.ivPhoto)
        holder.binder.tvLikesCount.text = photo.likes.toString()

        Glide.with(context).asBitmap().load(photo.author.profileImage)
            .apply(RequestOptions.circleCropTransform())
            .addListener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap,
                    model: Any,
                    target: Target<Bitmap>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.binder.tvUsername.isVisible = true
                    holder.binder.ivAuthorPhoto.isVisible = true
                    holder.binder.tvDateCreated.isVisible = true
                    holder.binder.tvLikesCount.isVisible = true

                    return false
                }
            }).into(holder.binder.ivAuthorPhoto)
        holder.binder.tvUsername.text = String.format(
            context.resources.getString(R.string.at_username),
            photo.author.username
        )
        holder.binder.tvDateCreated.text = photo.getDateCreateString()
    }


    inner class PhotoFeedViewHolder(val binder: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binder.root)

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }

}