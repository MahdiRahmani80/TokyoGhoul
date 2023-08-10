package mx.ma3r.tokyogoul.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.ma3r.tokyogoul.DELAY_SPLASH_SCREEN
import mx.ma3r.tokyogoul.model.Chapter
import mx.ma3r.tokyogoul.screen.home.HomeScreen
import mx.ma3r.tokyogoul.screen.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation() {

    val sharedViewModel = koinViewModel<SharedViewModel>()
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(navController, DELAY_SPLASH_SCREEN)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController,sharedViewModel)
        }
        composable(Screen.Setting.route) {
        }
        composable(Screen.Gallery.route) {
        }
        composable(Screen.Chapter.route) {
        }
        composable(Screen.Movie.route) {
        }
        composable(Screen.ChatSection.route) {
        }
    }
}
