package com.chihpoint.polycook.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chihpoint.polycook.ui.theme.AccentPink
import com.chihpoint.polycook.ui.theme.AppBackground
import com.chihpoint.polycook.ui.theme.ButtonBeige
import com.chihpoint.polycook.ui.theme.TextBlack
import com.chihpoint.polycook.ui.theme.TextGray
import com.chihpoint.polycook.ui.theme.TextWhite

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
                text = "ПОИСК ПО ПРОДУКТАМ",
                color = ButtonBeige,
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                lineHeight = 40.sp
            )

            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "Введите продукты, которые у Вас есть:",
                color = TextWhite,
                fontSize = 20.sp,
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { allowExtraIngredients = !allowExtraIngredients }
            ) {
                Checkbox(
                    checked = allowExtraIngredients,
                    onCheckedChange = { allowExtraIngredients = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = AccentPink,
                        uncheckedColor = TextWhite,
                        checkmarkColor = TextWhite
                    )
                )
                Text(
                    text = "PolyCook может добавлять другие ингредиенты",
                    color = TextWhite,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

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