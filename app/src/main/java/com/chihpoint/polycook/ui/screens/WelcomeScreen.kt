package com.chihpoint.polycook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.* // Тут все для отступов (padding, fillMaxSize)
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chihpoint.polycook.ui.theme.AppBackground
import com.chihpoint.polycook.ui.theme.ButtonBeige
import com.chihpoint.polycook.ui.theme.CartoonFont
import com.chihpoint.polycook.ui.theme.TextBlack
import com.chihpoint.polycook.ui.theme.TextWhite
import com.chihpoint.polycook.R
// Импортируем наши цвета (убедись, что они видны из шага 2)

@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.polycook_logo),
            contentDescription = "Логотип повара",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(300.dp)
                .offset(x = (-20).dp, y = (-60).dp)
        )

        Spacer(modifier = Modifier.height(0.dp))

        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = "PolyCook",
            color = ButtonBeige,
            fontSize = 70.sp,
            fontFamily = CartoonFont,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-80).dp)
        )

        Text(
            text = "Войдите в личный кабинет, чтобы\nсохранять рецепты",
            color = TextWhite,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBeige),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(56.dp)
                .offset(y = (-30).dp)
        ) {
            Text(
                text = "Войти",
                color = TextBlack,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onSkipClick,
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBeige),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(56.dp)
                .offset(y = (-30).dp)
        ) {
            Text(
                text = "Продолжить без входа",
                color = TextBlack,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(onLoginClick = {}, onSkipClick = {})
}