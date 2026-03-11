package com.example.polycook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polycook.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchByIngredientsScreen(
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    var ingredients by remember { mutableStateOf("") }
    var allowExtraIngredients by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Поиск по продуктам",
                color = ButtonBeige,
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                fontFamily = CartoonFont,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                lineHeight = 40.sp
            )

            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "Введите продукты, которые у Вас есть:",
                color = TextWhite,
                fontSize = 21.sp,
                fontFamily = DefaultFont,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            TextField(
                value = ingredients,
                onValueChange = { ingredients = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = TextWhite, fontSize = 18.sp),
                placeholder = {
                    Text(
                        text = "Вода, мука, соль",
                        color = TextGray,
                        fontFamily = DefaultFont,
                        fontSize = 18.sp
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = AccentPink,
                    focusedIndicatorColor = AccentPink,
                    unfocusedIndicatorColor = AccentPink,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Red
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- КАСТОМНЫЙ ЧЕКБОКС ---
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { allowExtraIngredients = !allowExtraIngredients }
            ) {
                // Вместо Checkbox используем Box
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .border(
                            width = 2.dp,
                            color = if (allowExtraIngredients) AccentPink else TextWhite,
                            shape = RoundedCornerShape(8.dp) // Скругление углов рамки
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (allowExtraIngredients) {
                        // Красный (розовый) квадрат внутри
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .background(AccentPink, RoundedCornerShape(4.dp))
                        )
                    }
                }

                Text(
                    text = "PolyCook может добавлять другие ингредиенты",
                    color = TextWhite,
                    fontSize = 16.sp,
                    fontFamily = DefaultFont,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
            // -------------------------

            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = onSearchClick,
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBeige),
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Найти рецепт",
                    color = TextBlack,
                    fontSize = 20.sp,
                    fontFamily = DefaultFont,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // --- КНОПКА "ВЕРНУТЬСЯ" С КАРТИНКОЙ ---
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onBackClick() } // Кликабельность на всю строку
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
                    painter = painterResource(id = R.drawable.strelka), // Ваша картинка
                    contentDescription = "Вернуться",
                    modifier = Modifier.height(50.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}