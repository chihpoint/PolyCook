package com.chihpoint.polycook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chihpoint.polycook.ui.theme.AccentPink
import com.chihpoint.polycook.ui.theme.AppBackground
import com.chihpoint.polycook.ui.theme.ButtonBeige
import com.chihpoint.polycook.ui.theme.TextBlack
import com.chihpoint.polycook.ui.theme.TextWhite
import com.chihpoint.polycook.R

@Composable
fun NoRecipeFoundScreen(
    onTryAgainClick: () -> Unit,      // Кнопка "Ввести другие продукты"
    onBackToMenuClick: () -> Unit     // Кнопка "Вернуться в главное меню"
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(16.dp)
    ) {
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
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Вернуться в главное меню",
                color = TextWhite,
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 12.dp)
            )

            IconButton(
                onClick = onBackToMenuClick,
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