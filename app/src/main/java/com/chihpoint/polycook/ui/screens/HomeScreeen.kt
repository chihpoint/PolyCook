package com.chihpoint.polycook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chihpoint.polycook.ui.theme.AccentPink
import com.chihpoint.polycook.ui.theme.AppBackground
import com.chihpoint.polycook.ui.theme.ButtonBeige
import com.chihpoint.polycook.ui.theme.TextBlack
import com.chihpoint.polycook.R

@Composable
fun HomeScreen(
    onSearchClick: () -> Unit,
    onRandomClick: () -> Unit,
    onSavedClick: () -> Unit,
    onInstructionClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(14.dp))

        // Верхний заголовок
        Image(
            painter = painterResource(id = R.drawable.img_home_header),
            contentDescription = "Заголовок",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )

        Spacer(modifier = Modifier.height(0.dp))
        // Кнопки меню
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
            modifier = Modifier.clickable { onInstructionClick() }
        )

        Spacer(modifier = Modifier.height(78.dp))
    }
}
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
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen({}, {}, {}, {})
}