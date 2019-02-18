package com.apprensics.readit.repository

import android.util.Log
import com.apprensics.readit.App
import com.apprensics.readit.repository.api.ArticlesApi
import com.apprensics.readit.repository.data.Article
import com.apprensics.readit.repository.db.ArticleDao
import io.reactivex.Observable

class ArticlesRepository(val articleDao:  ArticleDao){

    var articlesApi = App.retrofit.create(ArticlesApi::class.java)

    fun getArticles() : Observable<List<Article>> {
        return Observable.concatArray(
            getArticlesFromDb(),
            getArticlesFromApi()
        )
    }

    fun getArticlesFromDb() : Observable<List<Article>>{
        return articleDao.getArticles().filter{it.isNotEmpty()}
            .toObservable()
            .doOnNext{
                Log.v("Repo","Dispatching ${it.size} from DB")
            }
    }

    fun getArticlesFromApi() : Observable<List<Article>>{
        return articlesApi.getArticles()
            .doOnNext { Log.v("Repo","Dispatching ${it.size} from Api")
                storeAriclesInDb(it)
            }
    }

    fun storeAriclesInDb(articles : List<Article>){
        articleDao.insertAll(articles)
    }
}