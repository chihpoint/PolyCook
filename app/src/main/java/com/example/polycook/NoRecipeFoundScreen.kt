package com.example.polycook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polycook.ui.theme.*

@Composable
fun NoRecipeFoundScreen(
    onTryAgainClick: () -> Unit,      // Кнопка "Ввести другие продукты"
    onBackToMenuClick: () -> Unit     // Переход на HomeScreen
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(16.dp)
    ) {
        // Центральная часть (Грустный пельмень и текст)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.sad_pelmen),
                contentDescription = "Sad Chef",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "К сожалению, нам ничего не удалось найти. Попробуйте ввести другой набор продуктов, разрешить приложению добавлять продукты к Вашему перечню или опробовать функцию “Случайное блюдо”",
                color = TextWhite,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontFamily = DefaultFont,
                lineHeight = 24.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onTryAgainClick,
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBeige),
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(55.dp)
            ) {
                Text(
                    text = "Ввести другие продукты",
                    color = TextBlack,
                    fontSize = 18.sp,
                    fontFamily = DefaultFont,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Нижний правый угол: Стрелка + Текст справа
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd) // Нижний правый угол
                .padding(bottom = 16.dp, end = 8.dp)
                .clickable { onBackToMenuClick() }, // Делаем кликабельным для перехода
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Картинка стрелки
            Image(
                painter = painterResource(id = R.drawable.strelka),
                contentDescription = "Back to Menu",
                modifier = Modifier.size(40.dp) // Размер стрелки (можно настроить)
            )

            Spacer(modifier = Modifier.width(8.dp)) // Отступ между стрелкой и текстом

            // Текст справа от картинки
            Text(
                text = "вернуться в главное меню",
                color = TextWhite,
                fontSize = 16.sp,
                fontFamily = DefaultFont
            )
        }
    }
}