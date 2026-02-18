package com.example.polycook

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polycook.ui.theme.*

@Composable
fun RecipeDetailsScreen(
    onBackClick: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(16.dp)
    ) {
        // Основной контент с прокруткой
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "ШОКОЛАДНОЕ ПЕЧЕНЬЕ С ЭСПРЕССО",
                color = ButtonBeige,
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 30.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            RecipeSection(
                title = "Ингредиенты:",
                content = "мука — 1 Стакан, какао — 2 Ст. ложки, соль — 0,2 Чайных ложки, соды — 1/2 Чайных ложки, яйца — 2 Штуки, ванильный сахар — 1 Чайная ложка, масло сливочное — 2 Ст. ложки, вода — 2 Ст. ложки, сахарный песок — 0,7 Стакана, большое яйцо — 1 Штука, шоколадные чипсы — 1 Стакан, разрыхлитель — 1 Чайная ложка, эспрессо — 0,3 Стакана, шоколад темный — 170 Грамм"
            )

            Spacer(modifier = Modifier.height(16.dp))

            RecipeSection(
                title = "Рецепт:",
                content = "Кладем в маленькую мисочку масло и шоколад. Ставим миску на паровую баню и помешивая, пока ждем пока все не растопится. Измельчаем в кухонном комбайне зерна эспрессо, покрытые шоколадом. Теперь смешайте: измельченные зерна эспрессо, муку, какао, разрыхлитель и соль. В отдельной емкости взбиваем сахар, яйца, воду и ванильный экстракт. Постепенно добавляя смесь сухих ингредиентов, продолжаем все взбивать до густой однородной массы. Затем добавляем растопленный шоколад и шоколадные чипсы, перемешиваем. Выкладываем тесто на заранее застеленный пергаментом противень. Выпекаем 20 минут при температуре 150 градусов.\nПриятного аппетита!"
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //  КНОПКА ЛАЙК
            IconButton(
                onClick = {
                    isFavorite = !isFavorite
                    if (isFavorite) {
                        println("Добавлено в избранное")
                    } else {
                        println("Удалено из избранного")
                    }
                },
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = "Вернуться",
                    color = TextWhite,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 12.dp)
                )

                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(50.dp)
                        .background(AccentPink, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextWhite
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeSection(title: String, content: String) {
    Column {
        Text(
            text = title,
            color = TextWhite,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(ButtonBeige)
                .padding(16.dp)
        ) {
            Text(
                text = content,
                color = TextBlack,
                fontSize = 16.sp,
                lineHeight = 20.sp
            )
        }
    }
}