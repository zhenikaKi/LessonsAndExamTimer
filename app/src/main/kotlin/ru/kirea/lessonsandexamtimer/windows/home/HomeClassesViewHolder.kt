package ru.kirea.lessonsandexamtimer.windows.home

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.kirea.lessonsandexamtimer.data.Classes
import ru.kirea.lessonsandexamtimer.databinding.ItemClassBinding
import ru.kirea.lessonsandexamtimer.extensions.toDDMMYYY
import java.util.*

class HomeClassesViewHolder(private val binding: ItemClassBinding,
                            private val listener: HomeClassesAdapterListener): RecyclerView.ViewHolder(binding.root) {

    /** Отобразить данные по уроку */
    fun setData(classes: Classes) {
        with(binding) {
            classTitle.text = classes.name
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = classes.date
            val description = "${calendar.toDDMMYYY()} ${classes.classNumber}"
            classDescription.text = description

            zoomBlock.isVisible = classes.zoomUrl != null
            zoomBlock.setOnClickListener { listener.buttonZoomClick(classes.zoomUrl) }
        }
    }
}