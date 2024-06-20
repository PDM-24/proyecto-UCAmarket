package com.delgadojuarez.ucamarket.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.delgadojuarez.ucamarket.MainViewModel
import com.delgadojuarez.ucamarket.ui.component.ProductCard
import com.delgadojuarez.ucamarket.ui.component.TopBar

@Composable
fun Wishlist(
    viewModel: MainViewModel,
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        TopBar(title = "Lista de favoritos", navController = navController)

        Spacer(modifier = Modifier.height(30.dp))

        ProductCard(productName = "Camisa elegante", entrepreneurshipName = "Ropa sv", productPrice = "$7.99", navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun wishlistPreview() {
    val fakeViewModel = MainViewModel()
    val fakeNavController = rememberNavController()

    Wishlist(
        viewModel = fakeViewModel,
        navController = fakeNavController
    )
}

