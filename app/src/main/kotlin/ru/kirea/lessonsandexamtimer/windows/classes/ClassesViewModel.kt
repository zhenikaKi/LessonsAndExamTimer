package ru.kirea.lessonsandexamtimer.windows.classes

import kotlinx.coroutines.launch
import ru.kirea.lessonsandexamtimer.base.BaseViewModel
import ru.kirea.lessonsandexamtimer.windows.home.HomeService

class ClassesViewModel(private val service: HomeService) : BaseViewModel<ClassesState>() {

    /** Получить список уроков */
    fun getClasses() {
        coroutineScope.launch {
            liveData.postValue(ClassesState.ClassesList(service.getClasses()))
        }
    }
}