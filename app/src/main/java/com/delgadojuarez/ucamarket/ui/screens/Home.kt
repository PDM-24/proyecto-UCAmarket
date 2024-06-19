package com.delgadojuarez.ucamarket.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.delgadojuarez.ucamarket.MainViewModel
import com.delgadojuarez.ucamarket.ui.component.BottomNavBar

@Composable
fun Home(
    viewModel: MainViewModel,
    navController: NavController
){
    Column {
        Text(text = "Pantalla principal")
        BottomNavBar(navController = navController)
    }
}