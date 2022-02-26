package ru.kirea.lessonsandexamtimer.extensions

import ru.kirea.lessonsandexamtimer.Consts
import ru.kirea.lessonsandexamtimer.data.Tee
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

/**
 * Получить разниту в днях, часах и минутах между датой из [Calendar] и текущей датой.
 * @return Связка:
 * * на првом месте полное число дней между датами
 * * на втором полное число часов
 * * на третьем - полное число минут
 */
fun Calendar.getDifferenceDate(): Tee<Long, Long, Long> {
    val difference = abs(System.currentTimeMillis() - this.timeInMillis)
    //считаем полное число дней
    val day = difference / Consts.ONE_DAY
    var differenceMod = difference % Consts.ONE_DAY

    //считаем полное число часов
    val hour = differenceMod / Consts.ONE_HOUR
    differenceMod = difference % Consts.ONE_HOUR

    //считаем полное число минут
    val min = differenceMod / Consts.ONE_MINUTE

    return Tee(day, hour, min)
}

/** Получить дату в формате дд.мм.гггг */
fun Calendar.toDDMMYYY(): String {
    val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return simpleDateFormat.format(this.time)
}