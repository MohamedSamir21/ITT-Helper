package com.example.itthelper.career_guidance_hub.presentation.event_workshops

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EventsScreen() {
    val viewModel: EventsViewModel = viewModel()
    val state = viewModel.state.value
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.events) { event ->
            EventCard(event = event)
        }
    }
}

@Composable
fun EventCard(
    event: Event
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = event.image),
                contentDescription = event.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize(.4f)
            )
            Divider()
            Text(
                text = event.name,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = event.date,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = event.location,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.size(5.dp))
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(text = "Register")
            }
        }

    }
}
