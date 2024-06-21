package com.delgadojuarez.ucamarket.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.delgadojuarez.ucamarket.MainViewModel
import com.delgadojuarez.ucamarket.ui.component.TopBar

@Composable
fun ProductDetail(
    viewModel: MainViewModel,
    navController: NavController
){
    Scaffold (
        topBar = {
            TopBar(
                title = "Editar producto",
                navController = navController
            )
        }
    ){innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ){
            Text(text = "Pantalla de detalle de producto")
        }
    }

}