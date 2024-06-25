package com.delgadojuarez.ucamarket.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.delgadojuarez.ucamarket.MainViewModel
import com.delgadojuarez.ucamarket.UiState
import com.delgadojuarez.ucamarket.data.remote.model.ApiUser
import com.delgadojuarez.ucamarket.ui.component.AppButton
import com.delgadojuarez.ucamarket.ui.component.CustomOutlinedTextField
import com.delgadojuarez.ucamarket.ui.component.TopBar
import com.delgadojuarez.ucamarket.ui.theme.grisTextFields

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(
    viewModel: MainViewModel,
    navController: NavController
){
    var nombreUsuario by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var telefono by remember {
        mutableStateOf("")
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopBar(
                title = "Editar perfil",
                navController = navController
            )
        },
        bottomBar = {
            AppButton(
                text = "Guardar",
                onClick = {
                    // Accion al dar click
                    // Crea un objeto ApiUser con los datos ingresados
                    val apiUser = ApiUser(
                        name = nombreUsuario,
                        correo = email,
                        whatsapp = telefono
                    )
                    // Llama a la función updateUser del viewModel
                    viewModel.updateUser(apiUser)
                }
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                CustomOutlinedTextField(
                    value = nombreUsuario,
                    onValueChange = { nombreUsuario = it },
                    label = "Nombre de producto"
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Correo electrónico") },
                    trailingIcon = { Icon(imageVector = Icons.Filled.MailOutline, contentDescription = "Email") },
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
                    trailingIcon = { Icon(imageVector = Icons.Filled.Phone, contentDescription = "Telefono") },
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

                when (uiState) {
                    is UiState.Loading -> CircularProgressIndicator()
                    is UiState.Success -> {
                        Text(text = (uiState as UiState.Success).msg)
                    }
                    is UiState.Error -> {
                        Text(text = (uiState as UiState.Error).msg, color = Color.Red)
                    }

                    UiState.Ready -> TODO()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfilePreview() {
    // Aquí debes crear instancias ficticias de tus dependencias, como el viewModel y navController
    // Para el preview, puedes pasar instancias vacías o mocks.
    val fakeViewModel = MainViewModel()
    val fakeNavController = rememberNavController()

    EditProfile(
        viewModel = fakeViewModel,
        navController = fakeNavController
    )
}