package com.sheharyar.airarabiaassesment.data.remote

import javax.inject.Inject

class NyRemoteDataSource @Inject constructor(
    private val nyService: NyService
): BaseDataSource() {

    suspend fun getAllNews() = getResult { nyService.getAllNews() }
}