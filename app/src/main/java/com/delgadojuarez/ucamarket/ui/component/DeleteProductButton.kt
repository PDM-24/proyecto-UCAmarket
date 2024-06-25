package com.delgadojuarez.ucamarket.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.delgadojuarez.ucamarket.MainViewModel

@Composable
fun DeleteProductButton(viewModel: MainViewModel, productId: String, onDeleteConfirmed: () -> Unit ={}) {
    var showDialog by remember { mutableStateOf(false) }

    Column {
        Button(
            onClick = { showDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            elevation = ButtonDefaults.buttonElevation(5.dp),
            modifier = Modifier.height(30.dp).padding(start = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Eliminar",
                tint = Color.White,
                modifier = Modifier.size(35.dp)
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(
                    text = "Confirmar eliminación",
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 15.dp, top = 5.dp)
                ) },
                text = {
                    Column {
                        Text(
                            text = "El producto seleccionado se eliminará de forma permanente de tu lista de productos",
                            modifier = Modifier.padding(bottom = 10.dp),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "¿Estás seguro de continuar?",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 45.dp, bottom = 2.dp)
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            viewModel.deleteProduct(productId)
                            onDeleteConfirmed()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                    ) {
                        Text(text = "OK", color = Color.White)
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text(text = "CANCELAR", color = Color.White)
                    }
                }
            )
        }
    }
}