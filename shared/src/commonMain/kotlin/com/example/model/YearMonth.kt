package com.example.model

import com.example.utils.getCurrentTimeMillis
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

data class YearMonth(
    val year: Int,
    val month: Int // 0 = Janvier ... 11 = Décembre (convention d'origine conservée)
) {
    /** Remplace SimpleDateFormat("MMMM yyyy", Locale.FRENCH). */
    val displayLabel: String
        get() = "${MOIS[month]} $year"

    fun previous(): YearMonth =
        if (month == 0) YearMonth(year - 1, 11) else YearMonth(year, month - 1)

    fun next(): YearMonth =
        if (month == 11) YearMonth(year + 1, 0) else YearMonth(year, month + 1)

    fun containsTimestamp(timestamp: Long): Boolean = fromTimestamp(timestamp) == this

    /** Remplace les cal.set(...) de java.util.Calendar. */
    fun toMillis(day: Int, hour: Int): Long =
        LocalDateTime(
            year = year,
            monthNumber = month + 1, // kotlinx-datetime : mois 1..12
            dayOfMonth = day,
            hour = hour,
            minute = 0,
            second = 0,
            nanosecond = 0
        ).toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()

    companion object {
        private val MOIS = listOf(
            "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
            "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"
        )

        fun current(): YearMonth = fromTimestamp(getCurrentTimeMillis())

        fun fromTimestamp(timestamp: Long): YearMonth {
            val dt = Instant.fromEpochMilliseconds(timestamp)
                .toLocalDateTime(TimeZone.currentSystemDefault())
            return YearMonth(dt.year, dt.monthNumber - 1)
        }
    }
}