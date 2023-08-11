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
import mx.ma3r.tokyogoul.model.Movie

const val DELAY_SPLASH_SCREEN = 2800L

val movie = Movie(1, "S01E01", "https://...")
val CHAPTERS = listOf(
    Chapter(
        1,
        R.string.ch1,
        listOf(
            movie,
            movie.copy(name = "S01E02", id = 2),
            movie.copy(name = "S01E03", id = 3),
            movie.copy(name = "S01E04", id = 4),
            movie.copy(name = "S01E05", id = 5),
            movie.copy(name = "S01E06", id = 6),
            movie.copy(name = "S01E07", id = 7),
            movie.copy(name = "S01E08", id = 8),
            movie.copy(name = "S01E09", id = 9),
            movie.copy(name = "S01E010", id = 10),
            movie.copy(name = "S01E011", id = 11),
            movie.copy(name = "S01E012", id = 12),
        )
    ),
    Chapter(2, R.string.ch2, listOf(movie, movie, movie, movie, movie, movie, movie)),
    Chapter(3, R.string.ch3, listOf(movie, movie, movie, movie, movie, movie, movie)),
    Chapter(4, R.string.ch4, listOf(movie, movie, movie, movie, movie, movie, movie)),
)

@Composable
fun Background(){
    Image(
        painter = painterResource(id = R.drawable.bg_home),
        contentDescription = "bg",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    Spacer(modifier = Modifier.background(MaterialTheme.colorScheme.background.copy(0.8f)).fillMaxSize())

}