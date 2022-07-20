package com.example.newsreaderexperimentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsreaderexperimentation.model.News
import com.example.newsreaderexperimentation.repository.NewsRepository
import com.example.newsreaderexperimentation.retrofit.RetrofitService

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    var servicesLiveData: MutableLiveData<News>? = null

    fun getSportNews() : LiveData<News>? {
        servicesLiveData = newsRepository.getServicesApiCall()

        return servicesLiveData
    }
}