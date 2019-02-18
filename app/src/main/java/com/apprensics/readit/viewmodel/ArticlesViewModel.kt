package com.apprensics.readit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.apprensics.readit.App
import com.apprensics.readit.repository.ArticlesRepository
import com.apprensics.readit.viewmodel.data.ArticlesList
import io.reactivex.Observable

class ArticlesViewModel(application: Application) : AndroidViewModel(application) {

    var articlesRepository = ArticlesRepository(App.appDatabase.articlesDao())

    fun getArticles() : Observable<ArticlesList> {
        return articlesRepository.getArticles()
            .map {
                ArticlesList(it.take(10), "First 10")
            }
            .onErrorReturn {
                ArticlesList(emptyList(), "An error Occured", it)
            }
    }
}