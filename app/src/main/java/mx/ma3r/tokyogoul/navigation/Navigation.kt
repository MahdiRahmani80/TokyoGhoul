package mx.ma3r.tokyogoul.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.ma3r.tokyogoul.screen.splash.SplashScreen

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Splash.route){

        composable(Screen.Splash.route){
            SplashScreen(navController)
        }
    }
}