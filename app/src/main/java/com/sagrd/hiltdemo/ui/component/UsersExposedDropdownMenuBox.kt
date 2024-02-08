package com.sagrd.hiltdemo.ui.component

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.sagrd.hiltdemo.data.remote.dto.UsuarioDto

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun UsersExposedDropdownMenuBox(
    users: List<UsuarioDto>,
    onUserSelected: (Int) -> Unit,
    modifier: Modifier
) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    var selectedUser: UsuarioDto? by rememberSaveable {
        mutableStateOf(null)
    }
    if (selectedUser==null && users.isNotEmpty()){
        selectedUser = users[0]
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }
    )
    {
        OutlinedTextField(
            value = "${selectedUser?.idUsuario} - ${selectedUser?.nombres}",
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = modifier.menuAnchor()
        )
        ExposedDropdownMenu(expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            users.forEach { user ->
                DropdownMenuItem(
                    text={
                        Text(text = "${user.idUsuario} - ${user.nombres}")
                         },
                    onClick = {
                        isExpanded = false
                        selectedUser = user
                        onUserSelected(user.idUsuario)
                    }
                )
            }
        }
    }
}
