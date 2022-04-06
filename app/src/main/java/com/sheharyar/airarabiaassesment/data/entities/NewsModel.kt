package com.sheharyar.airarabiaassesment.data.entities

data class NewsModel(
    var status: String,
    var copyright: String,
    var num_results: String,
    var results: ArrayList<NewsResultsModel>
)