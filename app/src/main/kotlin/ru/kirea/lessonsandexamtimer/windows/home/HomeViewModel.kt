package ru.kirea.lessonsandexamtimer.windows.home

import android.content.res.Resources
import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ru.kirea.lessonsandexamtimer.Consts
import ru.kirea.lessonsandexamtimer.base.BaseViewModel
import ru.kirea.lessonsandexamtimer.data.Classes

class HomeViewModel(private val service: HomeService) : BaseViewModel<HomeState>() {
    private val job: Job = startTimerToExam()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    /** Получить остаточное время до экзамена */
    fun getTimeToExam() {
        coroutineScope.launch {
            Log.d(Consts.TAG_LOG, "HomeViewModel.getTimeToExam -> postValue")
            liveData.postValue(HomeState.TimeToExam(service.getTimeToExam()))
        }
    }

    /** Получить список уроков */
    fun getClasses(resources: Resources) {
        coroutineScope.launch {
            val classesList: List<Classes> = service.getClasses()
            val currentClass: Int = service.getCurrentClass(classesList)
            val countClassesText = service.getCountClassesText(classesList, resources)
            Log.d(Consts.TAG_LOG, "HomeViewModel.getClasses -> postValue")
            liveData.postValue(HomeState.ClassesList(classesList, currentClass, countClassesText))
        }
    }

    /** Получить остаточное время до экзамена */
    fun getHomeWorks() {
        coroutineScope.launch {
            Log.d(Consts.TAG_LOG, "HomeViewModel.getHomeWorks -> postValue")
            liveData.postValue(HomeState.HomeWorkList(service.getHomeWork()))
        }
    }

    /** Запустить таймер остатка времени до экзамена */
    private fun startTimerToExam(): Job {
        return coroutineScope.launch {
            while (isActive) {
                getTimeToExam()
                delay(Consts.ONE_MINUTE)
            }
        }
    }
}