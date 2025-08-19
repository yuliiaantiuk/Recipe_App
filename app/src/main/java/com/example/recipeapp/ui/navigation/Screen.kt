package com.example.recipeapp.ui.navigation

sealed class Screen(val route: String) {
    object RecipeList : Screen("recipe_list")
    object AddRecipe : Screen("add_recipe")
    object Favorites : Screen("favorites")
    object Search : Screen("search")
    object Profile : Screen("profile")
    object Register : Screen("register")
    object Login : Screen("login")
    object RecipeDetail : Screen("detail")
}
