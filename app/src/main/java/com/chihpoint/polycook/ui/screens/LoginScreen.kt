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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
// Если у тебя были другие импорты Material, замени их тоже на material3
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import com.chihpoint.polycook.ui.theme.TextBlack
import com.chihpoint.polycook.ui.theme.TextGray
import com.chihpoint.polycook.ui.theme.TextWhite
import com.chihpoint.polycook.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    // Храним состояние текста (то, что печатает пользователь)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isRememberMeChecked by remember { mutableStateOf(false) }

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

        // Email
        Text(
            text = "Почта",
            color = TextWhite,
            fontSize = 16.sp,
            modifier = Modifier.offset(y = (-10).dp).fillMaxWidth().padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("polycook@yandex.ru", color = TextGray) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = TextWhite
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = AccentPink,
                unfocusedIndicatorColor = AccentPink,
                textColor = TextWhite,
                cursorColor = AccentPink
            ),
            modifier = Modifier.offset(y = (-25).dp).fillMaxWidth()
        )

        // Пароль
        Text(
            text = "Пароль",
            color = TextWhite,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth().padding(start = 4.dp, bottom = 4.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("введите Ваш пароль", color = TextGray) },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = null, tint = TextWhite)
            },
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
                focusedIndicatorColor = AccentPink,
                unfocusedIndicatorColor = AccentPink,
                textColor = TextWhite,
                cursorColor = AccentPink
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Галочка и "Забыли пароль"
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Левая часть: Чекбокс + Текст
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isRememberMeChecked,
                    onCheckedChange = { isRememberMeChecked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = AccentPink,
                        uncheckedColor = TextWhite,
                        checkmarkColor = Color.White
                    )
                )
                Text(text = "Запомнить меня", color = TextWhite, fontSize = 14.sp)
            }

            // Правая часть: Забыли пароль
            Text(
                text = "Забыли пароль?",
                color = AccentPink,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onForgotPasswordClick() }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Кнопка Войти
        Button(
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBeige),
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text(
                text = "Войти",
                color = TextBlack,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Нет аккаунта? Зарегистрироваться
        Row {
            Text(text = "Нет аккаунта? ", color = TextGray)
            Text(
                text = "Зарегистрироваться",
                color = AccentPink,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onRegisterClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen({}, {}, {})
}