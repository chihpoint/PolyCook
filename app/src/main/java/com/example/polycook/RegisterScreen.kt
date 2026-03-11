package com.example.polycook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    onBackClick: () -> Unit // 1. <--- НОВЫЙ ПАРАМЕТР
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    // ЛОГИКА ПРОВЕРКИ ПАРОЛЯ
    val hasLength = password.length >= 8
    val hasDigit = password.any { it.isDigit() }
    val hasUppercase = password.any { it.isUpperCase() }

    val isFormValid = hasLength && hasDigit && hasUppercase && email.isNotEmpty()

    // 2. <--- ОБЕРНУЛИ В Box ДЛЯ СЛОЕВ
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .offset(y = (-40).dp)
                .verticalScroll(rememberScrollState()),
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
            Text(text = "Почта", color = TextWhite, fontSize = 16.sp, fontFamily = DefaultFont, modifier = Modifier.offset(y = (-15).dp).fillMaxWidth().padding(start = 4.dp))
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
                    .offset(y = (-25).dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Поле Пароль
            Text(text = "Пароль", color = TextWhite, fontSize = 16.sp, fontFamily = DefaultFont, modifier = Modifier.offset(y = (-25).dp).fillMaxWidth().padding(start = 4.dp))
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
                    .offset(y = (-35).dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(0.dp))

            // БЛОК С ПРАВИЛАМИ
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-25).dp)
                    .background(RequirementsBackground, RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Ваш пароль должен содержать минимум:",
                        color = TextWhite,
                        fontSize = 12.sp,
                        fontFamily = DefaultFont,
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
                modifier = Modifier.fillMaxWidth().offset(y = (-25).dp).height(56.dp)
            ) {
                Text(
                    text = "Регистрация",
                    color = TextBlack,
                    fontSize = 18.sp,
                    fontFamily = DefaultFont,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text(text = "Уже есть аккаунт? ", color = TextGray, modifier = Modifier.offset(y = (-25).dp))
                Text(
                    text = "Войти",
                    color = AccentPink,
                    fontWeight = FontWeight.Bold,
                    fontFamily = DefaultFont,
                    modifier = Modifier.offset(y = (-25).dp).clickable { onLoginClick() }
                )
            }
        }

        // 4. <--- КНОПКА "ВЕРНУТЬСЯ" ВНИЗУ СПРАВА
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 24.dp, end = 24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onBackClick() }
                    .padding(8.dp)
            ) {
                Text(
                    text = "Вернуться",
                    color = TextWhite,
                    fontFamily = DefaultFont,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    painter = painterResource(id = R.drawable.strelka),
                    contentDescription = "Вернуться",
                    modifier = Modifier.height(50.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

// УМНЫЙ КОМПОНЕНТ СТРОКИ
@Composable
fun RequirementItem(text: String, isCompleted: Boolean) {
    val color = if (isCompleted) SuccessGreen else ErrorRed
    val icon = if (isCompleted) "✓ " else "✕ "

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = icon,
            color = color,
            fontSize = 14.sp,
            fontFamily = DefaultFont,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text,
            color = color,
            fontFamily = DefaultFont,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen({}, {}, {})
}