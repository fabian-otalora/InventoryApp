package com.example.myapp.states

import com.example.myapp.models.Products

data class ProductState(
    val listProducts: List<Products> = emptyList()
)
