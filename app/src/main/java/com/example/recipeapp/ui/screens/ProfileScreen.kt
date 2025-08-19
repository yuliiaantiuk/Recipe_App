package com.example.recipeapp.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipeapp.ui.navigation.Screen
import com.example.recipeapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(paddingValues: PaddingValues, navController: NavHostController) {
    var profilePhotoUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        profilePhotoUri = uri
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Профіль",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Profile.route) { inclusive = true }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Вийти",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Teal)
            )
        },
        containerColor = CreamBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .clickable { photoPickerLauncher.launch("image/*") }
                ) {
                    if (profilePhotoUri != null) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(profilePhotoUri)
                                .build(),
                            contentDescription = "Аватарка",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painter = painterResource(id = android.R.drawable.ic_menu_camera),
                            contentDescription = "Виберіть фото",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        )
                    }
                }

                Column {
                    Text("Ім'я користувача", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = LabelColor)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Створено рецептів: 10", fontSize = 14.sp, color = LabelColor)
                    Text("Опубліковано: 6", fontSize = 14.sp, color = LabelColor)
                    Text("Сума лайків: 150", fontSize = 14.sp, color = LabelColor)
                    Text("З нами з: 15.04.2024", fontSize = 14.sp, color = LabelColor)
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { /* TODO: Налаштування акаунту */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Teal),
                    shape = FieldShape
                ) {
                    Text("Акаунт", color = Color.White)
                }

                Button(
                    onClick = { /* TODO: Налаштування застосунку */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Teal),
                    shape = FieldShape
                ) {
                    Text("Інтерфейс", color = Color.White)
                }
            }

            Text("Досягнення", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = LabelColor)
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Досягнення 1",
                    tint = DarkTeal,
                    modifier = Modifier.size(36.dp)
                )
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Досягнення 2",
                    tint = Teal,
                    modifier = Modifier.size(36.dp)
                )
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Досягнення 3",
                    tint = Color.Gray,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}