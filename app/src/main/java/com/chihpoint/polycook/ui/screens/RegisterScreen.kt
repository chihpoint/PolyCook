package com.chihpoint.polycook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import com.chihpoint.polycook.ui.theme.AccentPink
import com.chihpoint.polycook.ui.theme.AppBackground
import com.chihpoint.polycook.ui.theme.ButtonBeige
import com.chihpoint.polycook.ui.theme.CartoonFont
import com.chihpoint.polycook.ui.theme.ErrorRed
import com.chihpoint.polycook.ui.theme.RequirementsBackground
import com.chihpoint.polycook.ui.theme.SuccessGreen
import com.chihpoint.polycook.ui.theme.TextBlack
import com.chihpoint.polycook.ui.theme.TextGray
import com.chihpoint.polycook.ui.theme.TextWhite
import com.chihpoint.polycook.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    //  ЛОГИКА ПРОВЕРКИ ПАРОЛЯ
    val hasLength = password.length >= 8
    val hasDigit = password.any { it.isDigit() }
    val hasUppercase = password.any { it.isUpperCase() }

    val isFormValid = hasLength && hasDigit && hasUppercase && email.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.polycook_logo),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .offset(x = (-20).dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text = "PolyCook",
            color = ButtonBeige,
            fontSize = 70.sp,
            fontFamily = CartoonFont,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-20).dp)
        )

        Spacer(modifier = Modifier.height(0.dp))

        // Поле Почта
        Text(text = "Почта", color = TextWhite, fontSize = 16.sp, modifier = Modifier.offset(y = (-10).dp).fillMaxWidth().padding(start = 4.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("polycook@yandex.ru", color = TextGray) },
            leadingIcon = { Icon(Icons.Default.Email, null, tint = TextWhite) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = AccentPink,
                unfocusedIndicatorColor = AccentPink,
                textColor = TextWhite,
                cursorColor = AccentPink
            ),
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-15).dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Поле Пароль
        Text(text = "Пароль", color = TextWhite, fontSize = 16.sp, modifier = Modifier.offset(y = (-10).dp).fillMaxWidth().padding(start = 4.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("введите Ваш пароль", color = TextGray) },
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
            modifier = Modifier
                .offset(y = (-15).dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(0.dp))

        //  БЛОК С ПРАВИЛАМИ (УМНЫЙ)
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

        Spacer(modifier = Modifier.height(15.dp))

        // Кнопка Регистрация
        Button(
            onClick = onRegisterClick,
            enabled = true,
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBeige),
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text(
                text = "Регистрация",
                color = TextBlack,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(text = "Уже есть аккаунт? ", color = TextGray)
            Text(
                text = "Войти",
                color = AccentPink,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onLoginClick() }
            )
        }
    }
}

//  УМНЫЙ КОМПОНЕНТ СТРОКИ
@Composable
fun RequirementItem(text: String, isCompleted: Boolean) {
    val color = if (isCompleted) SuccessGreen else ErrorRed
    val icon = if (isCompleted) "✓ " else "✕ "

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = icon,
            color = color,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text,
            color = color,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen({}, {})
}