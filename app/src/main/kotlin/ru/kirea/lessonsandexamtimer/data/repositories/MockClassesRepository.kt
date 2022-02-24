package ru.kirea.lessonsandexamtimer.data.repositories

import android.util.Log
import ru.kirea.lessonsandexamtimer.Consts
import ru.kirea.lessonsandexamtimer.data.Classes
import ru.kirea.lessonsandexamtimer.data.ClassesNumber
import ru.kirea.lessonsandexamtimer.data.HomeWork
import java.util.*

/** Замоканный репозиторий */
class MockClassesRepository: ClassesRepository {
    //список доступных уроков
    private val classesList: List<Pair<String, String>> = listOf(
        Pair("Математика", "Учимся считать без калькулятора"),
        Pair("Русский язык", "Пишем без ошибок"),
        Pair("Английский язык", "Изучаем второй язык"),
        Pair("Физкультура", "Сдаем физические нормативы"),
        Pair("Труд", "Мальчики отдельно, девочки отдельно"),
        Pair("Музыка", "Разрабатываем голос"),
        Pair("Физика", "Изучаем свет и его особенности"),
        Pair("Химия", "Таблица Менделеева и все такое"),
        Pair("Информатика", "Знакомимся с компютерами"),
        Pair("Литератору", "Читаем, читаем и еще раз читаем")
    )

    private val examDate: Long = generateExamDate()
    private val classes: List<Classes> = generateClasses()
    private val homeWorks: List<HomeWork> = generateAllHomeWork()

    /** Получить дату экзамена */
    override fun getExamDate(): Long = examDate

    /** Получить список уроков */
    override fun getClasses(): List<Classes> = classes

    /** Получить список домашних работ */
    override fun getHomeWork(): List<HomeWork> = homeWorks

    /**
     * Сформировать случайную дату экзамена.
     * @return сформированная дата.
     */
    private fun generateExamDate(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + Random().nextInt(5))
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + Random().nextInt(5))
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + Random().nextInt(20) + 1)
        Log.d(Consts.TAG_LOG, String.format("Дата экзамена: %02d.%02d.%04d %02d:%02d",
            calendar.get(Calendar.DAY_OF_MONTH),
            (calendar.get(Calendar.MONTH) +1),
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE)
        ))

        return calendar.timeInMillis
    }

    /**
     * Сформировать случайные уроки.
     * @return список уроков на сегодня и завтра.
     */
    private fun generateClasses(): List<Classes> {
        val result: MutableList<Classes> = mutableListOf()
        //количество дней, за которые дступны уроки
        for (date in 1..2) {
            val classDate = getClassDate(date == 1)
            val countClasses = Random().nextInt(6) + 1
            val randomClass = Random()
            for (number in 1..countClasses) {
                val curClass = classesList[randomClass.nextInt(9)]
                val classNumber = ClassesNumber.values().find { it.number == number }
                val zoomUrl = if (Random().nextBoolean()) {
                    "https://lesson.zoom.us/j/2345346234?pwd=TnasF1dnhlc0QrsdfsdhgsU4elhKZz09#success"
                }
                else null
                classNumber?.let {
                    result.add(Classes(
                        name = curClass.first,
                        description = curClass.second,
                        date = classDate,
                        classNumber = it,
                        zoomUrl = zoomUrl
                    ))
                }
            }
        }
        return result
    }

    /**
     * Получить дату урока.
     * @param today true - урок идет сегодня, false - урок идет в ближайший рабочий день.
     * @return сформированная дата урока/
     */
    private fun getClassDate(today: Boolean): Long {
        var result = System.currentTimeMillis()
        if (!today) {
            val calendar = Calendar.getInstance()
            do {
                result += Consts.ONE_DAY
                calendar.timeInMillis = result
            } while (calendar.get(Calendar.DAY_OF_WEEK) == 5 || calendar.get(Calendar.DAY_OF_WEEK) == 6)
        }

        return result
    }

    /** Сформировать список домашних работ */
    private fun generateAllHomeWork(): List<HomeWork> {
        val result: MutableList<HomeWork> = mutableListOf()
        result.addAll(generateHomeWork(Random().nextInt(3), asExpire = true))
        result.addAll(generateHomeWork(Random().nextInt(5)+1, asExpire = false))
        return result
    }

    /** просроченные домашние работы */
    private fun generateHomeWork(count: Int, asExpire: Boolean): List<HomeWork> {
        val result: MutableList<HomeWork> = mutableListOf()
        for (ind in 1..count) {
            result.add(HomeWork(
                name = classesList[Random().nextInt(9)].first,
                description = "Описание ${if (asExpire) "просроченной" else "следующей"} домашней работы #$ind",
                date = getRandomDate(asExpire)
            ))
        }
        return result
    }

    /** Сформировать дату для домашней работы */
    private fun getRandomDate(asExpire: Boolean): Long {
        val type = Random().nextInt(2)
        val countEI = Random().nextInt(10) + 1
        val minus = if (asExpire) -1 else 1
        val dateTimeValue = when (type) {
            0 -> countEI * Consts.ONE_DAY * minus
            1 -> countEI * Consts.ONE_HOUR * minus
            else -> countEI * Consts.ONE_MINUTE * minus
        }
        return System.currentTimeMillis() + dateTimeValue
    }
}