package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.myapp.navigation.NavManager
import com.example.myapp.room.ProductsDatabase
import com.example.myapp.ui.theme.MyAppTheme
import com.example.myapp.viewmodel.AuthViewModel
import com.example.myapp.viewmodel.ProductsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val database =
                        Room.databaseBuilder(this, ProductsDatabase::class.java, "db_products")
                            .fallbackToDestructiveMigration(false)
                            .build()
                    val dao = database.productsDao()
                    val userDao = database.userDao()
                    val viewModel = ProductsViewModel(dao)
                    val authViewModel = AuthViewModel(userDao)

                    NavManager(viewModel = viewModel, authViewModel = authViewModel)
                }
            }
        }
    }
}
