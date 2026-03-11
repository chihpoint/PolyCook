package com.example.polycook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.polycook.ui.theme.*

// Цвет фона меню (подобран по вашей картинке)
val MenuTeal = Color(0xFF588C95)

@Composable
fun HomeScreen(
    onSearchClick: () -> Unit,
    onRandomClick: () -> Unit,
    onSavedClick: () -> Unit,
    onInstructionClick: () -> Unit,
    onProfileSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        // --- ОСНОВНОЙ КОНТЕНТ ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            // Заголовок
            Image(
                painter = painterResource(id = R.drawable.img_home_header),
                contentDescription = "Заголовок",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )

            // Кнопки основного меню
            MenuButton(text = "Поиск по продуктам \uD83C\uDF45", onClick = onSearchClick)
            Spacer(modifier = Modifier.height(30.dp))

            MenuButton(text = "Случайное блюдо \uD83C\uDF2E", onClick = onRandomClick)
            Spacer(modifier = Modifier.height(30.dp))

            MenuButton(text = "Сохраненные рецепты \uD83D\uDCD2", onClick = onSavedClick)

            Spacer(modifier = Modifier.weight(1f))

            // Инструкция
            Text(
                text = "Инструкция",
                color = AccentPink,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = DefaultFont,
                modifier = Modifier.clickable { onInstructionClick() }
            )
            Spacer(modifier = Modifier.height(78.dp))
        }

        // --- ПРАВЫЙ ВЕРХНИЙ УГОЛ: КНОПКА + МЕНЮ ---
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 24.dp, end = 24.dp)
        ) {
            // 1. Кнопка-картинка (Аватарка/Настройки)
            IconButton(
                onClick = { menuExpanded = !menuExpanded }, // Переключаем состояние
                modifier = Modifier.size(50.dp) // Размер иконки
            ) {
                Image(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "Настройки",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // 2. КАСТОМНОЕ МЕНЮ (POPUP)
            if (menuExpanded) {
                Popup(
                    alignment = Alignment.TopEnd,
                    onDismissRequest = { menuExpanded = false },
                    offset = IntOffset(x = -20, y = 140) // Сдвигаем меню чуть вниз и влево относительно кнопки
                ) {
                    // Контейнер меню (Сине-зеленый фон)
                    Box(
                        modifier = Modifier
                            .width(260.dp) // Ширина меню
                            .background(MenuTeal, RoundedCornerShape(20.dp)) // Цвет и сильное скругление
                            .padding(16.dp) // Отступы внутри меню
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp) // Расстояние между кнопками
                        ) {
                            // Кнопка 1: Выйти
                            MenuLinkButton(
                                text = "Выйти из аккаунта",
                                onClick = {
                                    menuExpanded = false
                                    onLogoutClick()
                                }
                            )

                            // Кнопка 2: Настройки профиля
                            MenuLinkButton(
                                text = "Настройки профиля",
                                onClick = {
                                    menuExpanded = false
                                    onProfileSettingsClick()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

// Компонент для больших кнопок на главном экране
@Composable
fun MenuButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = ButtonBeige),
        shape = RoundedCornerShape(60),
        modifier = Modifier
            .width(320.dp)
            .height(60.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(50))
    ) {
        Text(
            text = text,
            color = TextBlack,
            fontSize = 21.sp,
            fontFamily = DefaultFont,
            fontWeight = FontWeight.Bold
        )
    }
}

// Компонент для кнопок ВНУТРИ выпадающего меню
@Composable
fun MenuLinkButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = ButtonBeige), // Бежевый фон
        shape = RoundedCornerShape(12.dp), // Скругление как на картинке
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            color = TextBlack,
            fontSize = 16.sp,
            fontFamily = DefaultFont,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen({}, {}, {}, {}, {}, {})
}