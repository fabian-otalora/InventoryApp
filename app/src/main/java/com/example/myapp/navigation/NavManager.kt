package com.example.myapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapp.viewmodel.ProductsViewModel
import com.example.myapp.viewmodel.AuthViewModel
import com.example.myapp.views.EditProductView
import com.example.myapp.views.HomeView
import com.example.myapp.views.LoginView
import com.example.myapp.views.NewProductView
import com.example.myapp.views.RegisterView


@Composable
fun NavManager(viewModel: ProductsViewModel, authViewModel: AuthViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("home"){
            HomeView(navController, viewModel, authViewModel)
        }

        composable("newProduct") {
            NewProductView(navController, viewModel)
        }

        composable("edit/{id}/{name}/{price}/{description}/{stock}", arguments = listOf(
            navArgument("id") {type = NavType.IntType},
            navArgument("name") {type = NavType.StringType},
            navArgument("price") {type = NavType.StringType} ,
            navArgument("description") {type = NavType.StringType},
            navArgument("stock") {type = NavType.StringType}
        )) {
            EditProductView(
                navController,
                viewModel,
                it.arguments!!.getInt("id"),
                it.arguments?.getString("name"),
                it.arguments?.getString("price"),
                it.arguments?.getString("description"),
                it.arguments?.getString("stock")
            )
        }

        composable("login") {
            LoginView(
                viewModel = authViewModel,
                onRegisterClick = { navController.navigate("register") },
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("register") {
            RegisterView(
                viewModel = authViewModel,
                onRegisterSuccess = {
                    navController.navigate("home") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }
    }
}
