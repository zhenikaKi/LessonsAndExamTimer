package ru.kirea.lessonsandexamtimer.windows.home

import android.content.res.Resources
import android.util.Log
import ru.kirea.lessonsandexamtimer.Consts
import ru.kirea.lessonsandexamtimer.R
import ru.kirea.lessonsandexamtimer.data.Classes
import ru.kirea.lessonsandexamtimer.data.ClassesNumber
import ru.kirea.lessonsandexamtimer.data.HomeWork
import ru.kirea.lessonsandexamtimer.data.Tee
import ru.kirea.lessonsandexamtimer.data.repositories.ClassesRepository
import ru.kirea.lessonsandexamtimer.extensions.getDifferenceDate
import java.util.*

class HomeService(private val repository: ClassesRepository) {
    /** Получить остаточное время до экзамена */
    fun getTimeToExam(): Tee<Long, Long, Long> {
        val examDate = repository.getExamDate()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = examDate
        return calendar.getDifferenceDate()
    }

    /** Получить список уроков */
    fun getClasses(): List<Classes> = repository.getClasses()

    /** Получить список домашних работ */
    fun getHomeWork(): List<HomeWork> = repository.getHomeWork()/*.sortedBy { it.date }*/

    /**
     * Найти позицию текущего или ближайшего урока.
     * @param classesList список уроков.
     * @return позиция нужного урока.
     */
    fun getCurrentClass(classesList: List<Classes>): Int {
        //получаем текущую дату и время
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        classesList.forEachIndexed { index, classes ->
            //получаем дату урока
            val classCalendar = Calendar.getInstance()
            classCalendar.timeInMillis = classes.date
            val classDay = classCalendar.get(Calendar.DAY_OF_MONTH)
            //дошли до следующего дня с уроками, возвращаем самый первый урок следующего дня
            if (classDay > day) {
                return index
            }

            //смотрим текущий день
            if (hourInRange(hour, classes.classNumber) && minInRange(minute, classes.classNumber)) {
                return index
            }

        }

        return 0
    }

    /** Получить количество уроков за текущий или следующий день */
    fun getCountClassesText(classesList: List<Classes>, resources: Resources): String {
        //получаем текущую дату
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        val currentClass = getCurrentClass(classesList)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = classesList[currentClass].date
        val currentClassDay = calendar.get(Calendar.DAY_OF_MONTH)
        val countClasses = classesList.filter { classes ->
            val calendarFilter = Calendar.getInstance()
            calendarFilter.timeInMillis = classes.date
            return@filter calendarFilter.get(Calendar.DAY_OF_MONTH) == currentClassDay
        }.size

        return if (currentClassDay <= day) {
            resources.getQuantityString(R.plurals.count_today_classes, countClasses, countClasses)
        }
        else {
            resources.getQuantityString(R.plurals.count_next_class, countClasses, currentClassDay, countClasses)
        }
    }

    /** Проверить, входит ли указанный час во период урока */
    private fun hourInRange(hour: Int, classNumber: ClassesNumber): Boolean {
        return classNumber.timeStart.hour <= hour && classNumber.timeEnd.hour >= hour
    }

    /** Проверить, входят ли указанные минуты во период урока */
    private fun minInRange(minute: Int, classNumber: ClassesNumber): Boolean {
        return classNumber.timeStart.minute <= minute && classNumber.timeEnd.minute >= minute
    }
}