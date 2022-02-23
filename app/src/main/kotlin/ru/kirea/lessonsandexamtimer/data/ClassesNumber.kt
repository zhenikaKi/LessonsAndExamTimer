package ru.kirea.lessonsandexamtimer.data

/** Время уроков */
enum class ClassesNumber(val number: Int, val timeStart: ClassTime, val timeEnd: ClassTime) {
    LESSON_1(1, ClassTime(8, 0), ClassTime(8, 40)),
    LESSON_2(2, ClassTime(8, 50), ClassTime(9, 30)),
    LESSON_3(3, ClassTime(9, 50), ClassTime(10, 30)),
    LESSON_4(4, ClassTime(10, 50), ClassTime(11, 30)),
    LESSON_5(5, ClassTime(11, 40), ClassTime(12, 20)),
    LESSON_6(6, ClassTime(12, 30), ClassTime(13, 10)),
    LESSON_7(7, ClassTime(13, 20), ClassTime(14, 0));

    override fun toString(): String {
        return "${timeStart.hour}:${String.format("%02d", timeStart.minute)} - ${timeEnd.hour}:${String.format("%02d", timeEnd.minute)}"
    }
}