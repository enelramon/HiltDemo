package com.sagrd.hiltdemo.data.remote.dto

import com.squareup.moshi.Json

data class TicketDto(
    val idTicket: Int,
    val idCliente: Int,
    @Json(name = "solicitadoPor")
    val solicitado: String,
    val asunto: String
)
