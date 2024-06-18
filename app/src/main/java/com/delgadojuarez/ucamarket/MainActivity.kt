package com.delgadojuarez.ucamarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.delgadojuarez.ucamarket.ui.navigation.Navigation
import com.delgadojuarez.ucamarket.ui.screens.LoginScreen
import com.delgadojuarez.ucamarket.ui.theme.UCAMARKETTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View model
        val viewModel : MainViewModel by viewModels()

        setContent {
            //LoginScreen()
            Navigation(viewModel)
        }
    }
}

