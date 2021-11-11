package com.example.noteapp.NewNote

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.Utils.show
import com.example.noteapp.databinding.FragmentNewNoteBinding
import com.example.noteapp.home.HomeViewModel
import com.example.noteapp.model.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.InputStream


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

        insertNote()

    }

    private fun insertNote() {

        val title = binding.addNoteTitle.text.toString().trim()
        val body = binding.addNoteBody.text.toString().trim()

        if (title.isNotEmpty()) {

            val note = Note(0, title, body)

            lifecycleScope.launch {

                viewModel.insertNote(note)

                Toast.makeText(
                    requireContext(), "Note saved successfully",
                    Toast.LENGTH_LONG
                ).show()

            }

            findNavController().navigate(NewNoteFragmentDirections.actionNewNoteFragmentToHomeFragment())

        } else {

            activity?.show("Please enter note title!!!!!!!")

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.saveMenu -> {

                insertNote()

            }
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.new_note_menu, menu)

    }

}