package com.sagrd.hiltdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.sagrd.hiltdemo.data.remote.dto.TicketDto
import com.sagrd.hiltdemo.ui.theme.HiltDemoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {

        }

        setContent {
            HiltDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val tickets = emptyList<TicketDto>()
                    TicketList(tickets)
                }
            }
        }
    }


}


@Composable
private fun TicketList( tickets: List<TicketDto>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(tickets){ ticket->
            Text(text = ticket.asunto)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    HiltDemoTheme {
        val tickets = listOf(
            TicketDto(1,1,"enel","necesito ayuda"),
            TicketDto(2,1,"alexis","impresora mala"),
            TicketDto(22,2,"joseph","sistema no abre"),
            TicketDto(33,4,"robert","cable de red malo"),
            TicketDto(4,1,"russo","fallo de red"),
        )
        TicketList(tickets = tickets)

    }
}