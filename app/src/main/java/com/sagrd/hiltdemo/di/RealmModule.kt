package com.sagrd.hiltdemo.di

import com.sagrd.hiltdemo.data.realm.TicketRealDao
import com.sagrd.hiltdemo.data.realm.TicketRealm
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RealmModule {

    @Provides
    @Singleton
    fun provideRealm(): Realm {
        val realmConfig = RealmConfiguration.create(
            schema = setOf(
                TicketRealm::class,
            ),
        )
        return Realm.open(realmConfig)
    }

    @Provides
    @Singleton
    fun provideTicketRealDao(realm: Realm) = TicketRealDao(realm)
}