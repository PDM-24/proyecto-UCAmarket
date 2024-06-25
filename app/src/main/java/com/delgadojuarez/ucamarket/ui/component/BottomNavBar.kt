package com.delgadojuarez.ucamarket.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.delgadojuarez.ucamarket.MainViewModel
import com.delgadojuarez.ucamarket.ui.navigation.ScreenRoute
import com.delgadojuarez.ucamarket.ui.screens.AddProduct
import com.delgadojuarez.ucamarket.ui.theme.InriaSans
import com.delgadojuarez.ucamarket.ui.theme.grisTexto

@Composable
fun BottomNavBar(
    navController: NavController
){
    // Calculando ruta actual
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            if(currentRoute == ScreenRoute.Home.route){
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    modifier = Modifier
                        .padding(start = 20.dp, end = 8.dp)
                        .clickable {
                            navController.navigate(ScreenRoute.Home.route)
                        },
                    tint = Color.Black
                )
                Text(
                    text = "Página principal",
                    color = Color.Black,
                    fontFamily = InriaSans,
                    fontWeight = FontWeight.Bold
                )
            }
            if(currentRoute == ScreenRoute.clientProfile.route || currentRoute == ScreenRoute.enterprisingProfile.route){
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "Home",
                    modifier = Modifier
                        .padding(start = 20.dp, end = 8.dp)
                        .clickable {
                            navController.navigate(ScreenRoute.Home.route)
                        },
                    tint = Color.Gray
                )
                Text(
                    text = "Página principal",
                    color = grisTexto
                )
            }
        }
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
        ){

            if(currentRoute == ScreenRoute.Home.route){
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .padding(start = 20.dp, end = 8.dp)
                        .clickable {
                            navController.navigate(ScreenRoute.clientProfile.route)
                        },
                    tint = Color.Gray
                )
                Text(
                    text = "Perfil",
                    color = grisTexto,
                    fontFamily = InriaSans,
                    modifier = Modifier.padding(start = 11.dp)
                )
            }
            if(currentRoute == ScreenRoute.clientProfile.route || currentRoute == ScreenRoute.enterprisingProfile.route){
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .padding(start = 20.dp, end = 8.dp)
                        .clickable {
                            navController.navigate(ScreenRoute.clientProfile.route)
                        },
                    tint = Color.Black
                )
                Text(
                    text = "Perfil",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontFamily = InriaSans,
                    modifier = Modifier.padding(start = 11.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfiePreview() {
    // Aquí debes crear instancias ficticias de tus dependencias, como el viewModel y navController
    // Para el preview, puedes pasar instancias vacías o mocks.
    val fakeViewModel = MainViewModel()
    val fakeNavController = rememberNavController()

    BottomNavBar(

        navController = fakeNavController
    )
}