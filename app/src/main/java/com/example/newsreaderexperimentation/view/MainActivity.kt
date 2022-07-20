package com.example.newsreaderexperimentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newsreaderexperimentation.R
import com.example.newsreaderexperimentation.model.Article
import com.example.newsreaderexperimentation.model.News
import com.example.newsreaderexperimentation.repository.NewsRepository
import com.example.newsreaderexperimentation.retrofit.RetrofitService
import com.example.newsreaderexperimentation.view.adapter.NewsAdapter
import com.example.newsreaderexperimentation.viewmodel.MyViewModelFactory
import com.example.newsreaderexperimentation.viewmodel.NewsViewModel
import com.google.gson.Gson


class MainActivity : AppCompatActivity(), NewsAdapter.NewsItemListener {
    private lateinit var context: Context

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity

        /* ViewModel creation through Factory */
        val retrofitService = RetrofitService.getInstance()
        val newsRepository = NewsRepository(retrofitService, context as MainActivity)
        newsViewModel =
            ViewModelProvider(this, MyViewModelFactory(newsRepository)).get(NewsViewModel::class.java)


        // setup swipeRefreshLayout
        swipeRefreshLayout = this.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.isRefreshing = true

        // setup recyclerView & fetching data
        setupRecyclerView()
        subscribeObservers()

        // onRefresh -> refresh news
        swipeRefreshLayout.setOnRefreshListener { subscribeObservers() }
    }

    /**
     * Fill RW with our fetched news
     */
    private fun populateRecyclerView(news: News) {
        if (news.articles.isNotEmpty()) {
            adapter.setItems(news.articles)
        }
    }

    /**
     * Preparing our recyclerview that will display news fetched from API
     */
    private fun setupRecyclerView() {
        adapter = NewsAdapter(this)
        val newsRecyclerView = this.findViewById<RecyclerView>(R.id.news_recyclerview)

        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.adapter = adapter
    }

    private fun subscribeObservers() {
        newsViewModel.getSportNews()!!.observe(this) { news ->
                swipeRefreshLayout.isRefreshing = false
                populateRecyclerView(news)
        }
    }

    /**
     * When a news is clicked, we send it to the DetailsActivity, to display it
     */
    override fun onClickedNews(activity: AppCompatActivity, article: Article) {
        val intent = Intent(this, DetailsActivity::class.java)
        val gson = Gson()
        intent.putExtra("article", gson.toJson(article))
        startActivity(intent)
    }
}