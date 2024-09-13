package com.example.calculator_idk2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator_idk2.model.Note
import com.example.calculator_idk2.databinding.NoteLayoutBinding
import com.example.calculator_idk2.fragment.HomeFragmentDirections


class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>( ) {

    class NoteViewHolder(val itemBinding: NoteLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    //сравнение старых и новых данных
    private val  differCallback = object: DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
           return oldItem.id == newItem.id &&
                   oldItem.noteDesc == newItem.noteDesc &&
                   oldItem.noteTitle == newItem.noteTitle
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }
    //присоединение данного адаптера к интерфейсу
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    //передача данных на соответстующий элемент навграфа
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.itemBinding.noteTitle.text=currentNote.noteTitle
        holder.itemBinding.noteDesc.text = currentNote.noteDesc

        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            //☠☠☠
            it.findNavController().navigate(direction)



        }
    }
}