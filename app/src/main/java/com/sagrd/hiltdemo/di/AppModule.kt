package com.sagrd.hiltdemo.di

import android.content.Context
import androidx.room.Room
import com.sagrd.hiltdemo.data.local.TicketDb
import com.sagrd.hiltdemo.data.remote.AuthInterceptor
import com.sagrd.hiltdemo.data.remote.TicketApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

/*    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(AuthInterceptor("test"))
    }.build()*/

    @Provides
    @Singleton
    fun providesTicketApi(moshi: Moshi): TicketApi {
        return Retrofit.Builder()
            .baseUrl("https://sag-api-dev.azurewebsites.net/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TicketApi::class.java)
    }
  /*  @Provides
    @Singleton
    fun providesTicketApi(moshi: Moshi, client: OkHttpClient): TicketApi {
        return Retrofit.Builder()
            .baseUrl("https://sag-api.azurewebsites.net/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TicketApi::class.java)
    }*/

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