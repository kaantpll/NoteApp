package com.example.noteappfinish.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteappfinish.R
import com.example.noteappfinish.databinding.FragmentUpdateBinding
import com.example.noteappfinish.model.Note
import com.example.noteappfinish.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine

@AndroidEntryPoint
class FragmentUpdate : Fragment(R.layout.fragment_update) {

    private lateinit var viewModel : NoteViewModel
    private lateinit var currentNote : Note
    private val args: FragmentUpdateArgs by navArgs()
    private var fragmentBinding : FragmentUpdateBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        currentNote = args.note!!

        val binding = FragmentUpdateBinding.bind(view)
        fragmentBinding = binding

        binding.backToFeedPage.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentUpdate_to_fragmentFeed)
        }

        binding.editTextTitle.setText(currentNote.noteTitle)
        binding.editTextNote.setText(currentNote.note)

        binding.updateBtn.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            val note = binding.editTextNote.text.toString().trim()

            if(title.isNotEmpty() && note.isNotEmpty()){
                val note = Note(title,note,currentNote.mid)
                viewModel.updateNote(note)
            }
            findNavController().navigate(R.id.action_fragmentUpdate_to_fragmentFeed)
        }




    }
}