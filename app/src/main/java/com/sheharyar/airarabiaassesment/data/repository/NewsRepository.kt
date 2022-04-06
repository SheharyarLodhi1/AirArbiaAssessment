package com.sheharyar.airarabiaassesment.data.repository

import com.sheharyar.airarabiaassesment.data.local.NewsListDao
import com.sheharyar.airarabiaassesment.data.remote.NyRemoteDataSource
import com.sheharyar.airarabiaassesment.utils.performDbOperation
import com.sheharyar.airarabiaassesment.utils.performGetOperation
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val remoteDataSource: NyRemoteDataSource,
    private val localDataSource: NewsListDao
) {
    fun getAllNews() = performGetOperation(
        databaseQuery = { localDataSource.getAllNews() },
        networkCall = { remoteDataSource.getAllNews() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )

    fun getNewsDetails() = performDbOperation(
        databaseQuery = { localDataSource.getAllNews() }
    )
}