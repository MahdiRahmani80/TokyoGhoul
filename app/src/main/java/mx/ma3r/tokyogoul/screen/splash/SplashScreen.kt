package mx.ma3r.tokyogoul.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import mx.ma3r.tokyogoul.R
import mx.ma3r.tokyogoul.navigation.Screen

@Composable
fun SplashScreen(navController: NavController, delaySplash: Long = 2500L) {

    LaunchedEffect(true) {
        delay(delaySplash)
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }

    Image(
        painter = painterResource(id = R.drawable.bg),
        contentDescription = "splash bg",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.12f)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.background,
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "توکیو غول ",
                style = MaterialTheme.typography.headlineMedium,
            )
        }
    }
}