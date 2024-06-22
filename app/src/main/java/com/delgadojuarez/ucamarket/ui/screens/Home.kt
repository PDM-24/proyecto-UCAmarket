package com.delgadojuarez.ucamarket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.delgadojuarez.ucamarket.MainViewModel
import com.delgadojuarez.ucamarket.R
import com.delgadojuarez.ucamarket.ui.component.BottomNavBar
import com.delgadojuarez.ucamarket.ui.component.VerticalProductCard
import com.delgadojuarez.ucamarket.ui.navigation.ScreenRoute
import com.delgadojuarez.ucamarket.ui.theme.grisTextFields
import com.delgadojuarez.ucamarket.ui.theme.grisTexto
import com.delgadojuarez.ucamarket.ui.theme.gris_card
import com.delgadojuarez.ucamarket.ui.theme.gris_lupa
val categories = listOf(
    "Ropa y accesorios",
    "Tecnología",
    "Comida",
    "Materiales de estudio",
    "Arte y artesanía",
    "Servicios",
    "Otro"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    viewModel: MainViewModel,
    navController: NavController
){
    Scaffold(
        topBar = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 35.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(painter = painterResource(id = R.drawable.profile_img), contentDescription = "Foto de perfil",
                      modifier = Modifier.size(47.dp)
                )
                Column (
                    modifier = Modifier.padding(start = 15.dp)
                ){
                    Text(text = "¡Buen día! \uD83D\uDC4B",
                        color = grisTexto,
                        fontSize = 16.sp
                    )
                    Text(text = "Alexander Ainsley",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favoritos",
                    tint = Color.Gray,
                    modifier = Modifier.clickable{navController.navigate(ScreenRoute.wishlist.route)}
                )
            }
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Barra de búsqueda
            Surface (
                color = grisTextFields,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth()
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Icon(
                        modifier = Modifier.padding(start = 20.dp),
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = "Buscar",
                        tint = gris_lupa
                    )


                    TextField(
                        value = "",
                        onValueChange = {
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = grisTextFields,
                            cursorColor = Color.Black,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),

                        placeholder = {
                            Text(
                                text = "Buscar . . .",
                                style = TextStyle(
                                    color = gris_lupa,
                                    fontSize = 15.sp,
                                    letterSpacing = 0.1.em,
                                    fontWeight = FontWeight.Normal,
                                ),
                            )
                        },
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                Text(text = "Ofertas especiales",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 25.dp)
                        .align(alignment = Alignment.Start)
                )

                // Tarjeta de ofertas
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .padding(horizontal = 25.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row {
                        Column (
                            modifier = Modifier
                                .padding(18.dp)
                                .weight(1f),
                            horizontalAlignment = Alignment.Start
                        ){
                            Text(text = "10%", fontWeight = FontWeight.ExtraBold, fontSize = 50.sp)
                            Text(text = "¡De descuento!", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "Al presentar tu carnét de estudiante (de cualquier universidad)",
                                fontWeight = FontWeight.Bold, fontSize = 12.sp, color = grisTexto
                            )
                        }

                        Image(
                            painter = painterResource(id = R.drawable.cupon_img),
                            contentDescription ="Cupon",
                            modifier = Modifier
                                .weight(1f)
                                .size(140.dp)
                                .align(alignment = Alignment.CenterVertically)
                        )
                    }

                }

                // Botones de categorias
                Spacer(modifier = Modifier.height(5.dp))
                CategoryButtons()

                // Lista de productos disponibles
                Spacer(modifier = Modifier.height(10.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // Dos tarjetas por fila
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                ) {
                    items(4) { index -> // Cambia el número de tarjetas según tus necesidades
                        VerticalProductCard(
                            productName = "Camisa elegante",
                            entrepreneurshipName = "Ropa sv",
                            productPrice = "$7.99",
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryButtons() {
    var selectedCategory by remember { mutableStateOf(categories.first()) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
    ) {

        items(categories) { category ->
            Button(
                onClick = { selectedCategory = category },
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(25.dp)
                    )
                    .height(40.dp)
                    .selectable(
                        selected = category == selectedCategory,
                        onClick = { selectedCategory = category }
                    ),
                colors = ButtonDefaults.buttonColors(if (category == selectedCategory) Color.Black else Color.Transparent)
            ) {
                Text(
                    text = category,
                    color = if (category == selectedCategory) Color.White else Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun HomePreview() {
    // Aquí debes crear instancias ficticias de tus dependencias, como el viewModel y navController
    // Para el preview, puedes pasar instancias vacías o mocks.
    val fakeViewModel = MainViewModel()
    val fakeNavController = rememberNavController()

    Home(
        viewModel = fakeViewModel,
        navController = fakeNavController
    )
}