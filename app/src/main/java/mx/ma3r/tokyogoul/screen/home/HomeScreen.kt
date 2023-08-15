package mx.ma3r.tokyogoul.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.ma3r.tokyogoul.Background
import mx.ma3r.tokyogoul.R
import mx.ma3r.tokyogoul.navigation.Screen
import mx.ma3r.tokyogoul.navigation.SharedViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, share: SharedViewModel) {

    val homeViewModel = koinViewModel<HomeViewModel>()

    Scaffold(topBar = { TopBar() }) { paddingValues ->

        Background()
        LazyCol(navController, homeViewModel, share, paddingValues)

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
fun LazyCol(
    navController: NavController,
    vm: HomeViewModel,
    share: SharedViewModel,
    paddingValues: PaddingValues
) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 5.dp)
    ) {

        item {
            LastVideoPlayed(paddingValues, navController, vm, share)
        }

        item {
            Spacer(modifier = Modifier.height(15.dp))
            HomeFeatures(vm, onClick = { screen ->
                navController.navigate(screen.route)
            })
        }


        item {
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(id = R.string.chapter),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(20.dp, 0.dp),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

        }

        items(vm.chapters.value.size) { index ->

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
                onClick = {

                    share.setChapter(vm.chapters.value[index])
                    navController.navigate(Screen.Chapter.route)
                }) {

                Text(
                    text = stringResource(vm.chapters.value[index].name),
                    color = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier.padding(12.dp, 8.dp)
                )
            }
        }
    }

}


@Composable
fun LastVideoPlayed(
    paddingValues: PaddingValues,
    navController: NavController,
    homeViewModel: HomeViewModel,
    share: SharedViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues.apply { 10.dp })
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .padding(15.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.tertiary),
            contentAlignment = Alignment.BottomStart
        ) {

            Background(img=R.drawable.home_video_bg, alpha = 0.4f)

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

    }
}

@Composable
fun HomeFeatures(vm: HomeViewModel, onClick: (Screen) -> Unit) {

    val homeFeatureList by vm.homeFeature.collectAsState(initial = emptyList())

    LazyRow() {
        items(homeFeatureList.size) { index ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(5.dp, 5.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.secondary)
                        .clickable { onClick(homeFeatureList[index].screen) },
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        painter = painterResource(homeFeatureList[index].icon),
                        contentDescription = "item icon",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Text(
                    text = stringResource(homeFeatureList[index].title),
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 9.sp
                    ),
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
            }
        }
    }
}