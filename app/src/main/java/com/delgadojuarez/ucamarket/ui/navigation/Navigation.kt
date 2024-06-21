package com.delgadojuarez.ucamarket.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.delgadojuarez.ucamarket.MainViewModel
import com.delgadojuarez.ucamarket.ui.screens.AddProduct
import com.delgadojuarez.ucamarket.ui.screens.ClientProfile
import com.delgadojuarez.ucamarket.ui.screens.EditProduct
import com.delgadojuarez.ucamarket.ui.screens.EditProfile
import com.delgadojuarez.ucamarket.ui.screens.EnterprisingProfile
import com.delgadojuarez.ucamarket.ui.screens.Home
import com.delgadojuarez.ucamarket.ui.screens.ListProducts
import com.delgadojuarez.ucamarket.ui.screens.LoginScreen
import com.delgadojuarez.ucamarket.ui.screens.ProductDetail
import com.delgadojuarez.ucamarket.ui.screens.SignupScreen
import com.delgadojuarez.ucamarket.ui.screens.Wishlist

@Composable
fun Navigation(
    viewModel: MainViewModel
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        composable(route = ScreenRoute.Signin.route){
            LoginScreen(viewModel, navController)
        }
        composable(route = ScreenRoute.Signup.route){
            SignupScreen(viewModel, navController)
        }
        composable(route = ScreenRoute.Home.route){
            Home(viewModel, navController)
        }
        composable(route = ScreenRoute.ProductDetail.route){
            ProductDetail(viewModel, navController)
        }
        composable(route = ScreenRoute.Add.route){
            AddProduct(viewModel, navController)
        }
        composable(route = ScreenRoute.EditProduct.route){
            EditProduct(viewModel, navController)
        }
        composable(route = ScreenRoute.EditProfile.route){
            EditProfile(viewModel, navController)
        }
        composable(route = ScreenRoute.clientProfile.route){
            ClientProfile(viewModel, navController)
        }
        composable(route = ScreenRoute.enterprisingProfile.route){
            EnterprisingProfile(viewModel, navController)
        }
        composable(route = ScreenRoute.wishlist.route){
            Wishlist(viewModel, navController)
        }
        composable(route = ScreenRoute.listProducts.route){
            ListProducts(viewModel, navController)
        }
    }
}