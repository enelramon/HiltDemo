package com.sagrd.hiltdemo.data.remote.dto

import com.squareup.moshi.Json
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class TicketResponse(
    val idTicket: Int,
    val fecha: String,
    val idCliente: Int,
    @Json(name = "solicitadoPor")
    val solicitado: String,
    val empresa: String,
    val asunto: String
) {
    val currentDateTime = LocalDateTime.now()

    fun getPeriod(): String {
        // Parse the input date
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val parsedDate = LocalDate.parse(fecha, formatter)

        // Calculate the time span
        val dateTimeOfInputDate = parsedDate.atStartOfDay()
        val duration = java.time.Duration.between(dateTimeOfInputDate, currentDateTime)

        // Extract components from duration
        val daysAgo = duration.toDaysPart()
        val hoursAgo = duration.toHoursPart()
        val minutesAgo = duration.toMinutesPart()

        // Output the result
        return buildString {
            if (daysAgo > 0) append("$daysAgo days, ")
            else if (hoursAgo > 0) append("$hoursAgo hours, ")
            else if (minutesAgo > 0) append("$minutesAgo minutes")

            // Remove trailing comma and space
            if (isNotEmpty()) setLength(length - 2)
        }
    }
}

