package com.example.catbook.ui

import android.os.Bundle
import com.example.catbook.R
import dagger.android.support.DaggerAppCompatActivity

class HomeActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}