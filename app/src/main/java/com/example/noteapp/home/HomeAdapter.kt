package com.example.noteapp.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.ItemNoteAdapterBinding
import com.example.noteapp.model.Note

class HomeAdapter(private val listener: onClickDeleteListener) :
    RecyclerView.Adapter<HomeAdapter.NoteViewHolder>() {

    interface onClickDeleteListener {

        fun deleteNoteItem(note: Note)

    }

    private var binding: ItemNoteAdapterBinding? = null

    inner class NoteViewHolder constructor(itemBinding: ItemNoteAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {


    }

    private val differCallBack = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {

            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {

            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        binding = ItemNoteAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return NoteViewHolder(binding!!)

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val currentNote = differ.currentList[position]

        holder.itemView.apply {

            binding?.tvNoteTitle?.text = currentNote.notTitle
            binding?.tvNoteBody?.text = currentNote.noteBode

            val random = java.util.Random()
            val color = Color.argb(
                255,
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
            )

            binding?.imageDelete?.setOnClickListener {

                listener.deleteNoteItem(currentNote)

            }

            binding?.viewColor?.setBackgroundColor(color)

        }.setOnClickListener { mView ->

            mView.findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(currentNote)
            )
        }

    }

    override fun getItemCount(): Int {

        return differ.currentList.size
    }
}