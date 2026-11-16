package com.example.Aditya_3tia.Note

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Aditya_3tia.Data.AppDatabase
import com.example.Aditya_3tia.Data.Entity.NoteEntity
import com.example.Aditya_3tia.R
import com.example.Aditya_3tia.databinding.FragmentHomeBinding
import com.example.Aditya_3tia.databinding.FragmentNotesBinding
import kotlinx.coroutines.launch

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private lateinit var adapter: NoteAdapter
    private val notes = mutableListOf<NoteEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Notes"

        binding.fabAddNote.setOnClickListener {
            startActivity(Intent(requireContext(), NoteFormActivity::class.java))
        }
        binding.rvNotes.addItemDecoration(
            DividerItemDecoration(
                requireContext(), DividerItemDecoration.VERTICAL
            )
        )
        db = AppDatabase.getInstance(requireContext())
        adapter = NoteAdapter(notes) { note ->
            deleteNote(note)
        }
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter
        fetchNotes()
    }
    override fun onResume() {
        super.onResume()
        if (_binding != null) fetchNotes()
    }
private fun fetchNotes() {
    viewLifecycleOwner.lifecycleScope.launch {
        val result = db.noteDao().getAll()
        notes.clear()
        notes.addAll(result)
        adapter.notifyDataSetChanged()
    }
}
private fun deleteNote(note: NoteEntity) {
    viewLifecycleOwner.lifecycleScope.launch {
        db.noteDao().delete(note)
        fetchNotes()
    }
}
}