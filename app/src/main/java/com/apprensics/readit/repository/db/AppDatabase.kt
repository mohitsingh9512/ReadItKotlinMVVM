package com.apprensics.readit.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apprensics.readit.repository.data.Article

@Database(entities = arrayOf(Article::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articlesDao() : ArticleDao
}