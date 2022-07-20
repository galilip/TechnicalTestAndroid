package com.example.newsreaderexperimentation.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.newsreaderexperimentation.BuildConfig
import com.example.newsreaderexperimentation.model.Article
import com.example.newsreaderexperimentation.model.News
import com.example.newsreaderexperimentation.retrofit.RetrofitService
import com.example.newsreaderexperimentation.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class NewsRepository(private val retrofitService: RetrofitService, private val context: MainActivity)
{

    val newsResponse = MutableLiveData<News>()

    fun getServicesApiCall(): MutableLiveData<News> {

        val call = retrofitService.getServices(BuildConfig.NEWSDOC_API_KEY)
        try {
            call.enqueue(object: Callback<News> {
                // display error message if fail
                override fun onFailure(call: Call<News>, t: Throwable) {
                    Toast.makeText(context, t.message.toString(), Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<News>,
                    response: Response<News>
                ) {

                    val data = response.body()

                    if (data != null) {
                        // Formatting date because api returns US Format
                        for (art in data.articles) {
                            art.publishedAt = Article.formatDate(art.publishedAt)
                        }
                        newsResponse.value = News(data.status, data.totalResults, data.articles)
                    }
                }
            })

        } catch (e : Exception) {
            Log.i("ERROR", e.printStackTrace().toString())
            Toast.makeText(context, e.printStackTrace().toString(), Toast.LENGTH_LONG).show()
        }
        return newsResponse
    }
}