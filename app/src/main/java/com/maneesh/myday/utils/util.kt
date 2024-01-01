package com.maneesh.myday.utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Locale
import java.util.TimeZone


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

        fun getFormattedDateFromEpoch(epoch: Long): String {
            val formatter = getDateFormatter()
            return formatter.format(epoch)
        }

        fun getDate(isPrevious:Boolean=false, noOfDays:Long=0L):String{
            val formatter = getDateFormatter("yyyy-MM-dd")
            if(isPrevious){
                return LocalDate.now().minusDays(noOfDays).toString()
            }
            return LocalDate.now().plusDays(noOfDays).toString()
        }
    }
}



