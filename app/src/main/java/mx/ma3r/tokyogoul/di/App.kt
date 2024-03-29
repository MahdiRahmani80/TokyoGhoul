package mx.ma3r.tokyogoul.di

import android.app.Application
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.ma3r.tokyogoul.MainViewModel
import mx.ma3r.tokyogoul.navigation.SharedViewModel
import mx.ma3r.tokyogoul.persentation.signIn.SignInViewModel
import mx.ma3r.tokyogoul.screen.home.HomeViewModel
import mx.ma3r.tokyogoul.screen.movie.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App:Application() {

    override fun onCreate() {
        super.onCreate()

        val myModules = module {

            viewModel{ HomeViewModel()}
            viewModel { MovieViewModel() }
            single { SignInViewModel() }
            single{ SharedViewModel()}
        }

        startKoin {
            printLogger()
            androidContext(this@App)
            modules(myModules)
        }
    }
}