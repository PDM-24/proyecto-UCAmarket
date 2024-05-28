package np.com.bimalkafle.mylogin

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(){

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.a), contentDescription = "Login image",
            modifier = Modifier.size(200.dp))

        Text(text = "Iniciar sesión", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(text = "Módulo de administración")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = email, onValueChange = {
              email = it
        }, label = {
            Text(text = "Correo electrónico")
        })

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = password, onValueChange = {
                password = it
        }, label = {
            Text(text = "Contraseña")
        }, visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(16.dp))
        
        Button(onClick = {
            Log.i("Credential","Email : $email Password : $password")
        }) {
            Text(text = "Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(32.dp))
        
        Text(text = "Olvidaste tu contraseña?", modifier = Modifier.clickable {
            
        })

        Spacer(modifier = Modifier.height(32.dp))
        
        Text(text = "O inicia sesión con")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(painter = painterResource(id = R.drawable.fb),
                contentDescription = "Facebook",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        //Facebook clicked
                    }
            )

            Image(painter = painterResource(id = R.drawable.google),
                contentDescription = "Google",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        //Google clicked
                    }
            )

            Image(painter = painterResource(id = R.drawable.twitter),
                contentDescription = "Twitter",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        //Twitter clicked
                    }
            )
        }


        
        


        
        
    }


}










