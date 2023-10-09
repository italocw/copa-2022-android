package me.dio.copa.catar.features

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.dio.copa.catar.R
import me.dio.copa.catar.domain.extensions.getDate
import me.dio.copa.catar.domain.model.MatchDomain
import me.dio.copa.catar.domain.model.TeamDomain
import me.dio.copa.catar.ui.theme.Shapes

@Composable
fun MainScreen(matches: List<MatchDomain>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(matches) { match ->
                MatchInfo(match)
            }
        }
    }
}


@Composable
fun MatchInfo(match: MatchDomain) {
    val contentDescription = stringResource(id = R.string.stadium_image)
    Card(shape = Shapes.large, modifier = Modifier.fillMaxWidth()) {
        Box {
            AsyncImage(
                model = match.stadium.image,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(160.dp)
            )
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Notification(match)
            Title(match)
            Teams(match)
        }
    }
}

@Composable
fun Notification(match: MatchDomain) {
    var contentDescriptionId: Int
    var drawableId: Int
    if (match.notificationEnabled) {
        contentDescriptionId = R.string.match_notification_is_enabled
        drawableId = R.drawable.ic_notifications_active
    } else {
        contentDescriptionId = R.string.match_notification_is_not_enabled
        drawableId = R.drawable.ic_notifications
    }

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

        Image(
            painter = painterResource(id = drawableId),
            contentDescription = stringResource(id = contentDescriptionId)
        )
    }
}

@Composable
fun Title(match: MatchDomain) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = "${match.date.getDate()} - ${match.name}",
            style = MaterialTheme.typography.h6.copy(color = Color.White)
        )
    }
}

@Composable
fun Teams(match: MatchDomain) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = CenterVertically
    )
    {
        TeamItem(team = match.team1)
        Text(
            text = "X",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.h6.copy(color = Color.White)
        )
        TeamItem(team = match.team2)
    }
}

@Composable
fun TeamItem(team: TeamDomain) {
    Row(verticalAlignment = CenterVertically) {
        Text(
            text = team.flag,
            modifier = Modifier.align(CenterVertically),
            style = MaterialTheme.typography.h3.copy(color = Color.White)
        )

        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = team.flag, textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6.copy(color = Color.White)
        )
    }
}