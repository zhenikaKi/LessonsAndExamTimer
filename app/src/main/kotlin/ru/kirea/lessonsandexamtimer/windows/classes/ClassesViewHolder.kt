package ru.kirea.lessonsandexamtimer.windows.classes

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.kirea.lessonsandexamtimer.data.Classes
import ru.kirea.lessonsandexamtimer.databinding.ItemClassListBinding
import ru.kirea.lessonsandexamtimer.extensions.toDDMMYYY
import ru.kirea.lessonsandexamtimer.windows.home.ClassesAdapterListener
import java.util.*

class ClassesViewHolder(private val binding: ItemClassListBinding,
                        private val listener: ClassesAdapterListener
): RecyclerView.ViewHolder(binding.root) {

    /** Отобразить данные по уроку */
    fun setData(classes: Classes) {
        with(binding) {
            classTitle.text = classes.name
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = classes.date
            val time = "${calendar.toDDMMYYY()} ${classes.classNumber}"
            classTimeTitle.text = time
            classDescription.text = classes.description

            zoomBlock.isVisible = classes.zoomUrl != null
            zoomBlock.setOnClickListener { listener.buttonZoomClick(classes.zoomUrl) }
        }
    }
}