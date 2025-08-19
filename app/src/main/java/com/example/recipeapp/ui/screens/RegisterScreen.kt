package com.example.recipeapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.res.painterResource
import com.example.recipeapp.R
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.VisualTransformation
import com.example.recipeapp.ui.navigation.Screen
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(paddingValues: PaddingValues, navController: NavHostController)
{
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Реєстрація",
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            item {
                Text("Ім'я", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = LabelColor)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Введіть нікнейм", color = LabelColor.copy(alpha = 0.5f)) },
                    colors = fieldColors(),
                    shape = FieldShape,
                    singleLine = true
                )
            }

            item {
                Text("E-mail", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = LabelColor)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Приклад: you@example.com", color = LabelColor.copy(alpha = 0.5f)) },
                    colors = fieldColors(),
                    shape = FieldShape,
                    singleLine = true
                )
            }

            item {
                Text("Пароль", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = LabelColor)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Введіть пароль", color = LabelColor.copy(alpha = 0.5f)) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle password visibility",
                                tint = if (passwordVisible) DarkTeal else LabelColor.copy(alpha = 0.5f)
                            )
                        }
                    },
                    colors = fieldColors(),
                    shape = FieldShape,
                    singleLine = true
                )
            }

            item {
                Text("Підтвердіть пароль", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = LabelColor)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Повторіть пароль", color = LabelColor.copy(alpha = 0.5f)) },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(
                                imageVector = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle confirm password visibility",
                                tint = if (confirmPasswordVisible) DarkTeal else LabelColor.copy(alpha = 0.5f)
                            )
                        }
                    },
                    colors = fieldColors(),
                    shape = FieldShape,
                    singleLine = true
                )
            }

            item {
                Button(
                    onClick = { /* TODO: Зареєструвати */ },
                    colors = ButtonDefaults.buttonColors(containerColor = DarkTeal),
                    shape = FieldShape,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Зареєструватися", color = Color.White)
                }
            }

            item {
                Text("Або", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), color = LabelColor)
            }

            item {
                Button(
                    onClick = { /* TODO: Реєстрація через Google */ },
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
                TextButton(
                    onClick = {navController.navigate(Screen.Login.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("У мене вже є акаунт",
                        color = DarkTeal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 16.sp)
                }
            }
        }
    }
}
