package ru.kirea.lessonsandexamtimer.data

data class Classes(
    /** Название урока */
    val name: String,

    /** Описание урока */
    val description: String,

    /** Дата урока */
    val date: Long,

    /** Номер урока */
    val classNumber: ClassesNumber,

    /** Ссылка на zoom, если он есть на уроке */
    val zoomUrl: String? = null
)
