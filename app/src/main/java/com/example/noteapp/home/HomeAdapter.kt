package com.example.noteapp.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.ItemNoteAdapterBinding
import com.example.noteapp.model.Note

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.NoteViewHolder>() {

    private var binding: ItemNoteAdapterBinding? = null

    class NoteViewHolder constructor(itemBinding: ItemNoteAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

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

//    private var list = emptyList<Note>()
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setList(list: List<Note>) {
//
//        this.list = list
//        notifyDataSetChanged()
//
//    }
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