package com.apprensics.readit

import android.os.Bundle
import com.apprensics.readit.view.ArticlesFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null)
            supportFragmentManager.beginTransaction().replace(R.id.frag_container, ArticlesFragment()).commit()
    }
}
