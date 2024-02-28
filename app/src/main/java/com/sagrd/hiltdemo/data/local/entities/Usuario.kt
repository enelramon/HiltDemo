package com.sagrd.hiltdemo.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")
data class Usuario(
    @PrimaryKey
    val usuarioId: Int? = null,
    var nombres: String = ""
)