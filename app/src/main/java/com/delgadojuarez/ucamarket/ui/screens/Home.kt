package com.delgadojuarez.ucamarket.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delgadojuarez.ucamarket.MainViewModel
import com.delgadojuarez.ucamarket.ui.component.BottomNavBar

@Composable
fun Home(
    viewModel: MainViewModel,
    navController: NavController
){
    Column {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.height(35.dp).padding(start = 10.dp)
        ) {
            //Text(text = "Eliminar", color = Color.White, fontSize = 9.sp)
            Icon(
                //modifier = Modifier.padding(end = 20.dp),
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "Favoritos",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
        BottomNavBar(navController = navController)
    }
}