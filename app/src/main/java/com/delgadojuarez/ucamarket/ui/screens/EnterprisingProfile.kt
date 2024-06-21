package com.delgadojuarez.ucamarket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.delgadojuarez.ucamarket.MainViewModel
import com.delgadojuarez.ucamarket.R
import com.delgadojuarez.ucamarket.ui.component.BottomNavBar
import com.delgadojuarez.ucamarket.ui.component.TopBar
import com.delgadojuarez.ucamarket.ui.navigation.ScreenRoute
import com.delgadojuarez.ucamarket.ui.theme.InriaSans
import com.delgadojuarez.ucamarket.ui.theme.rojo

@Composable
fun EnterprisingProfile(
    viewModel: MainViewModel,
    navController: NavController
){
    var enabled by remember { mutableStateOf(true) }

    Scaffold (
        bottomBar = { BottomNavBar(navController = navController) }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "Perfil", modifier = Modifier
                    .padding(start = 40.dp, end = 10.dp)
                    .size(30.dp))
                Text(
                    text = "Perfil",
                    fontFamily = InriaSans,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(painter = painterResource(id = R.drawable.profile_img), contentDescription = "Imagen perfil",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(bottom = 12.dp))
                Text(text = "Alexander Ainsley",
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Divider(modifier = Modifier.padding(horizontal = 40.dp))

            }

            Spacer(modifier = Modifier.height(30.dp))

            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                Icon(imageVector = Icons.Outlined.Person, contentDescription = "Perfil", modifier = Modifier
                    .padding(start = 40.dp, end = 10.dp)
                    .clickable { navController.navigate(ScreenRoute.EditProfile.route) })
                ClickableText(
                    text = AnnotatedString("Editar perfil"),
                    style = TextStyle(
                        fontFamily = InriaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    onClick = { offset ->
                        if (enabled) {
                            enabled = false
                            navController.navigate(ScreenRoute.EditProfile.route)
                        }
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "Ir", modifier = Modifier
                    .padding(end = 30.dp)
                    .clickable { navController.navigate(ScreenRoute.EditProfile.route) })
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painterResource(id = R.drawable.outline_shopping_bag_24), contentDescription = "Bag", modifier = Modifier
                        .padding(start = 40.dp, end = 10.dp)
                        .clickable { navController.navigate(ScreenRoute.listProducts.route) })
                ClickableText(
                    text = AnnotatedString("Mis productos"),
                    style = TextStyle(
                        fontFamily = InriaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    onClick = { offset ->
                        if (enabled) {
                            enabled = false
                            navController.navigate(ScreenRoute.listProducts.route)
                        }
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "Ir", modifier = Modifier
                    .padding(end = 30.dp)
                    .clickable { navController.navigate(ScreenRoute.listProducts.route) })
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                Icon(imageVector = Icons.Outlined.ExitToApp, contentDescription = "Salir", tint = rojo,
                    modifier = Modifier
                        .padding(start = 40.dp, end = 10.dp))
                Text(
                    text = "Cerrar sesión",
                    fontFamily = InriaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = rojo
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "Ir", modifier = Modifier
                    .padding(end = 30.dp))
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun EnterprisingProfilePreview() {
    val fakeViewModel = MainViewModel()
    val fakeNavController = rememberNavController()

    EnterprisingProfile(
        viewModel = fakeViewModel,
        navController = fakeNavController
    )
}