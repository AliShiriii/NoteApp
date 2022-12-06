package com.example.noteapp.newNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.utils.show
import com.example.noteapp.databinding.FragmentNewNoteBinding
import com.example.noteapp.home.HomeViewModel
import com.example.repository.model.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*


@AndroidEntryPoint
class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    companion object {
        private val REUEST_CODE_STORAGE_PERMISION = 1
        private val REUEST_CODE_SELECTED_IMAGE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveNotes()

    }

    private fun insertNote() {

        val title = binding.addNoteTitle.text.toString().trim()
        val body = binding.addNoteBody.text.toString().trim()

        val calender = Calendar.getInstance()
        val currentTime = DateFormat.getDateInstance(DateFormat.FULL).format(calender.time)
//        var date = binding.date.text.toString().trim()
//        date = currentTime

        if (title.isNotEmpty()) {

            val note = Note(0, title, body, currentTime)

                viewModel.insertNote(note)

                Toast.makeText(
                    requireContext(), "Note saved successfully",
                    Toast.LENGTH_LONG
                ).show()


            findNavController().navigate(NewNoteFragmentDirections.actionNewNoteFragmentToHomeFragment())

        } else {

            activity?.show("Please enter note title!!!!!!!")

        }
    }

    private fun saveNotes() {

        binding.fabSaveNotes.setOnClickListener {

            insertNote()

        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        when (item.itemId) {
//
//            R.id.saveMenu -> {
//
//                insertNote()
//
//            }
//        }
//
//        return super.onOptionsItemSelected(item)
//
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        menu.clear()
//        inflater.inflate(R.menu.new_note_menu, menu)
//
}