package com.sheharyar.airarabiaassesment.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sheharyar.airarabiaassesment.data.entities.NewsResultsModel
import com.sheharyar.airarabiaassesment.utils.Converter

@Database(entities = [NewsResultsModel::class], version = 6, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsListDao(): NewsListDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "news")
                .fallbackToDestructiveMigration()
                .build()
    }

}