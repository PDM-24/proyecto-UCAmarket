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
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.delgadojuarez.ucamarket.MainViewModel
import com.delgadojuarez.ucamarket.R
import com.delgadojuarez.ucamarket.ui.component.AppButton
import com.delgadojuarez.ucamarket.ui.component.CustomOutlinedTextField
import com.delgadojuarez.ucamarket.ui.component.TopBar
import com.delgadojuarez.ucamarket.ui.theme.grisTextFields

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProduct(
    viewModel: MainViewModel,
    navController: NavController
){
    var nombreProducto by remember {
        mutableStateOf("")
    }
    var foto by remember {
        mutableStateOf("")
    }
    var precio by remember {
        mutableStateOf("")
    }
    var descripcion by remember {
        mutableStateOf("")
    }

    // Variables para Dropdown Menu
    var expanded by remember { mutableStateOf(false) }
    val categoria = listOf(
        "Ropa y accesorios",
        "Tecnología",
        "Comida",
        "Materiales de estudio",
        "Arte y artesanía",
        "Servicios",
        "Otro"
    )
    var selectedIndex by remember { mutableStateOf(categoria[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        TopBar(
            title = "Agregar producto",
            navController = navController
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomOutlinedTextField(
                value = nombreProducto,
                onValueChange = { nombreProducto = it },
                label = "Nombre de producto"
            )

            Spacer(modifier = Modifier.height(27.dp))

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
                    label = { Text(text = "Categoría de producto") },
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
                    categoria.forEachIndexed { index, Categoria ->
                        DropdownMenuItem(
                            text = { Text(text = Categoria) },
                            onClick = {
                                selectedIndex = categoria[index]
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text(text = "Precio") },
                trailingIcon = { Icon(painter = painterResource(id = R.drawable.baseline_attach_money_24), contentDescription = "Precio")},
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

            CustomOutlinedTextField(
                value = foto,
                onValueChange = { foto = it },
                label = "Fotografía"
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text(text = "Descripcion") },
                singleLine = false,
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
                    .height(140.dp)
            )

            Spacer(modifier = Modifier.height(128.dp))
            Spacer(modifier = Modifier.height(60.dp))

            AppButton(
                text = "Actualizar",
                onClick = {
                    // Accion al dar click
                }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProductPreview() {
    // Aquí debes crear instancias ficticias de tus dependencias, como el viewModel y navController
    // Para el preview, puedes pasar instancias vacías o mocks.
    val fakeViewModel = MainViewModel()
    val fakeNavController = rememberNavController()

    EditProduct(
        viewModel = fakeViewModel,
        navController = fakeNavController
    )
}