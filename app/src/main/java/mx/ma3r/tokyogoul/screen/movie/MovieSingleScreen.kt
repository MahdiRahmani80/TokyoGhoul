package mx.ma3r.tokyogoul.screen.movie

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mx.ma3r.tokyogoul.Background
import mx.ma3r.tokyogoul.R
import mx.ma3r.tokyogoul.model.Chapter
import mx.ma3r.tokyogoul.model.Chat
import mx.ma3r.tokyogoul.model.Movie
import mx.ma3r.tokyogoul.navigation.Screen
import mx.ma3r.tokyogoul.navigation.SharedViewModel
import mx.ma3r.tokyogoul.screen.home.TopBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSingleScreen(
    navController: NavController,
    chapter: Chapter,
    share: SharedViewModel
) {

    val movieViewModel = koinViewModel<MovieViewModel>()

    movieViewModel.setMovie(share.currentMovie)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(topBar = { TopBar() }) { paddingValues ->

        Background()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues.apply { 10.dp })
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
                    text = share.currentMovie.name,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(12.dp, 5.dp),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f)
                )

            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 0.dp)
            ) {

                if (share.currentMovie.id != 1)
                    Text(
                        text = stringResource(id = R.string.lastEpisode),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            share.setMovie(chapter.episodes[share.currentMovie.id - 2])
                            movieViewModel.setMovie(share.currentMovie)
                        })

                if (share.currentMovie.id != chapter.episodes.size)
                    Text(
                        text = stringResource(id = R.string.nextEpisode),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            share.setMovie(chapter.episodes[share.currentMovie.id])
                            movieViewModel.setMovie(share.currentMovie)
                        }
                    )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.chatSection),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(20.dp, 0.dp),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            ChatSection(share.currentMovie, movieViewModel, share)

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ChatSection(movie: Movie, vm: MovieViewModel, share: SharedViewModel) {

    var value by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
//    val keyboardController = LocalSoftwareKeyboardController.current

    val messageList by vm.chatList.collectAsState(share.currentMovie.chatList)


    Box(Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {

            LazyColumn(
                reverseLayout = true,
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {

                item() {

                    Box(
                        contentAlignment = Alignment.BottomCenter, modifier = Modifier
                            .padding(15.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.secondary,
                                RoundedCornerShape(10.dp)
                            )
                    ) {

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = value,
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                textDirection = TextDirection.ContentOrRtl
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = MaterialTheme.colorScheme.onSecondary,
                                containerColor = MaterialTheme.colorScheme.background,
                                unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                                unfocusedLabelColor = MaterialTheme.colorScheme.tertiary,
                                unfocusedLeadingIconColor = MaterialTheme.colorScheme.tertiary,
                                unfocusedSupportingTextColor = MaterialTheme.colorScheme.tertiary,
                            ),
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        value = value.trim()
                                        if (value != "") {
                                            vm.addMessage(value)
                                            value = ""
                                        }
//                                        keyboardController!!.hide()
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_send_24),
                                        contentDescription = "send",
                                        tint = MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                            },
                            onValueChange = { value = it },
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.commentPlaceHolder),
                                    color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f)
                                )
                            })
                    }

                }

                items(messageList.size) { index ->
                    ShowChats(messageList[index])
                }
            }
        }


    }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {
        IconButton(
            onClick = {
                coroutineScope.launch {
                    listState.animateScrollToItem(0)
                }
            },
            modifier = Modifier.padding(bottom = 50.dp, start = 20.dp, end = 20.dp)
        ) {

            if (listState.firstVisibleItemIndex > 0) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_downward_24),
                    contentDescription = "down",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
                        .padding(8.dp),
                    tint = MaterialTheme.colorScheme.onSecondary,
                )
            }
        }
    }
}

@Composable
fun ShowChats(chat: Chat) {

    MessageCard(chat) {}
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MessageCard(chat: Chat, onClick: () -> Unit) {

    val clipboardManager: ClipboardManager = LocalClipboardManager.current

//    todo-> change rounded corner and card when user is the owner of the comment
    Row(modifier = Modifier.padding(1.dp, 0.dp)) {
        Text(
            text = chat.owner,
            modifier = Modifier
                .padding(25.dp, 0.dp),
            color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }


    Card(
        modifier = Modifier
            .padding(start = 25.dp, bottom = 1.dp, end = 80.dp, top = 8.dp)
            .wrapContentWidth()
            .combinedClickable(
                onClick={},
                onLongClick = {
                    clipboardManager.setText(AnnotatedString(chat.text))
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
            contentColor = MaterialTheme.colorScheme.onSecondary,
        ),
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
            bottomEnd = 10.dp,
            bottomStart = 0.dp
        )
    ) {

        Column(horizontalAlignment = Alignment.End) {

            Text(
                text = chat.text,
                style = MaterialTheme.typography.bodySmall.copy(
                    textAlign = TextAlign.Justify,
                    textDirection = TextDirection.ContentOrRtl
                ),
                modifier = Modifier.padding(16.dp, 5.dp)
            )

            Row(
                modifier = Modifier
                    .padding(6.dp, 0.dp)
                    .wrapContentWidth(), horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = chat.time,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 6.sp)
                )
            }
        }
    }
}
