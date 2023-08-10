package mx.ma3r.tokyogoul.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import mx.ma3r.tokyogoul.CHAPTERS
import mx.ma3r.tokyogoul.model.Chapter

class SharedViewModel():ViewModel() {


    var chapter by mutableStateOf<Chapter>(CHAPTERS[0])
        private set

    fun setChapter(ch:Chapter){
        chapter = ch
    }
}