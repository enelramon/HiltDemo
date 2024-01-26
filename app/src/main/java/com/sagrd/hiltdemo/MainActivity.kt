package com.sagrd.hiltdemo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sagrd.hiltdemo.data.remote.dto.TicketDto
import com.sagrd.hiltdemo.ui.theme.HiltDemoTheme
import com.sagrd.hiltdemo.ui.ticket.TicketListState
import com.sagrd.hiltdemo.ui.ticket.TicketViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HiltDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TicketScreen()
                }
            }
        }
    }


}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun TicketScreen(viewModel: TicketViewModel = hiltViewModel()) {
    val uiState : TicketListState by viewModel.state.collectAsStateWithLifecycle()
    Column(modifier = Modifier.fillMaxSize()) {
        uiState.error?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        if (uiState.isLoading)
            CircularProgressIndicator()

        TicketList(uiState.ticket!!)
    }

}

@Composable
private fun TicketList( tickets: List<TicketDto>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
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