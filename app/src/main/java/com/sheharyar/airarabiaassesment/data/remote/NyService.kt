package com.sheharyar.airarabiaassesment.data.remote

import com.sheharyar.airarabiaassesment.data.entities.NewsModel
import com.sheharyar.airarabiaassesment.utils.AppConstant
import retrofit2.Response
import retrofit2.http.GET

interface NyService {
    @GET(AppConstant.API_KEY)
    suspend fun getAllNews() : Response<NewsModel>
}