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
    onResendClick: () -> Unit
) {
    var code by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(24.dp),
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
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

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
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
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
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text(text = "Письмо не пришло? ", color = TextGray)
            Text(
                text = "Отправить повторно",
                color = AccentPink,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onResendClick() }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun VerificationScreenPreview() {
    VerificationScreen({}, {})
}