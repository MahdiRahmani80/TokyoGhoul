package mx.ma3r.tokyogoul.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import mx.ma3r.tokyogoul.CHAPTERS
import mx.ma3r.tokyogoul.R
import mx.ma3r.tokyogoul.model.Chapter

class HomeViewModel : ViewModel() {

    val chapters: MutableState<List<Chapter>> by lazy {
        mutableStateOf(CHAPTERS)
    }
}