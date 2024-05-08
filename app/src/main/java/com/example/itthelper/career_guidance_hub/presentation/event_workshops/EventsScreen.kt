package com.example.itthelper.career_guidance_hub.presentation.event_workshops

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.presentation.components.InfoItem
import com.example.itthelper.core.ui.theme.ITTHelperTheme

@Composable
fun EventsScreen(
    state: EventsScreenState
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = stringResource(R.string.we_provide_you_the_events_and_workshops),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
        }
        item {
            Text(
                text = stringResource(R.string.sharing_insights_experiences_and_expertise),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
            HorizontalDivider()
        }
        items(state.events) { event ->
            EventCard(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                event = event
            )
        }
    }
}

@Composable
fun EventCard(
    modifier: Modifier = Modifier,
    event: Event
) {
    OutlinedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Image(
            painter = painterResource(id = event.image),
            contentDescription = event.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .size(90.dp)
        )
        Text(
            text = event.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "100LE",
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(end = 5.dp)
            )
            Text(
                text = "/Per Person",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        InfoItem(infoTitle = stringResource(R.string.date), infoValue = event.date)
        InfoItem(infoTitle = stringResource(R.string.location), infoValue = event.location)
        InfoItem(infoTitle = stringResource(R.string.deadline), infoValue = event.deadline)
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.register))
        }

    }
}

@Preview(
    showBackground = true
)
@Composable
private fun EventCardPreview() {
    ITTHelperTheme {
        EventCard(
            event = Event(
                image = R.drawable.event,
                name = "Android Track",
                date = "22/8/2024",
                location = "Cairo",
                deadline = "30/4/2024"
            )
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun EventsScreenPreview() {
    ITTHelperTheme {
        val state by remember {
            mutableStateOf(
                EventsScreenState(
                    listOf(
                        Event(
                            image = R.drawable.event,
                            name = "Dev",
                            date = "22/4/2024",
                            location = "Cairo",
                            deadline = "22/5/2024"
                        )
                    )
                )
            )
        }
        EventsScreen(state = state)
    }
}