package com.example.myapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapp.models.Products
import com.example.myapp.models.User
@Database(
    entities = [Products::class, User::class],
    version = 2,
    exportSchema = false
)

abstract class ProductsDatabase: RoomDatabase() {
    abstract fun productsDao(): ProductsDatabaseDao
    abstract fun userDao(): UserDao
}