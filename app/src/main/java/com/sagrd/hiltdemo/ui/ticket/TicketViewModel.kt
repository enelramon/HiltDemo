package com.sagrd.hiltdemo.ui.ticket

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrd.hiltdemo.data.remote.dto.TicketDto
import com.sagrd.hiltdemo.data.repository.TicketRepository
import com.sagrd.hiltdemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
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
        viewModelScope.launch {
            ticketRepository.getTickets().collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(ticket = result.data, isLoading = false)
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(error = result.message, ticket = emptyList(), isLoading = false)
                        }
                    }
                }
            }
        }
    }

}

data class TicketListState(
    val isLoading: Boolean = false,
    val ticket: List<TicketDto>? = emptyList(),
    val error: String? = ""
)