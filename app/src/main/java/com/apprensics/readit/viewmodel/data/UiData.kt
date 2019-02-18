package com.apprensics.readit.viewmodel.data

import com.apprensics.readit.repository.data.Article

data class ArticlesList(val articles : List<Article>, val message: String , val error : Throwable? = null)