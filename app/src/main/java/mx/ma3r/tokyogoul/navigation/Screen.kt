package mx.ma3r.tokyogoul.navigation

sealed class Screen(val route:String){

    object Splash:Screen("splash_screen")
    object Home:Screen("home_screen")
    object Setting:Screen("setting_screen")
    object ChatSection:Screen("chat_screen")
    object Movie:Screen("movie_screen")
    object Chapter:Screen("chapter_screen")
    object Gallery:Screen("gallery_screen")
    object AboutTokyoGhoul:Screen("about_tokyo_ghoul_screen")
    object AboutUs:Screen("about_screen")
    object DownloadedFiles:Screen("downloaded_screen")
    object ContactUs:Screen("contact_screen")
    object Donate:Screen("donate_screen")
}
