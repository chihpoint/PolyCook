package com.example.polycook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polycook.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPasswordScreen(
    onChangePasswordClick: () -> Unit // Действие "Сменить пароль"
) {
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val hasLength = password.length >= 8
    val hasDigit = password.any { it.isDigit() }
    val hasUppercase = password.any { it.isUpperCase() }

    val isFormValid = hasLength && hasDigit && hasUppercase

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // --- Логотипы ---
        Image(
            painter = painterResource(id = R.drawable.polycook_logo),
            contentDescription = null,
            modifier = Modifier.size(160.dp),
            contentScale = ContentScale.Fit
        )
        Image(
            painter = painterResource(id = R.drawable.polycook_text),
            contentDescription = null,
            modifier = Modifier.width(160.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Заголовок поля
        Text(
            text = "Введите новый пароль",
            color = TextWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth().padding(start = 4.dp, bottom = 8.dp)
        )

        //  Поле ввода пароля
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("введите пароль", color = TextGray) },
            leadingIcon = { Icon(Icons.Default.Lock, null, tint = TextWhite) },
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null,
                        tint = TextWhite
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = TextWhite,
                unfocusedIndicatorColor = TextWhite,
                textColor = TextWhite,
                cursorColor = TextWhite
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        //  Блок с правилами
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(RequirementsBackground, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Ваш пароль должен содержать минимум:",
                    color = TextWhite,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))

                RequirementItem(text = "8 символов", isCompleted = hasLength)
                RequirementItem(text = "1 цифру", isCompleted = hasDigit)
                RequirementItem(text = "1 заглавную букву", isCompleted = hasUppercase)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        //  Кнопка "Сменить пароль"
        Button(
            onClick = onChangePasswordClick,
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBeige),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "Сменить пароль",
                color = TextBlack,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun NewPasswordScreenPreview() {
    NewPasswordScreen({})
}