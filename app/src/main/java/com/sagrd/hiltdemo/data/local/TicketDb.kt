package com.sagrd.hiltdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sagrd.hiltdemo.data.local.dao.TicketDao
import com.sagrd.hiltdemo.data.local.dao.UsuarioDao
import com.sagrd.hiltdemo.data.local.entities.Ticket
import com.sagrd.hiltdemo.data.local.entities.Usuario

@Database(
    entities = [
        Ticket::class,
        Usuario::class
    ],
    version = 5,
    exportSchema = false
)
abstract class TicketDb : RoomDatabase() {
    abstract fun ticketDao(): TicketDao
    abstract fun userDao(): UsuarioDao
}