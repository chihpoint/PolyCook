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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polycook.ui.theme.*

@Composable
fun RandomRecipeScreen(
    onBackClick: () -> Unit,
    onNextRandomClick: () -> Unit
) {
    // Состояние лайка
    var isFavorite by remember { mutableStateOf(false) }

    // Основной контейнер (без скролла)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- ЗАГОЛОВОК ---
        Text(
            text = "ШОКОЛАДНОЕ ПЕЧЕНЬЕ С ЭСПРЕССО",
            color = ButtonBeige,
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 30.sp,
            fontFamily = CartoonFont,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // --- ЦЕНТРАЛЬНАЯ ЧАСТЬ (Занимает всё доступное место) ---
        Column(
            modifier = Modifier
                .weight(1f) // Занимает всё место между заголовком и кнопками
                .fillMaxWidth()
        ) {
            // Ингредиенты (занимают 40% свободного места)
            ScrollableRecipeSection(
                title = "Ингредиенты:",
                content = "мука — 1 Стакан, какао — 2 Ст. ложки, соль — 0,2 Чайных ложки, соды — 1/2 Чайных ложки, яйца — 2 Штуки, ванильный сахар — 1 Чайная ложка, масло сливочное — 2 Ст. ложки, вода — 2 Ст. ложки, сахарный песок — 0,7 Стакана, большое яйцо — 1 Штука, шоколадные чипсы — 1 Стакан, разрыхлитель — 1 Чайная ложка, эспрессо — 0,3 Стакана, шоколад темный — 170 Грамм",
                modifier = Modifier.weight(0.4f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Рецепт (занимает 60% свободного места)
            ScrollableRecipeSection(
                title = "Рецепт:",
                content = "Кладем в маленькую мисочку масло и шоколад. Ставим миску на паровую баню и помешивая, пока ждем пока все не растопится. Измельчаем в кухонном комбайне зерна эспрессо, покрытые шоколадом. Теперь смешайте: измельченные зерна эспрессо, муку, какао, разрыхлитель и соль. В отдельной емкости взбиваем сахар, яйца, воду и ванильный экстракт. Постепенно добавляя смесь сухих ингредиентов, продолжаем все взбивать до густой однородной массы. Затем добавляем растопленный шоколад и шоколадные чипсы, перемешиваем. Выкладываем тесто на заранее застеленный пергаментом противень. Выпекаем 20 минут при температуре 150 градусов.\nПриятного аппетита!",
                modifier = Modifier.weight(0.6f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- КНОПКА "ДРУГОЙ РЕЦЕПТ" ---
        Button(
            onClick = {
                isFavorite = false
                onNextRandomClick()
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

        Spacer(modifier = Modifier.height(20.dp))

        // --- НИЖНЯЯ ПАНЕЛЬ (ЛАЙК И КНОПКА НАЗАД) ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Лайк
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

            // Кнопка Вернуться
            Row(
                modifier = Modifier
                    .clickable { onBackClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Вернуться",
                    color = TextWhite,
                    fontSize = 18.sp,
                    fontFamily = DefaultFont,
                    modifier = Modifier.padding(end = 12.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.strelka),
                    contentDescription = "Back",
                    modifier = Modifier.size(48.dp) // Чуть уменьшил, чтобы аккуратнее смотрелось в строке
                )
            }
        }
    }
}

// Вспомогательный компонент для скроллящихся секций
@Composable
fun ScrollableRecipeSection(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            color = ButtonBeige, // Цвет заголовка секции (как и общий заголовок)
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = DefaultFont
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Box с внутренним скроллом
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight() // Заполняет отведенный вес
                // ИЗМЕНЕНИЕ 1: ФОН БЕЖЕВЫЙ
                .background(ButtonBeige, RoundedCornerShape(12.dp))
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = content,
                // ИЗМЕНЕНИЕ 2: ТЕКСТ ЧЕРНЫЙ
                color = TextBlack,
                fontSize = 16.sp,
                fontFamily = DefaultFont,
                lineHeight = 22.sp
            )
        }
    }
}