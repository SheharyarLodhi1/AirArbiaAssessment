package com.sheharyar.airarabiaassesment.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.sheharyar.airarabiaassesment.R
import com.sheharyar.airarabiaassesment.data.entities.MediaMetaList
import com.sheharyar.airarabiaassesment.data.entities.NewsResultsModel
import com.sheharyar.airarabiaassesment.databinding.ItemNewsBinding
import com.sheharyar.airarabiaassesment.utils.AppConstant

class NewsAdapter(private val listener: NewsItemListener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    interface NewsItemListener {
        fun onClickedNews(hitsList: NewsResultsModel, position: Int)
    }
    private var items = ArrayList<NewsResultsModel>()

    fun setItems(items: ArrayList<NewsResultsModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun filterList(filteredList: ArrayList<NewsResultsModel>) {
        items = filteredList
        notifyDataSetChanged()
        // send call back to fragment to tell the user that there is no matching roles here..
        /*if (filteredList.size == 0) {
            listener.onNotifyNoMatchingCities()
            return
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding: ItemNewsBinding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding, listener)
    }
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    class NewsViewHolder(
        private val itemNewsBinding: ItemNewsBinding,
        private val listener: NewsItemListener,

    ) : RecyclerView.ViewHolder(itemNewsBinding.root),
        View.OnClickListener {
        private lateinit var newsResultsModel: NewsResultsModel

        init {
            itemNewsBinding.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: NewsResultsModel) {
            this.newsResultsModel = item

            if (!item.title.equals("")) {

                itemNewsBinding.tvHeading.text = item.title
            }
            if (!item.byline.equals("")) {

                itemNewsBinding.tvAutherName.text = item.byline
            }
            if (!item.published_date.equals("")) {

                itemNewsBinding.tvDate.text = item.published_date
            }
            try {
                if (!item.media.isNullOrEmpty()) {
                    AppConstant.MediaMetaList.mediaMetaArrayList.add(item.media.get(0).media_metadata.get(0))
                    Glide.with(itemNewsBinding.root)
                        .load(AppConstant.MediaMetaList.mediaMetaArrayList.get(position).url)
                        .transform(CircleCrop())
                        .into(itemNewsBinding.ivCircularImage)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onClick(v: View?) {
            listener.onClickedNews(newsResultsModel, position)
        }
    }
}