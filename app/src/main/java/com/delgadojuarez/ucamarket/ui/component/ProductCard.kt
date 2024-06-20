package com.delgadojuarez.ucamarket.ui.component

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.delgadojuarez.ucamarket.ui.navigation.ScreenRoute
import com.delgadojuarez.ucamarket.ui.screens.Wishlist

@Composable
fun ProductCard(
    productName: String,
    entrepreneurshipName: String,
    productPrice: String,
    //imageUrl: String,
    onFavoriteClick: () -> Unit = {},
    navController: NavController
) {
    //Calculando ruta actual
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            //.clickable(navController.navigate(ScreenRoute.EditProduct.route))
    ) {
        Row(
            modifier = Modifier
                //.fillMaxSize()
                .padding(16.dp)
        ) {
            // Imagen a la izquierda
            Image(
                modifier = Modifier
                    .size(105.dp)
                    .clip(shape = RoundedCornerShape(4.dp)),
                painter = painterResource(id = R.drawable.camisa),
                contentDescription = "Camisa")


            // Textos en columna a la derecha
            Column(
                modifier = Modifier
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Row {

                    Column (

                    ){
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = productName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(text = entrepreneurshipName)
                        Text(
                            text = productPrice,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column (

                    ){
                        // Icono de corazón relleno en la esquina superior derecha
                        if(currentRoute == ScreenRoute.wishlist.route){
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier
                                    .padding(start = 60.dp)
                                    //.align(Alignment.End)
                                    .clickable { }
                            )
                        }


                        /*Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                            elevation = ButtonDefaults.buttonElevation(5.dp),
                            modifier = Modifier.height(30.dp).padding(start = 10.dp)
                        ) {
                            //Text(text = "Editar", color = Color.White, fontSize = 9.sp)
                            Icon(
                                //modifier = Modifier.padding(end = 20.dp),
                                imageVector = Icons.Default.Create,
                                contentDescription = "Editar",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }*/

                        if(currentRoute == ScreenRoute.listProducts.route){
                            Spacer(modifier = Modifier.height(15.dp))
                            Spacer(modifier = Modifier.height(32.dp))

                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                                elevation = ButtonDefaults.buttonElevation(5.dp),
                                modifier = Modifier.height(30.dp).padding(start = 10.dp)
                            ) {
                                //Text(text = "Editar", color = Color.White, fontSize = 9.sp)
                                Icon(
                                    //modifier = Modifier.padding(end = 20.dp),
                                    imageVector = Icons.Default.Create,
                                    contentDescription = "Editar",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                elevation = ButtonDefaults.buttonElevation(5.dp),
                                modifier = Modifier.height(35.dp).padding(start = 10.dp)
                            ) {
                                //Text(text = "Eliminar", color = Color.White, fontSize = 9.sp)
                                Icon(
                                    //modifier = Modifier.padding(end = 20.dp),
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Eliminar",
                                    tint = Color.White,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }

                    }

                }
            }
        }
    }
}
