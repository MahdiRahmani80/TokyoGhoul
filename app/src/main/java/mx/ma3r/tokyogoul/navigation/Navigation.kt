package mx.ma3r.tokyogoul.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.ma3r.tokyogoul.DELAY_SPLASH_SCREEN
import mx.ma3r.tokyogoul.screen.splash.SplashScreen

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Splash.route){

        composable(Screen.Splash.route){
            SplashScreen(navController, DELAY_SPLASH_SCREEN)
        }

        composable(Screen.Home.route){
        }
        composable(Screen.Setting.route){
        }
        composable(Screen.Gallery.route){
        }
        composable(Screen.Chapter.route){
        }
        composable(Screen.Movie.route){
        }
        composable(Screen.ChatSection.route){
        }
    }
}