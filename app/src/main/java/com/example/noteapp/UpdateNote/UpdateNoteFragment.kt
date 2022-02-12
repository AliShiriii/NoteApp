package com.example.noteapp.UpdateNote

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.R
import com.example.noteapp.Utils.show
import com.example.noteapp.databinding.FragmentUpdateNoteBinding
import com.example.noteapp.home.HomeViewModel
import com.example.repository.model.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {

    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val args: UpdateNoteFragmentArgs by navArgs()
    private lateinit var current: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        current = args.note!!

        binding.addNoteTitleUpdate.setText(current.notTitle)
        binding.addNoteBodyUpdate.setText(current.noteBode)
        binding.date.setText(current.date)

        binding.floatingActionButtonDone.setOnClickListener {

            val title = binding.addNoteTitleUpdate.text.toString().trim()
            val body = binding.addNoteBodyUpdate.text.toString().trim()
            val date = binding.date.text.toString().trim()

            if (title.isNotEmpty()) {

                val note = Note(current.id, title, body, date)
                activity?.show("Note updated")

                lifecycleScope.launch {

                    viewModel.updateNote(note)

                }

                findNavController().navigate(UpdateNoteFragmentDirections.actionUpdateNoteFragmentToHomeFragment())

            } else {

                activity?.show("Please enter note name")

            }
        }

    }

//    private fun deleteNote() {
//
//        AlertDialog.Builder(requireContext()).apply {
//            setTitle("Delete Note")
//            setMessage("Are you sure want to delete this name?")
//            setPositiveButton("DELETE") { _, _ ->
//
//                lifecycleScope.launch {
//                    viewModel.deleteNote(current)
//                }
//
//                findNavController().navigate(UpdateNoteFragmentDirections.actionUpdateNoteFragmentToHomeFragment())
//
//            }
//
//            setNegativeButton("CANCEL", null)
//
//        }.create().show()
//
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        when (item.itemId) {
//
//            R.id.delete_menu -> {
//
//                deleteNote()
//
//            }
//
//        }
//
//        return super.onOptionsItemSelected(item)
//
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//
//        menu.clear()
//        inflater.inflate(R.menu.update_menu, menu)
//
//        super.onCreateOptionsMenu(menu, inflater)
//
//    }

}