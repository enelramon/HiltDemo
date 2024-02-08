package com.sagrd.hiltdemo.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.sagrd.hiltdemo.data.remote.TicketApi
import com.sagrd.hiltdemo.data.remote.dto.UsuarioDto
import com.sagrd.hiltdemo.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UsuarioRepository @Inject constructor(
    private val ticketApi: TicketApi
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getUsuarios(): Flow<Resource<List<UsuarioDto>>> = flow {
        try {
            emit(Resource.Loading())

            val usuarios = ticketApi.getUsuarios()

            emit(Resource.Success(usuarios))

        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}