package mx.ma3r.tokyogoul.screen.movie

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mx.ma3r.tokyogoul.model.Chapter
import mx.ma3r.tokyogoul.model.Chat
import mx.ma3r.tokyogoul.model.Movie
import java.text.SimpleDateFormat
import java.util.Date

class MovieViewModel : ViewModel() {


    private val _movie = MutableStateFlow(Movie(0,"",""))

    val chatList:StateFlow<List<Chat>> = _movie.map {movie->
        movie.chatList.reversed()
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun setMovie(movie: Movie) {
        viewModelScope.launch{
            _movie.emit(movie)
        }
    }

    fun addMessage(message: String) {
        val sdf = SimpleDateFormat("hh:mm")
        val currentDate = sdf.format(Date())

        val updateChatList = _movie.value.chatList.toMutableList()
        updateChatList.add(Chat(text = message, time = currentDate, owner = "M3hdi"))
        viewModelScope.launch {
            _movie.emit(_movie.value.copy(chatList = ArrayList(updateChatList)))
        }
    }


}