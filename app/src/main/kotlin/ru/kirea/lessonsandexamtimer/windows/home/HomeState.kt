package ru.kirea.lessonsandexamtimer.windows.home

import ru.kirea.lessonsandexamtimer.data.Classes
import ru.kirea.lessonsandexamtimer.data.HomeWork
import ru.kirea.lessonsandexamtimer.data.Tee

sealed class HomeState {
    data class TimeToExam(val time: Tee<Long, Long, Long>): HomeState()

    data class ClassesList(val classes: List<Classes>,
                           val currentClass: Int,
                           val countClasses: String): HomeState()

    data class HomeWorkList(val homework: List<HomeWork>): HomeState()
}
