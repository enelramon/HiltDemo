package com.sagrd.hiltdemo.data.repository

import com.sagrd.hiltdemo.data.local.dao.UsuarioDao
import com.sagrd.hiltdemo.data.local.entities.Usuario
import javax.inject.Inject

//create usuario repository
class UsuarioRepository @Inject constructor(
    private val usuarioDao: UsuarioDao,
) {

    suspend fun save(usuario: Usuario) = usuarioDao.save(usuario)

    suspend fun find(id: Int) = usuarioDao.find(id)

    suspend fun delete(usuario: Usuario) = usuarioDao.delete(usuario)

    fun getAll() = usuarioDao.getAll()
}