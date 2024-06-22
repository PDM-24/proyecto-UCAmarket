package com.delgadojuarez.ucamarket.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import com.delgadojuarez.ucamarket.R
import com.delgadojuarez.ucamarket.ui.navigation.ScreenRoute
import com.delgadojuarez.ucamarket.ui.theme.grisTexto

@Composable
fun VerticalProductCard(
    productName: String,
    entrepreneurshipName: String,
    productPrice: String,
    navController: NavController
){
    Box(
        modifier = Modifier
            .clickable { navController.navigate(ScreenRoute.productDetail.route) }
            .background(Color.Transparent, shape = RoundedCornerShape(15.dp)) // Redondea el fondo
        .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(15.dp)) // Borde redondeado
        .clip(RoundedCornerShape(15.dp)) // Clip redondeado para evitar desbordamientos
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(130.dp)
                    .padding(start = 17.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clip(shape = RoundedCornerShape(18.dp)),
                painter = painterResource(id = R.drawable.camisa),
                contentDescription = "Imagen de producto"
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = productName,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(text = entrepreneurshipName, color = grisTexto)
            Text(
                text = productPrice,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun productPreview(){
    val fakeNavController = rememberNavController()
    VerticalProductCard(
        productName = "Camisa elegante",
        entrepreneurshipName = "Ropa sv",
        productPrice = "$7.99",
        navController = fakeNavController
    )
}