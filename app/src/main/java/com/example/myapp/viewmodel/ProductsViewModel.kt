package com.example.myapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.models.Products
import com.example.myapp.room.ProductsDatabaseDao
import com.example.myapp.states.ProductState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductsViewModel (
    private val dao: ProductsDatabaseDao
): ViewModel(){

    var state by mutableStateOf(ProductState())
        private set

    init {
        viewModelScope.launch {
            dao.getProducts().collectLatest {
                state = state.copy(
                    listProducts = it
                )
            }
        }
    }

    fun addProduct(product: Products) = viewModelScope.launch {
        dao.addProduct(product = product)
    }

    fun updateProduct(product: Products) = viewModelScope.launch {
        dao.updateProduct(product = product)
    }

    fun deleteProduct(product: Products) = viewModelScope.launch {
        dao.deleteProduct(product = product)
    }
}