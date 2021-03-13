package com.example.noteappfinish.view

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteappfinish.R
import com.example.noteappfinish.adapter.NoteAdapter
import com.example.noteappfinish.databinding.FragmentFeedBinding
import com.example.noteappfinish.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentFeed @Inject constructor(
        private var adapter : NoteAdapter
): Fragment(R.layout.fragment_feed){

    private  var fragmentBinding : FragmentFeedBinding? = null
    private lateinit var viewModel : NoteViewModel

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
           val selectedNote = adapter.noteListAdapter[layoutPosition]
            viewModel.deleteNote(selectedNote)

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFeedBinding.bind(view)
        fragmentBinding = binding

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentFeed_to_fragmentAdd)
        }
        binding.rv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter = adapter

        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.rv)

        //SearchView
        binding.searchView.isSubmitButtonEnabled = false
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               if(newText != null){
                   searchNote(newText)
               }
                return true
            }

        })

        observeNoteList()

    }

    private fun observeNoteList() {
        viewModel.noteList.observe(viewLifecycleOwner, Observer {
            list->
            list?.let {
                adapter.noteListAdapter = it

            }
        })
    }


    private fun searchNote(query: String?){
        val searchQuery = "%$query%"
        viewModel.searchNote(searchQuery).observe(viewLifecycleOwner, Observer {
            list->
            adapter.noteListAdapter = list
        })

    }
}


