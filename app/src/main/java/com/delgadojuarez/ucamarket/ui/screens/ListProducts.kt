package com.delgadojuarez.ucamarket.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.delgadojuarez.ucamarket.MainViewModel
import com.delgadojuarez.ucamarket.ui.component.AppButton
import com.delgadojuarez.ucamarket.ui.component.ProductCard
import com.delgadojuarez.ucamarket.ui.component.TopBar
import com.delgadojuarez.ucamarket.ui.navigation.ScreenRoute

@Composable
fun ListProducts(
    viewModel: MainViewModel,
    navController: NavController
){
    Scaffold(
        topBar = {
            TopBar(title = "Mis productos", navController = navController)
        },
        bottomBar = {
            AppButton(
                text = "Agregar producto",
                onClick = {
                    navController.navigate(ScreenRoute.Add.route)
                }
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            ProductCard(productName = "Camisa elegante", entrepreneurshipName = "Ropa sv", productPrice = "$7.99", navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListProductPreview() {
    val fakeViewModel = MainViewModel()
    val fakeNavController = rememberNavController()

    ListProducts(
        viewModel = fakeViewModel,
        navController = fakeNavController
    )
}