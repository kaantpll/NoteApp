package com.example.noteappfinish.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappfinish.R
import com.example.noteappfinish.model.Note
import com.example.noteappfinish.view.FragmentFeedDirections
import javax.inject.Inject

class NoteAdapter @Inject constructor() : RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    class MyViewHolder(var view : View) : RecyclerView.ViewHolder(view)

    private val diffUtil = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
          return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerViewDiff = AsyncListDiffer(this,diffUtil)

    var noteListAdapter : List<Note>
    get() = recyclerViewDiff.currentList
    set(value) = recyclerViewDiff.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val cardNoteTitle = holder.view.findViewById<TextView>(R.id.card_title)
       val cardNoteText = holder.view.findViewById<TextView>(R.id.card_note)

        val currentNote = recyclerViewDiff.currentList[position]

        val noteList = noteListAdapter[position]
        holder.view.apply {
            cardNoteTitle.text = "${noteList.noteTitle}"
            cardNoteText.text = "${noteList.note}"
        }
        holder.itemView.setOnClickListener { view->

            val direction = FragmentFeedDirections.actionFragmentFeedToFragmentUpdate(currentNote)
            view.findNavController().navigate(direction)

            }
    }

    override fun getItemCount(): Int = noteListAdapter.size

}