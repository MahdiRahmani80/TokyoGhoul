package mx.ma3r.tokyogoul.navigation

sealed class Screen(val route:String){

    object Splash:Screen("splash_screen")
    object Home:Screen("home_screen")
    object Setting:Screen("setting_screen")
    object ChatSection:Screen("chat_screen")
    object Movie:Screen("movie_screen")
    object Chapter:Screen("chapter_screen")
    object Gallery:Screen("gallery_screen")
}
