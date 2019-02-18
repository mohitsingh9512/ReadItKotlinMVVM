package com.apprensics.readit.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apprensics.readit.repository.data.Article
import io.reactivex.Single

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getArticles(): Single<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(article: List<Article>)

}