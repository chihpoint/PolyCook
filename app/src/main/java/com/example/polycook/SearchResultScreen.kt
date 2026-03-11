package com.example.polycook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polycook.ui.theme.*

@Composable
fun SearchResultScreen(
    onBackClick: () -> Unit,
    onNextRecipeClick: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }

    // Основной контейнер
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- ЗАГОЛОВОК (Фиксированный) ---
        Text(
            text = "ХЛЕБНЫЕ ЛЕПЕШКИ",
            color = ButtonBeige,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            fontFamily = CartoonFont,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // --- ЦЕНТРАЛЬНАЯ ЧАСТЬ (Занимает все свободное место) ---
        Column(
            modifier = Modifier
                .weight(1f) // Растягиваем эту колонку
                .fillMaxWidth()
        ) {
            // Ингредиенты (40% высоты)
            ScrollableRecipeSection(
                title = "Ингредиенты:",
                content = "Мука - 400-500 г, Вода горячая (70-80 градусов) - 250-300 мл, Соль - 1 ч. ложка",
                modifier = Modifier.weight(0.4f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Рецепт (60% высоты)
            ScrollableRecipeSection(
                title = "Рецепт:",
                content = "Замесить тесто на теплой воде так, чтобы оно было мягким. Для этого насыпать муку горкой, сделать в середине углубление. Понемногу добавляя в него теплую воду с добавлением соли, вымесить мягкое, эластичное тесто. Дать тесту полежать 10 минут. Разделать тесто на шарики размером с куриное яйцо. Затем шарики сплющить и придать форму толстых лепешек. Разогреть сковороду. Лепешки по-домашнему жарить на сковороде с ровным днищем. Как только лепешка начнет надуваться (через 2 минуты), перевернуть и жарить до готовности (еще 1,5 минуты). Домашние лепёшки готовы!",
                modifier = Modifier.weight(0.6f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- КНОПКА "ДРУГОЙ РЕЦЕПТ" ---
        Button(
            onClick = {
                isFavorite = false
                onNextRecipeClick()
            },
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBeige),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(55.dp)
        ) {
            Text(
                text = "Другой рецепт",
                color = TextBlack,
                fontSize = 20.sp,
                fontFamily = DefaultFont,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Next",
                tint = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- НИЖНЯЯ ПАНЕЛЬ (ЛАЙК + ВЕРНУТЬСЯ) ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // КНОПКА ЛАЙК
            IconButton(
                onClick = { isFavorite = !isFavorite },
                modifier = Modifier
                    .size(50.dp)
                    .background(AccentPink, CircleShape)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Like",
                    tint = if (isFavorite) Color(0xFFB71C1C) else TextWhite,
                    modifier = Modifier.size(28.dp)
                )
            }

            // КНОПКА ВЕРНУТЬСЯ
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
                    fontSize = 18.sp,
                    fontFamily = DefaultFont
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

// Переиспользуемый компонент (должен быть в файле или скопирован сюда, если файлы разные)
