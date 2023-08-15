package mx.ma3r.tokyogoul.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.common.Feature
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import mx.ma3r.tokyogoul.CHAPTERS
import mx.ma3r.tokyogoul.HOME_FEATURES
import mx.ma3r.tokyogoul.R
import mx.ma3r.tokyogoul.model.Chapter
import mx.ma3r.tokyogoul.model.HomeFeature
import mx.ma3r.tokyogoul.navigation.Screen

class HomeViewModel : ViewModel() {


    val chapters: MutableState<List<Chapter>> by lazy {
        mutableStateOf(CHAPTERS)
    }

    private val _homeFeatures =
        MutableStateFlow<List<HomeFeature>>(HOME_FEATURES)

    val homeFeature = _homeFeatures.asStateFlow()

}