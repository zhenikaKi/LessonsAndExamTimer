package ru.kirea.lessonsandexamtimer.windows.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kirea.lessonsandexamtimer.data.Classes
import ru.kirea.lessonsandexamtimer.databinding.ItemClassBinding

class  HomeClassesAdapter(private val items: List<Classes>,
                          private val listener: HomeClassesAdapterListener):
    RecyclerView.Adapter<HomeClassesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeClassesViewHolder {
        val binding = ItemClassBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeClassesViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: HomeClassesViewHolder, position: Int) {
        holder.setData(items[position])
    }

    override fun getItemCount() = items.size
}