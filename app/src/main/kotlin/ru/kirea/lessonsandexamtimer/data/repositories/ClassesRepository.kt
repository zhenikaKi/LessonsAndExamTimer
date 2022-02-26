package ru.kirea.lessonsandexamtimer.data.repositories

import ru.kirea.lessonsandexamtimer.data.Classes
import ru.kirea.lessonsandexamtimer.data.HomeWork

interface ClassesRepository {

    /** Получить дату экзамена */
    fun getExamDate(): Long

    /** Получить список уроков */
    fun getClasses(): List<Classes>

    /** Получить список домашних работ */
    fun getHomeWork(): List<HomeWork>
}