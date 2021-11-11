package com.example.noteapp.home

import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentHomeBinding
import com.example.noteapp.model.Note
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),
    SearchView.OnQueryTextListener {

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

        goToNewFragment()
        setUpRecyclerView()

    }

    private fun goToNewFragment() {

        binding.fabAddNote.setOnClickListener {

            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewNoteFragment())

        }
    }

    private fun setUpRecyclerView() {

        noteAdapter = HomeAdapter()

        binding.recyclerView.apply {

            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL

            )

            setHasFixedSize(true)
            adapter = noteAdapter

        }

        activity?.let {

            viewModel.getAllNotes().observe(viewLifecycleOwner, { notes ->

                noteAdapter.differ.submitList(notes)

                updateNote(notes)

            })

        }

    }

    private fun updateNote(note: List<Note>) {

        if (note.isNotEmpty()) {

            binding.recyclerView.visibility = VISIBLE
            binding.tvNoNotesAvailable.visibility = GONE

        } else {

            binding.recyclerView.visibility = GONE
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

//    override fun onDestroy() {
//        super.onDestroy()
//
//        _binding = null
//
//    }

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
        viewModel.searchNotes(searchQuery).observe(viewLifecycleOwner, { listQuery ->

            noteAdapter.differ.submitList(listQuery)

        })

    }
}
