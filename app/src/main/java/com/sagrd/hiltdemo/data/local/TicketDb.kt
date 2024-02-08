package com.sagrd.hiltdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sagrd.hiltdemo.data.local.dao.TicketDao
import com.sagrd.hiltdemo.data.local.entities.Ticket

@Database(
    entities = [Ticket::class ],
    version = 4,
    exportSchema = false
)
abstract class TicketDb : RoomDatabase() {
    abstract fun ticketDao(): TicketDao
}