package mx.ma3r.tokyogoul

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import mx.ma3r.tokyogoul.model.Chapter
import mx.ma3r.tokyogoul.model.HomeFeature
import mx.ma3r.tokyogoul.model.Movie
import mx.ma3r.tokyogoul.navigation.Screen

const val DELAY_SPLASH_SCREEN = 2800L

val HOME_FEATURES = listOf(
    HomeFeature(R.string.aboutTokyoGhoul,R.drawable.baseline_movie_24,Screen.AboutTokyoGhoul),
    HomeFeature(R.string.aboutUs,R.drawable.baseline_engineering_24,Screen.AboutUs),
    HomeFeature(R.string.downloadedFiles,R.drawable.baseline_cloud_download_24,Screen.DownloadedFiles),
    HomeFeature(R.string.contactUs, R.drawable.baseline_phone_enabled_24,Screen.ContactUs),
    HomeFeature(R.string.donate, R.drawable.baseline_wifi_protected_setup_24,Screen.Donate),
//    HomeFeature(R.string.gallery,R.drawable.poster1,Screen.Gallery),
)

val movie = Movie(1, "S01E01", "https://...")
val CHAPTERS = listOf(
    Chapter(
        1,
        R.string.ch1,
        listOf(
            movie,
            movie.copy(name = "S01E02", id = 2, chatList = ArrayList()),
            movie.copy(name = "S01E03", id = 3, chatList = ArrayList()),
            movie.copy(name = "S01E04", id = 4, chatList = ArrayList()),
            movie.copy(name = "S01E05", id = 5, chatList = ArrayList()),
            movie.copy(name = "S01E06", id = 6, chatList = ArrayList()),
            movie.copy(name = "S01E07", id = 7, chatList = ArrayList()),
            movie.copy(name = "S01E08", id = 8, chatList = ArrayList()),
            movie.copy(name = "S01E09", id = 9, chatList = ArrayList()),
            movie.copy(name = "S01E010", id = 10, chatList = ArrayList()),
            movie.copy(name = "S01E011", id = 11, chatList = ArrayList()),
            movie.copy(name = "S01E012", id = 12, chatList = ArrayList()),
        )
    ),
    Chapter(2, R.string.ch2, listOf(movie, movie, movie, movie, movie, movie, movie)),
    Chapter(3, R.string.ch3, listOf(movie, movie, movie, movie, movie, movie, movie)),
    Chapter(4, R.string.ch4, listOf(movie, movie, movie, movie, movie, movie, movie)),
)

@Composable
fun Background(alpha: Float = 0.8f, img: Int = R.drawable.bg_home) {
    Image(
        painter = painterResource(id = img),
        contentDescription = "bg",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    Spacer(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background.copy(alpha = alpha))
            .fillMaxSize()
    )

}