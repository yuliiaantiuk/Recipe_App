package com.example.recipeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeapp.ui.screens.*
import androidx.compose.foundation.layout.PaddingValues

@Composable
fun AppNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.RecipeList.route
    ) {
        composable(Screen.RecipeList.route) {
            RecipeListScreen(paddingValues = paddingValues, navController = navController)
        }
        composable(Screen.RecipeDetail.route) {
            RecipeDetailScreen(paddingValues = paddingValues, navController = navController)
        }
        composable(Screen.AddRecipe.route) {
            AddRecipeScreen(paddingValues = paddingValues)
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(paddingValues = paddingValues, navController = navController)
        }
        composable(Screen.Search.route) {
            SearchScreen(paddingValues = paddingValues, navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(paddingValues = paddingValues, navController = navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(paddingValues = paddingValues, navController = navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(paddingValues = paddingValues, navController = navController)
        }
    }
}


