package mx.ma3r.tokyogoul.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import mx.ma3r.tokyogoul.R
import mx.ma3r.tokyogoul.model.Chapter
import mx.ma3r.tokyogoul.navigation.Screen
import mx.ma3r.tokyogoul.navigation.SharedViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, share: SharedViewModel) {

    val homeViewModel = koinViewModel<HomeViewModel>()

    Scaffold(topBar = { TopBar() }) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues.apply { 10.dp })) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
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
                    text = stringResource(id = R.string.lastWatch),
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(12.dp, 5.dp),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f)
                )

            }


            Text(
                text = stringResource(id = R.string.chapter),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(20.dp, 0.dp),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            ChaptersLazyCol(navController, homeViewModel.chapters.value){chapter->
                share.setChapter(chapter)
                navController.navigate(Screen.Chapter.route)
            }

        }

    }
}

@Composable
fun TopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(15.dp)
    ) {

        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge
        )

        IconButton(onClick = { /*TODO CaffeBazaar intent: Star app */ }) {
            Icon(
                painterResource(id = R.drawable.baseline_star_24),
                contentDescription = "star",
                tint = Color(0xFFFFC107)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChaptersLazyCol(
    navController: NavController,
    chapters: List<Chapter>,
    itemClick: (ch: Chapter) -> Unit
) {

    LazyColumn(modifier = Modifier.padding(10.dp, 5.dp)) {
        items(chapters.size) { index ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
                onClick = {
                    itemClick(chapters[index])
                }
            ) {

                Text(
                    text = stringResource(chapters[index].name),
                    color = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier.padding(12.dp, 8.dp)
                )
            }
        }
    }

}