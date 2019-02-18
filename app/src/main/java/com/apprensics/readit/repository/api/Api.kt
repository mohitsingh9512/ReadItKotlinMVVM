package com.apprensics.readit.repository.api

import com.apprensics.readit.repository.data.Article
import io.reactivex.Observable
import retrofit2.http.GET

interface ArticlesApi {

    @GET("5c5a891d320000271e4ec563")
    fun getArticles() : Observable<List<Article>>
}