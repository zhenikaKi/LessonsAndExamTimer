package ru.kirea.lessonsandexamtimer.data

/** сущность домашней работы */
data class HomeWork(

    /** Название урока */
    val name: String,

    /** Описание домашней работы */
    val description: String,

    /** Дата сдачи домашней работы */
    val date: Long
)
