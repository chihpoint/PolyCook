package com.chihpoint.polycook.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chihpoint.polycook.ui.theme.AccentPink
import com.chihpoint.polycook.ui.theme.AppBackground
import com.chihpoint.polycook.ui.theme.TextWhite

@Composable
fun InstructionScreen(
    onBackClick: () -> Unit
) {
    // Column с возможностью прокрутки
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {

        // --- Блок 1 ---
        InstructionBlock(
            title = "Поиск по продуктам \uD83C\uDF45",
            text = "Введите перечень продуктов, которые у Вас есть, и PolyCook найдет подходящие рецепты!\n" +
                    "Например, \"курица, картофель, лук\".\n" +
                    "Приложение изучит Ваш список ингредиентов и предложит несколько восхитительных рецептов, которые можно приготовить прямо сейчас. Наслаждайтесь готовкой с тем, что у вас уже есть! \uD83C\uDF73"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- Блок 2 ---
        InstructionBlock(
            title = "Случайное блюдо \uD83C\uDF2E",
            text = "Нажмите кнопку “Случайное блюдо”, и приложение выберет рецепт, который сможет Вас заинтересовать!\n" +
                    "Почувствуйте себя кулинарным авантюристом и приготовьте что-то новое и увлекательное! \uD83C\uDF1F"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- Блок 3 ---
        InstructionBlock(
            title = "Другой рецепт \uD83D\uDD04",
            text = "Не понравился предложенный рецепт? Не беда! Нажмите кнопку \"Другой рецепт\", и PolyCook предложит Вам другой вариант. Продолжайте нажимать, пока не найдете то, что Вам действительно по душе! \uD83D\uDE0B"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- Блок 4 (Заключение) ---
        Text(
            text = "Наслаждайтесь процессом готовки и откройте для себя множество кулинарных шедевров вместе с PolyCook! \uD83C\uDF7D\uFE0F\uD83D\uDC69\u200D\uD83C\uDF73",
            color = TextWhite,
            fontSize = 16.sp,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        // --- КНОПКА "ВЕРНУТЬСЯ" ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onBackClick() },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Вернуться",
                color = TextWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.width(12.dp))

            Surface(
                color = AccentPink,
                shape = CircleShape,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Назад",
                        tint = TextWhite,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        // Отступ снизу, чтобы на телефонах с челкой/жестами было удобно
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun InstructionBlock(title: String, text: String) {
    Column {
        Text(
            text = title,
            color = TextWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            color = TextWhite,
            fontSize = 16.sp,
            lineHeight = 22.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InstructionScreenPreview() {
    InstructionScreen({})
}