package com.example.noteapp.home

import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentHomeBinding
import com.example.repository.model.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),
    SearchView.OnQueryTextListener, HomeAdapter.onClickDeleteListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var noteAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        goToNewFragment()
        setUpRecyclerView()

    }

    private fun observeViewModel(){

        viewModel.getAllNotes()

    }

    private fun goToNewFragment() {

        binding.fabAddNote.setOnClickListener {

            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewNoteFragment())

        }
    }

    private fun setUpRecyclerView() {

        noteAdapter = HomeAdapter(this)

        binding.noteRecyclerView.apply {

            setHasFixedSize(true)
            adapter = noteAdapter

        }

        activity?.let {

            viewModel.getNotes.observe(viewLifecycleOwner) { notes ->

                noteAdapter.differ.submitList(notes)

                updateNote(notes)

            }

        }

    }

    private fun updateNote(note: List<Note>) {

        if (note.isNotEmpty()) {

            binding.noteRecyclerView.visibility = VISIBLE
            binding.tvNoNotesAvailable.visibility = GONE

        } else {

            binding.noteRecyclerView.visibility = GONE
            binding.tvNoNotesAvailable.visibility = VISIBLE

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)

        val searchItem = menu.findItem(R.id.search_menu)
        val searchView = searchItem.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        if (query != null) {

            searchNotes(query)

        }
        return true

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {

            searchNotes(newText)

        }

        return true

    }

    private fun searchNotes(query: String?) {

        val searchQuery = "$query"
        viewModel.searchNotes(searchQuery).observe(viewLifecycleOwner) { listQuery ->

            noteAdapter.differ.submitList(listQuery)

        }

    }

    override fun deleteNoteItem(note: Note) {

        deleteNote(note)

    }

    private fun deleteNote(note: Note) {

        AlertDialog.Builder(requireContext()).apply {
            setTitle("Delete Note")
            setMessage("Are you sure want to delete this name?")
            setPositiveButton("DELETE") { _, _ ->

                lifecycleScope.launch {
                    viewModel.deleteNote(note)
                }
            }

            setNegativeButton("CANCEL", null)

        }.create().show()

    }
}
