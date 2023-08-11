package mx.ma3r.tokyogoul.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import mx.ma3r.tokyogoul.CHAPTERS
import mx.ma3r.tokyogoul.model.Chapter
import mx.ma3r.tokyogoul.model.Movie
import mx.ma3r.tokyogoul.movie

class SharedViewModel():ViewModel() {


    var currentChapter by mutableStateOf<Chapter>(CHAPTERS[0])
        private set

    fun setChapter(ch: Chapter) {
        currentChapter = ch
    }

    var currentMovie by mutableStateOf<Movie>(movie)
        private set

    fun setMovie(m:Movie){
        currentMovie = m
    }
}