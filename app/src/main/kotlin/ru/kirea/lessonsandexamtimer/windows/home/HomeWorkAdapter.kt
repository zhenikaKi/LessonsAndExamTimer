package ru.kirea.lessonsandexamtimer.windows.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kirea.lessonsandexamtimer.data.HomeWork
import ru.kirea.lessonsandexamtimer.databinding.ItemHomeworkBinding

class  HomeWorkAdapter(private val items: List<HomeWork>): RecyclerView.Adapter<HomeWorkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeWorkViewHolder {
        val binding = ItemHomeworkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeWorkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeWorkViewHolder, position: Int) {
        holder.setData(items[position])
    }

    override fun getItemCount() = items.size
}