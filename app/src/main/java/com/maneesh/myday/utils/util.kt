package com.maneesh.myday.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.util.Locale
import java.util.TimeZone
import kotlin.math.abs


class Util {
    companion object {
        private fun getDateFormatter(
            format: String = "yyyy-MM-dd",
            timeZoneID: String = "UTC"
        ): SimpleDateFormat {
            return SimpleDateFormat(format, Locale.CANADA).apply {
                this.timeZone = TimeZone.getTimeZone(timeZoneID)
            }
        }

        val startDate = LocalDate.of(2023, 1, 1)
        val members = mapOf<Int, String>(
            0 to "Bharath",
            1 to "Comrade",
            2 to "Pa1",
            3 to "Resh",
            4 to "GC",
            5 to "Man",
            6 to "Mr C",
            7 to "Jay"
        )

        fun getPreviousDayEpoch(utcEpoch: Long, noOfDays: Long = 1L): Long {
            val instant = Instant.ofEpochMilli(utcEpoch)
            val utcDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC)
            return utcDateTime.minusDays(noOfDays).toInstant(ZoneOffset.UTC).toEpochMilli()
        }

        fun getNextDayEpoch(utcEpoch: Long, noOfDays: Long = 1L): Long {
            val instant = Instant.ofEpochMilli(utcEpoch)
            val utcDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC)
            return utcDateTime.plusDays(noOfDays).toInstant(ZoneOffset.UTC).toEpochMilli()
        }

        fun getFormattedDateFromEpoch(epoch: Long, format: String = "yyyy-MM-dd"): String {
            val formatter = getDateFormatter()
            return formatter.format(epoch)
        }

        fun getDate(isPrevious: Boolean = false, noOfDays: Long = 0L): String {
            val formatter = getDateFormatter("yyyy-MM-dd")
            if (isPrevious) {
                return LocalDate.now().minusDays(noOfDays).toString()
            }
            return LocalDate.now().plusDays(noOfDays).toString()
        }

        private fun getMemberIndex(differenceDays: Int): Int {
            if (differenceDays < 0) {
                return if (abs(differenceDays) <= 8) members.size - abs(differenceDays) else members.size - (abs(
                    differenceDays
                ) % 8)
            }
            if (differenceDays <= 0 && differenceDays < 8) {
                return differenceDays
            } else {
                return differenceDays % 8
            }
        }

        fun getFutureSchedule(epochMilli: Long): String {
            try {
                val today = LocalDate.now()
                val differenceDays = ChronoUnit.DAYS.between(
                    today, LocalDate.ofInstant(
                        Instant.ofEpochMilli(epochMilli),
                        ZoneId.of("UTC")
                    )
                ).toInt()
                val nameIndex: Int = getMemberIndex(differenceDays)
                Log.i("diff", differenceDays.toString())
                Log.i("diff", nameIndex.toString())
                return members.get(nameIndex)!!
            }catch(e:NullPointerException){
                return members[0]!!
            }
        }
    }
}



