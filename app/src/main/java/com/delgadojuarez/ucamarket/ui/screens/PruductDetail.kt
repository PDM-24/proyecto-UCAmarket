package com.delgadojuarez.ucamarket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.delgadojuarez.ucamarket.MainViewModel
import com.delgadojuarez.ucamarket.R
import com.delgadojuarez.ucamarket.ui.component.TopBar
import com.delgadojuarez.ucamarket.ui.theme.InriaSans

@Composable
fun ProductDetail(
    viewModel: MainViewModel,
    navController: NavController
){
    Scaffold (
        topBar = {
            TopBar(
                title = "",
                navController = navController
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(Color.White)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 35.dp, end = 20.dp)
                        //.weight(1f)
                    ) {
                        Text(
                            text = "Precio",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                        Spacer(modifier = Modifier.height(1.dp))
                        Text(
                            text = "$7.99",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 33.sp
                        )
                    }

                    Button(
                        onClick = {

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            //.weight(1f)
                            .padding(start = 16.dp, end = 30.dp)
                            .height(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        elevation = ButtonDefaults.buttonElevation(5.dp)
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.whatsapp),
                            contentDescription = "Whatsapp icon",
                            modifier = Modifier
                                .size(24.dp)
                                .background(color = Color.Black)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Contactar", color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    ){innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.camisa),
                    contentDescription = "Imagen de producto",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp)
                )
            }
            Text(
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
                text = "Camisa formal",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
                text = "Ropa sv",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 17.sp,
                color = Color.Gray,
                fontFamily = InriaSans
            )
            Divider(modifier = Modifier.padding(horizontal = 30.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
                text = "Descripción",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(top = 10.dp, bottom = 30.dp),
                text = "Lorem ipsum dolor sit amet, consectetur adispiscing elit, sed do eiusmod tempor incididunt ut labore",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color.Gray,
                fontFamily = InriaSans
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Preview
@Composable
fun DetailProductPreview() {
    val fakeViewModel = MainViewModel()
    val fakeNavController = rememberNavController()

    ProductDetail(
        viewModel = fakeViewModel,
        navController = fakeNavController
    )
}