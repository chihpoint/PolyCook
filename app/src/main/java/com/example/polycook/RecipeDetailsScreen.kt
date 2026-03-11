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
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polycook.ui.theme.*

@Composable
fun RecipeDetailsScreen(
    onBackClick: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }

    // Используем Column как основной контейнер, чтобы распределить вес
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

        // --- ЦЕНТРАЛЬНАЯ ЧАСТЬ (Занимает все свободное место) ---
        Column(
            modifier = Modifier
                .weight(1f) // Растягиваем эту колонку между заголовком и кнопками
                .fillMaxWidth()
        ) {
            // Ингредиенты (40% высоты)
            ScrollableRecipeSection(
                title = "Ингредиенты:",
                content = "мука — 1 Стакан, какао — 2 Ст. ложки, соль — 0,2 Чайных ложки, соды — 1/2 Чайных ложки, яйца — 2 Штуки, ванильный сахар — 1 Чайная ложка, масло сливочное — 2 Ст. ложки, вода — 2 Ст. ложки, сахарный песок — 0,7 Стакана, большое яйцо — 1 Штука, шоколадные чипсы — 1 Стакан, разрыхлитель — 1 Чайная ложка, эспрессо — 0,3 Стакана, шоколад темный — 170 Грамм",
                modifier = Modifier.weight(0.4f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Рецепт (60% высоты)
            ScrollableRecipeSection(
                title = "Рецепт:",
                content = "Кладем в маленькую мисочку масло и шоколад. Ставим миску на паровую баню и помешивая, пока ждем пока все не растопится. Измельчаем в кухонном комбайне зерна эспрессо, покрытые шоколадом. Теперь смешайте: измельченные зерна эспрессо, муку, какао, разрыхлитель и соль. В отдельной емкости взбиваем сахар, яйца, воду и ванильный экстракт. Постепенно добавляя смесь сухих ингредиентов, продолжаем все взбивать до густой однородной массы. Затем добавляем растопленный шоколад и шоколадные чипсы, перемешиваем. Выкладываем тесто на заранее застеленный пергаментом противень. Выпекаем 20 минут при температуре 150 градусов.\nПриятного аппетита!",
                modifier = Modifier.weight(0.6f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- НИЖНЯЯ ПАНЕЛЬ С КНОПКАМИ ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // КНОПКА ЛАЙК (Слева)
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

            // КНОПКА ВЕРНУТЬСЯ (Справа)
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

// Переиспользуемый компонент со скроллом
