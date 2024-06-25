package com.delgadojuarez.ucamarket.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.delgadojuarez.ucamarket.ui.navigation.ScreenRoute
import com.delgadojuarez.ucamarket.ui.theme.InriaSans

@Composable
fun TopBar(
    title : String = "",
    navController: NavController
){

    // Calculando ruta actual
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    var isFavorite by remember { mutableStateOf(false) }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, bottom = 10.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Regresar",
            modifier = Modifier
                .padding(start = 30.dp, end = 8.dp)
                .clickable {
                    navController.popBackStack()
                },
            tint = Color.Black
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = InriaSans
        )
        
        Spacer(modifier = Modifier.weight(1f))

        if (currentRoute == ScreenRoute.productDetail.route) {
            IconButton(
                modifier = Modifier.wrapContentSize(),
                onClick = { isFavorite = !isFavorite }
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorito",
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(40.dp) // Ajusta el tamaño aquí
                )
            }
        }
    }
}

@Preview
@Composable
fun topbarPreview(){
    val fakeNavController = rememberNavController()
    TopBar(title = "Ejemplo", navController = fakeNavController)
}