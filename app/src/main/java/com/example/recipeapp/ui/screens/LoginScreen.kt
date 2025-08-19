package com.example.recipeapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.recipeapp.ui.navigation.Screen
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(paddingValues: PaddingValues, navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Авторизація",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Teal)
            )
        },
        containerColor = CreamBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            item {
                Text("Email", color = LabelColor, fontWeight = FontWeight.Medium, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Введіть email", color = LabelColor.copy(alpha = 0.5f)) },
                    singleLine = true,
                    colors = fieldColors(),
                    shape = FieldShape
                )
            }

            item {
                Text("Пароль", color = LabelColor, fontWeight = FontWeight.Medium, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Введіть пароль", color = LabelColor.copy(alpha = 0.5f)) },
                    singleLine = true,
                    colors = fieldColors(),
                    shape = FieldShape,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle password visibility",
                                tint = if (passwordVisible) DarkTeal else LabelColor.copy(alpha = 0.5f)
                            )
                        }
                    }
                )
            }

            item {
                Button(
                    onClick = { /* TODO: Увійти */ },
                    colors = ButtonDefaults.buttonColors(containerColor = DarkTeal),
                    shape = FieldShape,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Увійти", color = Color.White)
                }
            }

            item {
                Button(
                    onClick = { /* TODO: Вхід через Google */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = FieldShape,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.google_logo),
                        contentDescription = "Google",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Продовжити через Google", color = LabelColor)
                }
            }

            item {
                Text(
                    "Забули пароль?",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = DarkTeal,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }

            item {
                TextButton(
                    onClick = {
                        navController.navigate(Screen.Register.route)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "У мене ще немає акаунту",
                        color = DarkTeal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

