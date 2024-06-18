package com.delgadojuarez.ucamarket.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.delgadojuarez.ucamarket.MainViewModel
import com.delgadojuarez.ucamarket.ui.screens.Home
import com.delgadojuarez.ucamarket.ui.screens.LoginScreen
import com.delgadojuarez.ucamarket.ui.screens.ProductDetail
import com.delgadojuarez.ucamarket.ui.screens.SignupScreen

@Composable
fun Navigation(
    viewModel: MainViewModel
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoute.Signin.route) {
        composable(route = ScreenRoute.Signin.route){
            LoginScreen(viewModel, navController)
        }
        composable(route = ScreenRoute.Signup.route){
            SignupScreen(viewModel, navController)
        }
        composable(route = ScreenRoute.Home.route){
            Home()
        }
        composable(route = ScreenRoute.ProductDetail.route){
            ProductDetail()
        }
    }
}