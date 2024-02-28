package com.sagrd.hiltdemo.ui.ticket

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrd.hiltdemo.data.local.entities.Ticket
import com.sagrd.hiltdemo.data.local.entities.Usuario
import com.sagrd.hiltdemo.data.repository.TicketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TicketListState())
    val state = _state.asStateFlow()

    init {
        getTickets()
        getUsuarios()
    }

    private fun getTickets() {
        val selectedUser = _state.value.selectedUserId.toString()

        viewModelScope.launch {

        }
    }

    private fun getUsuarios() {
        viewModelScope.launch {

        }
    }

    fun onUserSelected(userId: Int?) {
        if (userId == null)
            return

        _state.update {
            it.copy(selectedUserId = userId)
        }

        getTickets()
    }

}

data class TicketListState(
    val isLoading: Boolean = false,
    val tickets: List<Ticket>? = emptyList(),
    val usuarios: List<Usuario>? = emptyList(),
    val error: String? = null,
    val selectedUser: String? = "",
    val selectedUserId: Int? = 0
)