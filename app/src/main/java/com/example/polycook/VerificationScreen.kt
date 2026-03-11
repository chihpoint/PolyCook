package com.example.polycook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polycook.ui.theme.*

@Composable
fun VerificationScreen(
    onVerifyClick: () -> Unit,
    onResendClick: () -> Unit,
    onBackClick: () -> Unit // 1. <--- НОВЫЙ ПАРАМЕТР
) {
    var code by remember { mutableStateOf("") }

    // 2. <--- Box ДЛЯ СЛОЕВ
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .offset(y = (-20).dp), // 3. <--- СДВИГ ВВЕРХ НА 20dp
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- Логотип ---
            Image(
                painter = painterResource(id = R.drawable.polycook_logo),
                contentDescription = "Логотип повара",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(300.dp)
                    .offset(x = (-20).dp, y = (-20).dp)
            )

            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "PolyCook",
                color = ButtonBeige,
                fontSize = 70.sp,
                fontFamily = CartoonFont,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-40).dp)
            )

            Spacer(modifier = Modifier.height(1.dp))

            Text(
                text = "На Вашу почту po******@ya****.ru\nотправлено письмо с кодом\nподтверждения. Введите его ниже",
                color = TextWhite,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontFamily = DefaultFont,
                lineHeight = 24.sp,
                modifier = Modifier.offset(y = (-20).dp) // Чуть подняли текст
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Поле ввода кода
            BasicTextField(
                value = code,
                onValueChange = {
                    if (it.length <= 5 && it.all { char -> char.isDigit() }) {
                        code = it
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                decorationBox = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(5) { index ->
                            val char = code.getOrNull(index)?.toString() ?: ""
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .background(ButtonBeige, RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = char,
                                    color = TextBlack,
                                    fontSize = 24.sp,
                                    fontFamily = DefaultFont,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                },
                modifier = Modifier.offset(y = (-20).dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onVerifyClick,
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBeige),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Продолжить",
                    color = TextBlack,
                    fontSize = 18.sp,
                    fontFamily = DefaultFont,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Text(text = "Письмо не пришло? ", fontFamily = DefaultFont, color = TextGray)
                Text(
                    text = "Отправить повторно",
                    color = AccentPink,
                    fontWeight = FontWeight.Bold,
                    fontFamily = DefaultFont,
                    modifier = Modifier.clickable { onResendClick() }
                )
            }
            // Дополнительный отступ снизу, чтобы текст не наезжал на кнопку "Вернуться"
            Spacer(modifier = Modifier.height(60.dp))
        }

        // 4. <--- КНОПКА "ВЕРНУТЬСЯ"
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 24.dp, end = 24.dp)
        ) {
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
                    fontFamily = DefaultFont,
                    fontSize = 18.sp
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

@Preview(showBackground = true)
@Composable
fun VerificationScreenPreview() {
    VerificationScreen({}, {}, {})
}