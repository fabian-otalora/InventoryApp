package com.example.myapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapp.models.Products
import com.example.myapp.viewmodel.ProductsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductView(navController: NavController, viewModel: ProductsViewModel, id: Int, name: String?, price: String?, description: String?, stock: String?) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Editar View", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack()}
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar", tint = Color.White)
                    }
                }
            )
        }
    ) {
        ContentEditProduct(it, navController, viewModel, id, name, price, description, stock)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentEditProduct(it: PaddingValues, navController: NavController, viewModel: ProductsViewModel, id: Int, name: String?, price: String?, description: String?, stock: String?) {
    var name by remember { mutableStateOf(name) }
    var price by remember { mutableStateOf(price) }
    var description by remember { mutableStateOf(description) }
    var stock by remember { mutableStateOf(stock) }

    Column(
        modifier = Modifier.padding(it).padding(top = 30.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name ?: "",
            onValueChange = {name = it},
            label = { Text(text = "Nombre")},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp).padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = price ?: "",
            onValueChange = {price = it},
            label = { Text(text = "Precio")},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp).padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = description ?: "",
            onValueChange = {description = it},
            label = { Text(text = "Descripción")},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp).padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = stock ?: "",
            onValueChange = {stock = it},
            label = { Text(text = "Stock")},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp).padding(bottom = 15.dp)
        )

        Button(
            onClick = {
                val product = Products(id = id, name = name!!, price = price!!, description = description!!, stock = stock!!)
                viewModel.updateProduct(product)
                navController.popBackStack()
            }
        ){
            Text(text = "Editar")
        }
    }
}