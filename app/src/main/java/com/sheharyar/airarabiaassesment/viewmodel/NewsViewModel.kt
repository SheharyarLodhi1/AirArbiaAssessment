package com.sheharyar.airarabiaassesment.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sheharyar.airarabiaassesment.data.repository.NewsRepository

class NewsViewModel @ViewModelInject constructor(
    private
    val repository: NewsRepository
) : ViewModel() {
    val newYorkTimeNews = repository.getAllNews()
}