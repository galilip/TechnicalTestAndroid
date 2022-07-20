package com.example.newsreaderexperimentation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.newsreaderexperimentation.R
import com.example.newsreaderexperimentation.model.Article
import com.google.gson.Gson
import com.squareup.picasso.Picasso


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // retrieve data passed by previous activity when we clicked an article
        val gson = Gson()
        val article = gson.fromJson(intent.getStringExtra("article"), Article::class.java)

        val textTitleView = this.findViewById<TextView>(R.id.textTitleDetail)
        val descriptionView = this.findViewById<TextView>(R.id.descriptionDetail)
        val imageView = this.findViewById<ImageView>(R.id.imageDetail)
        val dateView = this.findViewById<TextView>(R.id.dateDetails)
        val btnGoToWebSite = this.findViewById<TextView>(R.id.goToWebsiteButton)

        textTitleView.text = article.title
        descriptionView.text = article.description
        dateView.text = article.publishedAt
        
        // open article on website's publisher
        btnGoToWebSite.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            startActivity(browserIntent)
        }

        if (article.urlToImage != null) {
            if (!article.urlToImage.startsWith("http")) {
                Picasso.get()
                    .load("placeholder")
                    .placeholder(R.drawable.placeholder)
            } else {
                Picasso.get()
                    .load(article.urlToImage)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(imageView)
            }
        }
    }
}