package com.sagrd.hiltdemo

import android.app.Application
import com.sagrd.hiltdemo.data.realm.TicketRealm
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import timber.log.Timber

@HiltAndroidApp
class HiltDemoApp : Application() {

    companion object{
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(TicketRealm::class)
            )
        )

        Timber.plant(Timber.DebugTree())
    }


}