package com.example.polycook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape // <--- Добавлен импорт
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack // <--- Добавлен импорт стрелки
import androidx.compose.material.icons.filled.Check
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
fun LoginScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onBackClick: () -> Unit // 1. <--- НОВЫЙ ПАРАМЕТР для кнопки "Назад"
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isRememberMeChecked by remember { mutableStateOf(false) }

    // 2. <--- ОБЕРНУЛИ ВСЁ В Box, чтобы позиционировать элементы слоями
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        // --- ВАШ СТАРЫЙ КОД (COLUMN) ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .offset(y = (-30).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.polycook_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .offset(x = (-10).dp),
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
                fontFamily = DefaultFont,
                fontSize = 16.sp,
                modifier = Modifier.offset(y = (-10).dp).fillMaxWidth().padding(start = 4.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("polycook@yandex.ru", fontFamily = DefaultFont, color = TextGray) },
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
                fontFamily = DefaultFont,
                modifier = Modifier.fillMaxWidth().padding(start = 4.dp, bottom = 4.dp)
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("введите Ваш пароль", fontFamily = DefaultFont, color = TextGray) },
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val outerSize = 24.dp
                    val innerSize = 14.dp
                    val cornerRadius = 8.dp
                    val strokeWidth = 2.dp

                    val activeColor = AccentPink
                    val inactiveColor = TextWhite

                    Box(
                        modifier = Modifier
                            .size(outerSize)
                            .border(
                                width = strokeWidth,
                                color = if (isRememberMeChecked) activeColor else inactiveColor,
                                shape = RoundedCornerShape(cornerRadius)
                            )
                            .clickable { isRememberMeChecked = !isRememberMeChecked },
                        contentAlignment = Alignment.Center
                    ) {
                        if (isRememberMeChecked) {
                            Box(
                                modifier = Modifier
                                    .size(innerSize)
                                    .background(
                                        color = activeColor,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Запомнить меня",
                        color = TextWhite,
                        fontFamily = DefaultFont,
                        fontSize = 14.sp
                    )
                }
                Text(
                    text = "Забыли пароль?",
                    color = AccentPink,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = DefaultFont,
                    modifier = Modifier.clickable { onForgotPasswordClick() }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

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
                    fontFamily = DefaultFont,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Нет аккаунта? Зарегистрироваться
            Row {
                Text(text = "Нет аккаунта? ", fontFamily = DefaultFont, color = TextGray)
                Text(
                    text = "Зарегистрироваться",
                    color = AccentPink,
                    fontWeight = FontWeight.Bold,
                    fontFamily = DefaultFont,
                    modifier = Modifier.clickable { onRegisterClick() }
                )
            }
        } // Конец Column

        // 3. <--- КНОПКА "ВЕРНУТЬСЯ" В ПРАВОМ НИЖНЕМ УГЛУ
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd) // Прижимаем к углу
                .padding(bottom = 24.dp, end = 24.dp) // Отступы от края экрана
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onBackClick() } // Вызываем действие возврата
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
                    modifier = Modifier
                        .height(50.dp) // Высота 50dp (как раньше был диаметр круга)
                    // Ширина подстроится автоматически, чтобы сохранить пропорции.
                    // Если картинка кажется слишком маленькой/большой,
                    // измените 50.dp на 60.dp или 70.dp
                    ,
                    contentScale = ContentScale.Fit
                )
            }
        }
    } // Конец Box
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    // Не забываем добавить пустую лямбду для onBackClick в превью
    LoginScreen({}, {}, {}, {})
}