 package com.example.noteappfinish.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteappfinish.R
import com.example.noteappfinish.databinding.FragmentAddBinding
import com.example.noteappfinish.model.Note
import com.example.noteappfinish.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAdd : Fragment(R.layout.fragment_add) {

    private var fragmentbinding : FragmentAddBinding? = null
    private lateinit var viewModel : NoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddBinding.bind(view)
        fragmentbinding = binding

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)


        binding.saveBtn.setOnClickListener {
            val title =binding.editTextTitle.text.toString()
            val note = binding.editTextNote.text.toString()
            viewModel.createNote(title,note)
            findNavController().navigate(R.id.action_fragmentAdd_to_fragmentFeed)
        }
        binding.backToFeedPage.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentAdd_to_fragmentFeed)
        }
    }
}
