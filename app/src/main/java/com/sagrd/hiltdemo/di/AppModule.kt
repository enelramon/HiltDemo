package com.sagrd.hiltdemo.di

import android.content.Context
import androidx.room.Room
import com.sagrd.hiltdemo.data.local.TicketDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTicketDatabase(@ApplicationContext appContext: Context): TicketDb =
        Room.databaseBuilder(
            appContext,
            TicketDb::class.java,
            "Ticket.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesTicketDao(db: TicketDb) = db.ticketDao()

}