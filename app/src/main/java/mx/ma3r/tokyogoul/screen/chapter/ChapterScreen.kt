package mx.ma3r.tokyogoul.screen.chapter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import mx.ma3r.tokyogoul.Background
import mx.ma3r.tokyogoul.R
import mx.ma3r.tokyogoul.model.Chapter
import mx.ma3r.tokyogoul.navigation.Screen
import mx.ma3r.tokyogoul.navigation.SharedViewModel
import mx.ma3r.tokyogoul.screen.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterScreen(navController: NavHostController, chapter: Chapter, share: SharedViewModel) {

    val chapterName = stringResource(chapter.name)

    Scaffold(topBar = {

        Text(
            text = chapterName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            style = MaterialTheme.typography.titleLarge
        )

    }) { paddingValues ->

        Background()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues.apply { 1.dp })
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .padding(15.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colorScheme.tertiary),
                contentAlignment = Alignment.BottomStart
            ) {

                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxSize()) {
                    Icon(
                        painterResource(id = R.drawable.baseline_play_circle_outline_24),
                        "play",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.size(60.dp)
                    )
                }

                Text(
                    text = stringResource(id = R.string.trailer) + " $chapterName",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(12.dp, 5.dp),
                    style = MaterialTheme.typography.titleSmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f)
                )

            }

            MovieLazyCol(navController, share)
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieLazyCol(
    navController: NavController,
    share: SharedViewModel,
) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 5.dp)
    ) {
        items(share.currentChapter.episodes.size) { index ->

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
                onClick = {

                    share.setMovie(share.currentChapter.episodes[index])
                    navController.navigate(Screen.Movie.route)
                }) {

                Text(
                    text = share.currentChapter.episodes[index].name,
                    color = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier.padding(12.dp, 8.dp)
                )
            }
        }
    }

}