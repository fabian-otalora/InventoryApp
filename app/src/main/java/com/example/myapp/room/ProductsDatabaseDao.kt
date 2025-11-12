package com.example.myapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapp.models.Products
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDatabaseDao {
   @Query("SELECT * FROM products")
   fun getProducts(): Flow<List<Products>>

   @Query("SELECT * FROM products WHERE id = :id")
   fun getProduct(id: Int): Flow<Products>

   @Insert
   suspend fun addProduct(product: Products)

   @Update
   suspend fun updateProduct(product: Products)

   @Delete
   suspend fun deleteProduct(product: Products)

}