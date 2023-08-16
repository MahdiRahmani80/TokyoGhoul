package mx.ma3r.tokyogoul

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import mx.ma3r.tokyogoul.navigation.Navigation
import mx.ma3r.tokyogoul.persentation.signIn.GoogleAuthUiClient
import mx.ma3r.tokyogoul.persentation.signIn.SignInViewModel
import mx.ma3r.tokyogoul.ui.theme.TokyoGoulTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    private lateinit var analytics: FirebaseAnalytics

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        analytics = Firebase.analytics

        setContent {
            TokyoGoulTheme {
                HideStatusBar()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val signInViewModel = koinViewModel<SignInViewModel>()
                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result ->
                            if (result.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResult = googleAuthUiClient.signInWithIntent(
                                        intent = result.data ?: return@launch
                                    )
                                    signInViewModel.onSignInResult(signInResult)
                                }
                            }
                        }
                    )

                    Navigation(googleAuthUiClient, signIn = {
                        lifecycleScope.launch {
                            val signInIntentSender = googleAuthUiClient.signIn()
                            launcher.launch(
                                IntentSenderRequest.Builder(
                                    signInIntentSender ?: return@launch
                                ).build()
                            )
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun HideStatusBar() {
    val context = LocalContext.current
    val activity = context as ComponentActivity
    val window = activity.window

    // Hide the status bar using WindowInsetsController
//    val windowInsetsController = remember { WindowCompat.getInsetsController(window, activity.window.decorView.rootView) }
//    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//    windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TokyoGoulTheme {

    }
}