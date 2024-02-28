package com.sagrd.hiltdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.sagrd.hiltdemo.data.realm.TicketRealm
import com.sagrd.hiltdemo.ui.theme.HiltDemoTheme
import dagger.hilt.android.AndroidEntryPoint
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HiltDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    EjemploTicketRealm()
                }
            }
        }
    }


    @Composable
    fun EjemploTicketRealm() {
        var cliente by remember {
            mutableStateOf("")
        }
        var asunto by remember {
            mutableStateOf("")
        }

        var ticketRealms by remember {
            mutableStateOf(emptyList<TicketRealm>())
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            OutlinedTextField(value = cliente,
                onValueChange = { cliente = it },
                label = { Text(text = "Cliente") })
            OutlinedTextField(value = asunto,
                onValueChange = { asunto = it },
                label = { Text(text = "Asunto") })

            ExtendedFloatingActionButton(text = { Text(text = "Nuevo Ticket") }, icon = {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = "add"
                )
            }, onClick = {
                lifecycleScope.launch {
                    val ticketRealm = TicketRealm().apply {
                        Cliente = cliente
                        Asunto = asunto
                    }
                    HiltDemoApp.realm.write {
                        copyToRealm(ticketRealm)
                    }
                }
                cliente = ""
                asunto = ""
            })
            ExtendedFloatingActionButton(text = { Text(text = "Cargar") }, icon = {
                Icon(
                    imageVector = Icons.Default.Refresh, contentDescription = "refresh"
                )
            }, onClick = {

            })

            LaunchedEffect(Unit) {
                lifecycleScope.launch {
                    HiltDemoApp.realm
                        .query<TicketRealm>()
                        .asFlow()
                        .collect() {
                            ticketRealms = it.list.toList()
                        }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(ticketRealms) { ticket ->
                    Text(text = "cliente ${ticket.Cliente} asunto ${ticket.Asunto}")
                }
            }
        }
    }

}

