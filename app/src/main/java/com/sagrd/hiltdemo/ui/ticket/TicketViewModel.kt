package com.sagrd.hiltdemo.ui.ticket

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrd.hiltdemo.data.remote.dto.TicketResponse
import com.sagrd.hiltdemo.data.remote.dto.UsuarioDto
import com.sagrd.hiltdemo.data.repository.TicketRepository
import com.sagrd.hiltdemo.data.repository.UsuarioRepository
import com.sagrd.hiltdemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository,
    private val usuarioRepository: UsuarioRepository
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
            ticketRepository
                .getTickets(selectedUser.toIntOrNull()).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                tickets = result.data,
                                isLoading = false)
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message,
                                tickets = emptyList(),
                                isLoading = false
                            )
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    private fun getUsuarios() {
        viewModelScope.launch {
            usuarioRepository.getUsuarios().collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                usuarios = result.data,
                                isLoading = false)
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message,
                                tickets = emptyList(),
                                isLoading = false
                            )
                        }
                    }

                }
            }
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
    val tickets: List<TicketResponse>? = emptyList(),
    val usuarios: List<UsuarioDto>? = emptyList(),
    val error: String? = null,
    val selectedUser: String? = "",
    val selectedUserId: Int? = 0
)