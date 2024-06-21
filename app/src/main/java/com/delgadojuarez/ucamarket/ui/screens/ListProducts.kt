package com.delgadojuarez.ucamarket.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
    Column {
        Spacer(modifier = Modifier.height(40.dp))

        TopBar(title = "Mis productos", navController = navController)

        Spacer(modifier = Modifier.height(30.dp))

        ProductCard(productName = "Camisa elegante", entrepreneurshipName = "Ropa sv", productPrice = "$7.99", navController = navController)

        Spacer(modifier = Modifier.height(128.dp))
        Spacer(modifier = Modifier.height(128.dp))
        Spacer(modifier = Modifier.height(128.dp))
        Spacer(modifier = Modifier.height(128.dp))

        AppButton(
            text = "Agregar producto",
            onClick = {
                navController.navigate(ScreenRoute.Add.route)
            }
        )
    }
}