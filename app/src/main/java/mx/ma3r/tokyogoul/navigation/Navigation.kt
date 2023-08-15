package mx.ma3r.tokyogoul.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.ma3r.tokyogoul.DELAY_SPLASH_SCREEN
import mx.ma3r.tokyogoul.R
import mx.ma3r.tokyogoul.persentation.signIn.GoogleAuthUiClient
import mx.ma3r.tokyogoul.persentation.signIn.SignInViewModel
import mx.ma3r.tokyogoul.screen.chapter.ChapterScreen
import mx.ma3r.tokyogoul.screen.home.HomeScreen
import mx.ma3r.tokyogoul.screen.movie.MovieSingleScreen
import mx.ma3r.tokyogoul.screen.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(
    googleAuthUiClient: GoogleAuthUiClient,
    signIn: () -> Unit
) {

    val sharedViewModel = koinViewModel<SharedViewModel>()
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(navController, DELAY_SPLASH_SCREEN)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController, sharedViewModel)
        }
        composable(Screen.Setting.route) {
        }
        composable(Screen.Gallery.route) {
        }
        composable(Screen.Chapter.route) {
            ChapterScreen(navController, sharedViewModel.currentChapter, sharedViewModel)
        }
        composable(Screen.Movie.route) {
            val viewModel = koinViewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val context = LocalContext.current

//            LaunchedEffect(key1 = state.isSignInSuccessful){
            if (state.isSignInSuccessful) {
//                    Log.d("LOGIN_IS_SUCCESSFUL",state.isSignInSuccessful.toString())
                Toast.makeText(
                    context,
                    context.getText(R.string.login_successful),
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.resetState()
            }
//            }

            MovieSingleScreen(
                navController,
                sharedViewModel.currentChapter,
                sharedViewModel,
                state,
                googleAuthUiClient.getSignedInUser(),
                onSignInClick = {
                    signIn()
                })
        }
        composable(Screen.ChatSection.route) {
        }
    }
}
