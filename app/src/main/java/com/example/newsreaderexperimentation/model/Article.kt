package com.example.newsreaderexperimentation.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*


data class Article (
    @SerializedName("source")
    var source : Source,

    @SerializedName("author")
    var author : String,

    @SerializedName("title")
    var title : String,

    @SerializedName("description")
    var description : String,

    @SerializedName("url")
    var url : String,

    @SerializedName("urlToImage")
    var urlToImage: String,

    @SerializedName("publishedAt")
    var publishedAt : String,

    @SerializedName("content")
    var content: String,
) {
    companion object {
        fun formatDate (date : String) : String {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            val output = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.FRENCH)
            val d: Date = sdf.parse(date)
            return output.format(d)
        }
    }
}
