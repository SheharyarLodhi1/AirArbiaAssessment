package com.sheharyar.airarabiaassesment.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sheharyar.airarabiaassesment.data.entities.NewsResultsModel

@Dao
interface NewsListDao {
    @Query("SELECT * FROM news")
    fun getAllNews() : LiveData<List<NewsResultsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<NewsResultsModel>)
}