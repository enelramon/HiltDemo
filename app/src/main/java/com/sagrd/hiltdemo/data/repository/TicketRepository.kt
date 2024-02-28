package com.sagrd.hiltdemo.data.repository


import com.sagrd.hiltdemo.data.local.dao.TicketDao
import com.sagrd.hiltdemo.data.local.entities.Ticket
import javax.inject.Inject

class TicketRepository @Inject constructor(
    private val ticketDao: TicketDao,
) {

    suspend fun save(ticket: Ticket) = ticketDao.save(ticket)

    suspend fun find(id: Int) = ticketDao.find(id)

    suspend fun delete(ticket: Ticket) = ticketDao.delete(ticket)

    fun getAll() = ticketDao.getAll()
}

