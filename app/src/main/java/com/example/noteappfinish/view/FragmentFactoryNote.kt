package com.example.noteappfinish.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.noteappfinish.adapter.NoteAdapter
import javax.inject.Inject

class FragmentFactoryNote @Inject constructor(
         var adapter : NoteAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (className) {
            FragmentFeed::class.java.name -> FragmentFeed(adapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}