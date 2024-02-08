package com.sagrd.hiltdemo.data.repository

import com.sagrd.hiltdemo.data.remote.TicketApi
import com.sagrd.hiltdemo.data.remote.dto.TicketResponse
import com.sagrd.hiltdemo.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TicketRepository @Inject constructor(
    private val ticketApi: TicketApi
) {
    fun getTickets(selectedUser: Int?): Flow<Resource<List<TicketResponse>>> = flow {
        try {
            emit(Resource.Loading())

            val tickets = ticketApi.getTickets(selectedUser)

            delay(3000L)
            emit(Resource.Success(tickets))

        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
        catch (e: Exception) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    fun getTicketById(ticketId: Int): Flow<Resource<TicketResponse>> = flow {
        try {
            emit(Resource.Loading())

            val tickets = ticketApi.getTicketById(ticketId)

            delay(3000L)
            emit(Resource.Success(tickets))

        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
        catch (e: Exception) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

}
