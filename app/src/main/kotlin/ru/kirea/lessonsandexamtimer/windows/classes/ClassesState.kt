package ru.kirea.lessonsandexamtimer.windows.classes

import ru.kirea.lessonsandexamtimer.data.Classes

sealed class ClassesState {
    data class ClassesList(val classes: List<Classes>): ClassesState()
}
