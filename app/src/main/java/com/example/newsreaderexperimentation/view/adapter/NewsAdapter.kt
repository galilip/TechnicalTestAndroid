package com.example.newsreaderexperimentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsreaderexperimentation.R
import com.example.newsreaderexperimentation.model.Article
import com.squareup.picasso.Picasso


class NewsAdapter(private val listener: NewsItemListener) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    interface NewsItemListener {
        fun onClickedNews (activity: AppCompatActivity, article: Article) {}
    }

    private val items = ArrayList<Article>()
    private lateinit var article: Article

    fun setItems(items: ArrayList<Article>) {
        this.items.clear()
        this.items.addAll(items)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_cardview, parent, false)
        return NewsViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getItem(position: Int): Article {
        return this.items[position]
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        article = items[position]

        holder.textTitle.text = article.title
        // sometimes article has not description, so I put content in it instead to avoid blank
        if (article.description !== null && article.description.isEmpty() ) {
            holder.textDescription.text = article.content;
        } else {
            holder.textDescription.text = article.description
        }
        holder.textDate.text = article.publishedAt
        loadImage(holder, article)

    }

    private fun loadImage (holder: NewsViewHolder, article: Article) {
        if (article.urlToImage != null) {
            // sometimes url images received from API are not well formed
            // so when they don't I put a placeholder
            if (!article.urlToImage.startsWith("http")) {
                Picasso.get()
                    .load("placeholder")
                    .placeholder(R.drawable.placeholder)
            } else {
                Picasso.get()
                    .load(article.urlToImage)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(holder.image)
            }
        }
    }

    inner class NewsViewHolder(itemView: View, private val listener: NewsItemListener) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val itemLayout: LinearLayout = itemView.findViewById(R.id.news_fragment)
        val textTitle: TextView = itemView.findViewById(R.id.text_title)
        val textDescription: TextView = itemView.findViewById(R.id.text_description)
        val textDate : TextView = itemView.findViewById(R.id.text_date)
        val image: AppCompatImageView = itemView.findViewById(R.id.image)


        init {
            itemLayout.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val activity= v?.context as AppCompatActivity
            this@NewsAdapter.getItem(adapterPosition).let { listener.onClickedNews(activity, it) }
        }

    }

}
