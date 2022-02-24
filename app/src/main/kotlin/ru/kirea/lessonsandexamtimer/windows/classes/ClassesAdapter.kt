package ru.kirea.lessonsandexamtimer.windows.classes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kirea.lessonsandexamtimer.data.Classes
import ru.kirea.lessonsandexamtimer.databinding.ItemClassListBinding
import ru.kirea.lessonsandexamtimer.windows.home.ClassesAdapterListener

class  ClassesAdapter(private val items: List<Classes>,
                      private val listener: ClassesAdapterListener
): RecyclerView.Adapter<ClassesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesViewHolder {
        val binding = ItemClassListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClassesViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ClassesViewHolder, position: Int) {
        holder.setData(items[position])
    }

    override fun getItemCount() = items.size
}