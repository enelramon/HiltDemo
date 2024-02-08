package com.sagrd.hiltdemo.ui.ticket

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sagrd.hiltdemo.data.remote.dto.TicketResponse
import com.sagrd.hiltdemo.data.remote.dto.UsuarioDto
import com.sagrd.hiltdemo.ui.component.UsersExposedDropdownMenuBox
import com.sagrd.hiltdemo.ui.theme.HiltDemoTheme


@OptIn(ExperimentalMaterial3Api::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun TicketListScreen(viewModel: TicketViewModel = hiltViewModel()) {
    val uiState: TicketListState by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Tickets Pendientes") },
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "add"
                        )
                    }
                })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            uiState.error?.let {
                Text(
                    text = "ocurrio un error${it}",
                    color = MaterialTheme.colorScheme.error
                )
            }

            if (uiState.isLoading)
                LinearProgressIndicator()

            TicketListBody(
                tickets = uiState.tickets!!,
                users = uiState.usuarios!!,
                onUserSelected = viewModel::onUserSelected,
                onVerTicket = {

                }
            )
        }
    }
}

@Composable
private fun TicketListBody(
    tickets: List<TicketResponse>,
    users: List<UsuarioDto> = emptyList(),
    onUserSelected: (Int) -> Unit,
    onVerTicket: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {

        UsersExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            users = users,
            onUserSelected = { id ->
                onUserSelected(id)
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(tickets) { ticket ->
                TicketCard(ticket) {
                    onVerTicket(it)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TicketCard(
    ticket: TicketResponse,
    onVerTicket: (Int) -> Unit
) {
    //eleveated card with rounded corners and
    ElevatedCard(
        onClick = { onVerTicket(ticket.idTicket) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = "Ticket #: ${ticket.idTicket}")
                Text(text = "Fecha: ${ticket.fecha.take(10)}")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Empresa: ${ticket.empresa}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = "${ticket.getPeriod()} ago", color = MaterialTheme.colorScheme.error)
            }

            Text(text = "Asunto: ${ticket.asunto}")
        }
    }
    Spacer(modifier = Modifier.padding(2.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TicketScreen(
    ticket: TicketResponse
) {
    //eleveated card with rounded corners and
    ElevatedCard(
        onClick = {  },
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = "Ticket #: ${ticket.idTicket}")
                Text(text = "Fecha: ${ticket.fecha.take(10)}")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Empresa: ${ticket.empresa}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = "${ticket.getPeriod()} ago", color = MaterialTheme.colorScheme.error)
            }

            Text(text = "Asunto: ${ticket.asunto}")
        }
    }
    Spacer(modifier = Modifier.padding(2.dp))
}

@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    HiltDemoTheme {
        val tickets = listOf(
            TicketResponse(1, "2022-01-01", 1, "enel", "Gama", "necesito ayuda"),
            TicketResponse(2, "2022-01-01", 1, "alexis", "Gama", "impresora mala"),
            TicketResponse(22, "2022-01-01", 2, "joseph", "Gama", "sistema no abre"),
            TicketResponse(33, "2022-01-01", 4, "robert", "Gama", "cable de red malo"),
            TicketResponse(4, "2022-01-01", 1, "russo", "Gama", "fallo de red"),
        )
        TicketListBody(
            tickets = tickets,
            onUserSelected = { },
              onVerTicket = {}
        )

    }
}

