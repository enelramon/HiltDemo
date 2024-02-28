package com.sagrd.hiltdemo.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.sagrd.hiltdemo.data.local.entities.Ticket
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {
    @Upsert()
    suspend fun save(ticket: Ticket)

    @Query(
        """
        SELECT * 
        FROM Tickets 
        WHERE ticketId=:id  
        LIMIT 1
        """
    )
    suspend fun find(id: Int): Ticket?

    @Delete
    suspend fun delete(ticket: Ticket)

    @Query("SELECT * FROM Tickets")
    fun getAll(): Flow<List<Ticket>>
}




















