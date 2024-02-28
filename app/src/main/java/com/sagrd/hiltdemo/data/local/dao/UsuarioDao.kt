package com.sagrd.hiltdemo.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.sagrd.hiltdemo.data.local.entities.Usuario
import kotlinx.coroutines.flow.Flow

//create usuario dao
@Dao
interface UsuarioDao {
    @Upsert()
    suspend fun save(usuario: Usuario)

    @Query(
        """
        SELECT * 
        FROM Usuarios 
        WHERE usuarioId=:id  
        LIMIT 1
        """
    )
    suspend fun find(id: Int): Usuario?

    @Delete
    suspend fun delete(usuario: Usuario)

    @Query("SELECT * FROM Usuarios")
    fun getAll(): Flow<List<Usuario>>
}