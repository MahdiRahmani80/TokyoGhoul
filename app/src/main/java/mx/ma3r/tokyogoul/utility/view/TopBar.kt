package mx.ma3r.tokyogoul.utility.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mx.ma3r.tokyogoul.R


@Composable
fun TopBar(title: String = LocalContext.current.getText(R.string.app_name).toString()) {
    Column(Modifier.padding(top=23.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(15.dp)
        ) {

            Text(
                text = title,
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
}