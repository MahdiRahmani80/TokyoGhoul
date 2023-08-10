package mx.ma3r.tokyogoul

import mx.ma3r.tokyogoul.model.Chapter
import mx.ma3r.tokyogoul.model.Movie

const val DELAY_SPLASH_SCREEN = 2800L

val movie = Movie("S01E01","https://...")
val CHAPTERS = listOf(
    Chapter(R.string.ch1, listOf(movie, movie, movie, movie, movie, movie, movie)),
    Chapter(R.string.ch2,listOf(movie, movie, movie, movie, movie, movie, movie)),
    Chapter(R.string.ch3,listOf(movie, movie, movie, movie, movie, movie, movie)),
    Chapter(R.string.ch4,listOf(movie, movie, movie, movie, movie, movie, movie)),
)