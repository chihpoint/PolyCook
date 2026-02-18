package com.example.polycook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polycook.ui.theme.*

@Composable
fun SavedRecipesScreen(
    onBackClick: () -> Unit,
    onRecipeClick: (String) -> Unit
) {
    val recipes = listOf(
        "Шоколадное печенье с эспрессо",
        "Латук с сальсой и лососем",
        "Тыквенное смузи с апельсином",
        "Гусь в духовке"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 1. Заголовок
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Сохраненные рецепты",
            color = ButtonBeige,
            fontSize = 45.sp,
            fontFamily = CartoonFont,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // СПИСОК РЕЦЕПТОВ
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(recipes) { recipeName ->
                SavedRecipeCard(
                    text = recipeName,
                    onClick = { onRecipeClick(recipeName) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //  Кнопка ВЕРНУТЬСЯ
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
                        imageVector = Icons.Default.ArrowBack, // Стрелка
                        contentDescription = "Назад",
                        tint = TextWhite,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedRecipeCard(
    text: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = ButtonBeige
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                color = TextBlack,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = AccentPink,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SavedRecipesScreenPreview() {
    SavedRecipesScreen({}, {})
}