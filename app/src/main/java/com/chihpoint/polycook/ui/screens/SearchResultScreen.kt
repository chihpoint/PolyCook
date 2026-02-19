package com.chihpoint.polycook.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chihpoint.polycook.ui.theme.AccentPink
import com.chihpoint.polycook.ui.theme.AppBackground
import com.chihpoint.polycook.ui.theme.ButtonBeige
import com.chihpoint.polycook.ui.theme.TextBlack
import com.chihpoint.polycook.ui.theme.TextWhite

@Composable
fun SearchResultScreen(
    onBackClick: () -> Unit,
    onNextRecipeClick: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ХЛЕБНЫЕ ЛЕПЕШКИ",
                color = ButtonBeige,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            RecipeSection(
                title = "Ингредиенты:",
                content = "Мука - 400-500 г, Вода горячая (70-80 градусов) - 250-300 мл, Соль - 1 ч. ложка"
            )

            Spacer(modifier = Modifier.height(16.dp))

            RecipeSection(
                title = "Рецепт:",
                content = "Замесить тесто на теплой воде так, чтобы оно было мягким. Для этого насыпать муку горкой, сделать в середине углубление. Понемногу добавляя в него теплую воду с добавлением соли, вымесить мягкое, эластичное тесто. Дать тесту полежать 10 минут. Разделать тесто на шарики размером с куриное яйцо. Затем шарики сплющить и придать форму толстых лепешек. Разогреть сковороду. Лепешки по-домашнему жарить на сковороде с ровным днищем. Как только лепешка начнет надуваться (через 2 минуты), перевернуть и жарить до готовности (еще 1,5 минуты). Домашние лепёшки готовы!"
            )

            Spacer(modifier = Modifier.height(32.dp))

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
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
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