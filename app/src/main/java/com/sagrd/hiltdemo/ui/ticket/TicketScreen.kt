package com.sagrd.hiltdemo.ui.ticket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sagrd.hiltdemo.data.local.entities.Ticket

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TicketScreen(
    ticket: Ticket
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

                Text(text = "Ticket #: ${ticket.ticketId}")
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
                //Text(text = "${ticket.getPeriod()} ago", color = MaterialTheme.colorScheme.error)
            }

            Text(text = "Asunto: ${ticket.asunto}")
        }
    }
    Spacer(modifier = Modifier.padding(2.dp))
}