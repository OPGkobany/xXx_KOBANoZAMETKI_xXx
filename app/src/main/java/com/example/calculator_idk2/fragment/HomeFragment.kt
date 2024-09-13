package com.example.calculator_idk2.fragment

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView

import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.calculator_idk2.MainActivity
import com.example.calculator_idk2.R
import com.example.calculator_idk2.adapter.NoteAdapter
import com.example.calculator_idk2.databinding.FragmentHomeBinding
import com.example.calculator_idk2.viewmodel.NoteViewModel


class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener, MenuProvider {
// фрагмент с главным экраном

    private var homrBinding: FragmentHomeBinding? = null
    private val binding get() = homrBinding!!

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var notesAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homrBinding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        notesViewModel = (activity as MainActivity).noteViewModel
        setupHomeRecyclerView()

        binding.addNoteFab.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }
    }
    private fun updateUI(note: List<com.example.calculator_idk2.model.Note>?) {
        if (note != null) {
            if(note.isNotEmpty()) {
                //показ картинки при отсутствии изображений хдд
                binding.emptyNotesImage.visibility = View.GONE
                binding.homeRecyclerView.visibility = View.VISIBLE
            } else {
                binding.emptyNotesImage.visibility = View.VISIBLE
                binding.homeRecyclerView.visibility = View.GONE
            }
        }
    }
    private fun setupHomeRecyclerView(){
        notesAdapter = NoteAdapter()
        binding.homeRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            //задание стаггеред лейаута для ресайклера вью
            setHasFixedSize(true)
            adapter = notesAdapter
        }

        activity?.let {
            notesViewModel.getAllNotes().observe(viewLifecycleOwner){ note ->
                //происходит наблюдение за getAllNotes и список заметок отображается черкз адаптер
                notesAdapter.differ.submitList(note)
                updateUI(note)
                //апдейт интерфейса в зависимости от доступности note
            }
        }
    }

    private fun searchNote(query: String?){
        val searchQuery = "%$query"

        notesViewModel.searchNote(searchQuery).observe(this) {
            list ->
            notesAdapter.differ.submitList(list)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        //используется ...TextChange
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchNote(newText)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        homrBinding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home_menu, menu)

        val menuSearch = menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        //используется searchNote
        return false
    }
}
