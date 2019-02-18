package com.apprensics.readit.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.apprensics.readit.R
import com.apprensics.readit.databinding.ArticlesFragmentBinding
import com.apprensics.readit.viewmodel.ArticlesViewModel
import com.apprensics.readit.viewmodel.data.ArticlesList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.articles_fragment.*
import java.net.ConnectException
import java.net.UnknownHostException

class ArticlesFragment : BaseMvvmFragment<ArticlesFragmentBinding, ArticlesViewModel>() {


    override val bindingVariable: Int
        get() = 0
    override val layoutId: Int
        get() = R.layout.articles_fragment
    override val viewModel: ArticlesViewModel
        get() = ViewModelProviders.of(this).get(ArticlesViewModel::class.java)

    private lateinit var articlesFragmentBinding: ArticlesFragmentBinding

    override fun onStart() {
        super.onStart()
        subscribe(viewModel.getArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showUsers(it)
            }, {
                showError()
            }))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articlesFragmentBinding = viewDataBinding
    }


    fun showUsers(data: ArticlesList) {
        if (data.error == null) {
            articlesFragmentBinding.articleList.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, data.articles)
        } else if (data.error is ConnectException || data.error is UnknownHostException) {
            Log.v("TAG","No connection, maybe inform user that data loaded from DB.")
        } else {
            showError()
        }
    }

    fun showError() {
        Toast.makeText(context, "An error occurred :(", Toast.LENGTH_SHORT).show()
    }

}
