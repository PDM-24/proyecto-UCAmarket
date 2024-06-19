package com.delgadojuarez.ucamarket.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.delgadojuarez.ucamarket.MainViewModel
import com.delgadojuarez.ucamarket.ui.component.TopBar
import com.delgadojuarez.ucamarket.ui.theme.InriaSans
import com.delgadojuarez.ucamarket.ui.theme.azul
import com.delgadojuarez.ucamarket.ui.theme.grisTextFields

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    viewModel: MainViewModel,
    navController: NavController
){
    var nombre by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var telefono by remember {
        mutableStateOf("")
    }
    var tipoUsuario by remember {
        mutableStateOf("")
    }
    var nombreEmprendimiento by remember {
        mutableStateOf("")
    }
    var foto by remember {
        mutableStateOf("")
    }

    // Variables para Dropdown Menu
    var expanded by remember { mutableStateOf(false) }
    val usuarios = listOf(
        "Cliente",
        "Emprendedor"
    )
    var selectedIndex by remember { mutableStateOf(usuarios[0]) }

    val isReadOnly by derivedStateOf { selectedIndex != "Emprendedor" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        TopBar(
            title = "Registro",
            navController = navController
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text(text = "Nombre completo") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = grisTextFields,
                    cursorColor = Color.Black,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Correo electrónico") },
                trailingIcon = { Icon(imageVector = Icons.Filled.MailOutline, contentDescription = "Email")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = grisTextFields,
                    cursorColor = Color.Black,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text(text = "Número de teléfono") },
                trailingIcon = { Icon(imageVector = Icons.Filled.Phone, contentDescription = "Telefono")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = grisTextFields,
                    cursorColor = Color.Black,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )

            //Spacer(modifier = Modifier.height(20.dp))

            /*OutlinedTextField(
                value = tipoUsuario,
                onValueChange = { tipoUsuario = it },
                label = { Text(text = "Tipo de usuario *") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = grisTextFields,
                    cursorColor = Color.Black,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),oundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )*/

            Spacer(modifier = Modifier.height(20.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    value = selectedIndex,
                    onValueChange = {},
                    label = { Text(text = "Tipo de usuario *") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = grisTextFields,
                        cursorColor = Color.Black,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(12.dp),
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    usuarios.forEachIndexed { index, usuario ->
                        DropdownMenuItem(
                            text = { Text(text = usuario) },
                            onClick = {
                                selectedIndex = usuarios[index]
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = nombreEmprendimiento,
                onValueChange = { if (!isReadOnly) nombreEmprendimiento = it },
                label = { Text(text = "Nombre del emprendimiento") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = if (isReadOnly) Color.LightGray else grisTextFields, // Cambia el color de fondo si está desactivado
                    cursorColor = if (isReadOnly) Color.Transparent else Color.Black, // Oculta el cursor si está desactivado
                    focusedBorderColor = if (isReadOnly) Color.Transparent else Color.Black, // Cambia el color del borde si está desactivado
                    unfocusedBorderColor = if (isReadOnly) Color.Transparent else grisTextFields // Cambia el color del borde si está desactivado
                ),
                shape = RoundedCornerShape(12.dp),
                readOnly = isReadOnly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = foto,
                onValueChange = { foto = it },
                label = { Text(text = "Fotografía") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = grisTextFields,
                    cursorColor = Color.Black,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )

            Spacer(modifier = Modifier.height(128.dp))
            Spacer(modifier = Modifier.height(60.dp))

            Button(onClick = {
                Log.i("Credential","Email : $email Nombre : $nombre")
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = azul),
                elevation = ButtonDefaults.buttonElevation(5.dp)
            ) {
                Text(text = "Guardar")
            }
        }
    }
}

