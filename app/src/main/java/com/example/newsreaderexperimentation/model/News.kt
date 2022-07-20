package com.example.newsreaderexperimentation.model

import com.google.gson.annotations.SerializedName

data class News (
    @SerializedName("status")
    var status: String,

    @SerializedName("totalResults")
    var totalResults: Int,

    @SerializedName("articles")
    var articles: ArrayList<Article>,
) {
}