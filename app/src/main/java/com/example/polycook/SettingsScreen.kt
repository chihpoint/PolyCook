package com.example.polycook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polycook.ui.theme.*

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit
) {
    // Состояние полей
    var email by remember { mutableStateOf("polycook@yandex.ru") }
    var password by remember { mutableStateOf("12345678") } // Пароль для примера
    var isPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(24.dp)
    ) {
        // --- ЦЕНТРАЛЬНЫЙ КОНТЕНТ ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center) // Центрируем по вертикали
                .offset(y = (-40).dp),   // Чуть сдвигаем вверх визуально
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. ЗАГОЛОВОК "НАСТРОЙКИ" + КАРТИНКА
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "НАСТРОЙКИ",
                    color = ButtonBeige, // Бежевый цвет
                    fontSize = 40.sp,    // Крупный шрифт
                    fontFamily = CartoonFont, // Ваш кастомный шрифт
                    letterSpacing = 2.sp
                )
                Spacer(modifier = Modifier.width(10.dp))

                // Картинка повара (замените R.drawable.cook_icon на имя вашего файла с поваром)
                // Если картинки пока нет, можно временно убрать Image
                Image(
                    painter = painterResource(id = R.drawable.polycook_logo), // <-- Вставьте сюда R.drawable.ваша_картинка_повара
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            // 2. ПОЛЕ ПОЧТА
            SettingsField(
                label = "Почта",
                value = email,
                onValueChange = { email = it },
                leadingIcon = Icons.Default.Email
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 3. ПОЛЕ ПАРОЛЬ
            SettingsField(
                label = "Пароль",
                value = password,
                onValueChange = { password = it },
                leadingIcon = Icons.Default.Lock, // Или Icons.Default.Key
                isPassword = true,
                isPasswordVisible = isPasswordVisible,
                onVisibilityChange = { isPasswordVisible = !isPasswordVisible }
            )
        }

        // --- НИЖНЯЯ ПАНЕЛЬ "ВЕРНУТЬСЯ" ---
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd) // Прижимаем к низу
                .clickable { onBackClick() }
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Вернуться в главное меню",
                color = TextWhite,
                fontSize = 16.sp,
                fontFamily = DefaultFont,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Ваша картинка стрелки
            Image(
                painter = painterResource(id = R.drawable.strelka),
                contentDescription = "Назад",
                modifier = Modifier.height(50.dp), // Размер кнопки
                contentScale = ContentScale.Fit
            )
        }
    }
}

// --- КАСТОМНЫЙ КОМПОНЕНТ ПОЛЯ ВВОДА (КАК НА КАРТИНКЕ) ---
@Composable
fun SettingsField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: androidx.compose.ui.graphics.vector.ImageVector,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onVisibilityChange: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Название поля (Почта / Пароль)
        Text(
            text = label,
            color = TextWhite,
            fontSize = 18.sp,
            fontFamily = DefaultFont,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Строка ввода с подчеркиванием
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    // Рисуем белую линию снизу
                    drawLine(
                        color = Color.White,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 2.dp.toPx()
                    )
                }
                .padding(bottom = 8.dp), // Отступ от текста до линии
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Иконка слева (конверт или замок)
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = TextWhite,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Вертикальная черта |
            Box(
                modifier = Modifier
                    .width(1.5.dp)
                    .height(20.dp)
                    .background(TextWhite)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Поле ввода
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    color = TextWhite,
                    fontSize = 16.sp,
                    fontFamily = DefaultFont
                ),
                visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                modifier = Modifier.weight(1f) // Занимает всё доступное место
            )

            // Иконки справа
            if (isPassword) {
                // Глазик (показать/скрыть)
                IconButton(
                    onClick = onVisibilityChange,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password",
                        tint = TextWhite
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
            }

            // Карандаш (редактировать)
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = TextWhite,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen({})
}