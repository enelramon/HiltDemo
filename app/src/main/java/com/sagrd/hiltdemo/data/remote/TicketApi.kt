package com.sagrd.hiltdemo.data.remote

import com.sagrd.hiltdemo.data.remote.dto.TicketDto
import retrofit2.http.GET

interface TicketApi {
    @GET("/api/Tickets")
    suspend fun getTickets():List<TicketDto>
}