package com.example.recipeapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.recipeapp.ui.navigation.Screen
import androidx.compose.ui.graphics.Color

data class BottomNavItem(val screen: Screen, val icon: ImageVector, val label: String)

val bottomNavItems = listOf(
    BottomNavItem(Screen.RecipeList, Icons.Default.List, "Головна"),
    BottomNavItem(Screen.AddRecipe, Icons.Default.Add, "Додати"),
    BottomNavItem(Screen.Favorites, Icons.Default.Favorite, "Обране"),
    BottomNavItem(Screen.Search, Icons.Default.Search, "Знайти"),
    BottomNavItem(Screen.Profile, Icons.Default.Person, "Профіль")
)

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        containerColor = Color(0xFF03B5AA)
    ) {
        bottomNavItems.forEach { item ->
            val selected = navBackStackEntry.value?.destination?.route == item.screen.route

            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(item.screen.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(item.label)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                    indicatorColor = Color(0xFF256E6A)
                )
            )
        }
    }
}
