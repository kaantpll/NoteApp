package com.example.noteappfinish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteappfinish.view.FragmentFactoryNote
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory : FragmentFactoryNote
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = factory
        setContentView(R.layout.activity_main)



    }
}