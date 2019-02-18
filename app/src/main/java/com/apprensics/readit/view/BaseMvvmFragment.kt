package com.apprensics.readit.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import com.apprensics.readit.BaseActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseMvvmFragment<d : ViewDataBinding, v : AndroidViewModel> : Fragment() {
    lateinit var viewDataBinding: d

    private set
    private var mViewModel: v? = null
    private var mRootView: View? = null
    var baseActivity: BaseActivity? = null
    private set
    protected abstract val bindingVariable: Int

    abstract val layoutId: Int

    abstract val viewModel: v

    interface Callback {
        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.baseActivity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewDataBinding.setVariable(bindingVariable, mViewModel)
        mRootView = viewDataBinding.root
        return mRootView
    }

    val subscriptions  =  CompositeDisposable()

    fun subscribe(disposable: Disposable) : Disposable {
        subscriptions.add(disposable)
        return subscriptions
    }

    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }
}
