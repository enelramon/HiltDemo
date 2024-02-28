package com.sagrd.hiltdemo.data.realm

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

open class TicketRealm() : RealmObject {
    @PrimaryKey
    var ticketId: Int = 0
    var Cliente: String = ""
    var solicitadoPor: String = ""
    var Asunto: String = ""
    var solicitud: String = ""
}

class TicketRealDao @Inject constructor(
    private val realm: Realm,
) {
    suspend fun insert(ticketRealm: TicketRealm) {
        realm.write {
            copyToRealm(ticketRealm)
        }
    }

    fun getAll(): Flow<List<TicketRealm>> {
        return realm
            .query<TicketRealm>()
            .asFlow()
            .map {
                it.list.toList()
            }

    }
}