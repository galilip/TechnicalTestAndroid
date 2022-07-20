package com.example.newsreaderexperimentation

import android.content.Context
import com.example.newsreaderexperimentation.model.Article
import com.example.newsreaderexperimentation.model.News
import com.example.newsreaderexperimentation.repository.NewsRepository
import com.example.newsreaderexperimentation.retrofit.RetrofitService
import com.example.newsreaderexperimentation.view.MainActivity
import com.example.newsreaderexperimentation.viewmodel.NewsViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class NewsViewModelUnitTest {
    lateinit var newsViewModel: NewsViewModel
    lateinit var newsRepository: NewsRepository

    lateinit var articles : ArrayList<Article>

    @Mock
    lateinit var apiService: RetrofitService
    @Mock
    lateinit var mainActivity: MainActivity


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        newsRepository = NewsRepository(apiService, mainActivity)
        newsViewModel = NewsViewModel(newsRepository)
    }

    @Test
    fun getSportNews () {
            Mockito.`when`(newsRepository.getServicesApiCall().value)
                .thenReturn((News("ok", 20, articles)))
            newsViewModel.getSportNews()
            val result = newsViewModel.servicesLiveData
            assertEquals(News("ok", 20, articles), result)
    }

}