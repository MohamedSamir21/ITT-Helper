package com.example.itthelper.career_guidance_hub.presentation.training

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.presentation.courses.Course
import com.example.itthelper.career_guidance_hub.presentation.courses.CoursePager
import com.example.itthelper.career_guidance_hub.presentation.util.LargePageSize
import com.example.itthelper.career_guidance_hub.presentation.util.SmallPageSize
import com.example.itthelper.core.ui.theme.ITTHelperTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrainingScreen(
    state: TrainingScreenState,
    onBookClicked: () -> Unit,
    onForwardClicked: () -> Unit
) {
    val trainingPagerState = rememberPagerState {
        state.programs.size
    }
    val coursePagerState = rememberPagerState {
        state.courses.size
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(10.dp)
    ) {
        TrainingBanner(
            text = stringResource(R.string.training_banner_message)
        )
        HorizontalDivider()
        TrainingMotivation(
            modifier = Modifier.padding(top = 40.dp, start = 5.dp, end = 5.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 5.dp, end = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            ChooseCard()
        }
        HorizontalPager(
            state = trainingPagerState,
            modifier = Modifier.fillMaxWidth(),
            pageSize = LargePageSize
        ) { currentIndex ->
            TrainingCard(
                program = state.programs[currentIndex],
                onBookClicked = onBookClicked
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 15.dp))
        Text(
            text = stringResource(R.string.haven_not_built_a_skill_yet),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = stringResource(R.string.courses_you_might_like),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
        )
        CoursePager(
            modifier = Modifier
                .height(240.dp)
                .padding(5.dp),
            courses = state.courses,
            pagerState = coursePagerState,
            imageSize = 150.dp,
            customPageSize = SmallPageSize,
            onForwardClicked = onForwardClicked
        )
    }
}

@Composable
private fun TrainingBanner(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(10.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onTertiaryContainer
    )
}

@Composable
private fun TrainingMotivation(
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            5.dp
        ),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Text(
            text = stringResource(R.string.training_motivation_message),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textAlign = TextAlign.Center,
            softWrap = true
        )
    }
}

@Composable
private fun ChooseCard() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            5.dp
        )
    ) {
        Text(
            text = stringResource(R.string.choose_one),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(10.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun TrainingCard(
    modifier: Modifier = Modifier,
    program: TrainingProgram,
    onBookClicked: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxHeight(.5f)
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(
            5.dp
        ),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Box {
            Image(
                painter = painterResource(id = program.image),
                contentDescription = program.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp),
                contentScale = ContentScale.Inside,
                alignment = Alignment.TopCenter
            )
            TrainingCardTopRow(
                modifier = Modifier.fillMaxWidth(),
                program = program
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.secondaryContainer,
                                MaterialTheme.colorScheme.secondaryContainer
                            )
                        )
                    )
                    .align(Alignment.BottomCenter)
            ) {
                HorizontalDivider(Modifier.padding(vertical = 5.dp, horizontal = 5.dp))
                TrainingCardBottomCard(
                    program = program,
                    onBookClicked = onBookClicked
                )
            }
        }
    }
}

@Composable
private fun TrainingCardTopRow(
    modifier: Modifier = Modifier,
    program: TrainingProgram
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.on, program.date),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = 10.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = .8f))
                .padding(5.dp)
        )
        Text(
            text = program.place,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 10.dp))
                .background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = .5f))
                .padding(5.dp)
        )
    }
}

@Composable
private fun TrainingCardBottomCard(
    program: TrainingProgram,
    onBookClicked: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = program.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = program.company,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = onBookClicked,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 5.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(text = stringResource(R.string.book))
        }
    }
}

@Preview
@Composable
private fun TrainingBannerPreview() {
    ITTHelperTheme {
        TrainingBanner(text = stringResource(id = R.string.training_banner_message))
    }
}

@Preview
@Composable
private fun TrainingMotivationPreview() {
    ITTHelperTheme {
        TrainingMotivation()
    }
}

@Preview
@Composable
private fun TrainingCardPreview() {
    val state by remember {
        mutableStateOf(
            TrainingProgram(
                image = R.drawable.intern,
                name = "Training 1",
                date = "10:45:00",
                place = "Alex",
                company = "Google"
            )
        )
    }

    ITTHelperTheme {
        Box(
            modifier = Modifier.fillMaxHeight()
        ) {
            TrainingCard(program = state) {

            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun TrainingScreenPreview() {
    val state by remember {
        mutableStateOf(
            TrainingScreenState(
                listOf(
                    TrainingProgram(
                        image = R.drawable.intern,
                        name = "Training 1",
                        date = "10:45:00",
                        place = "Alex",
                        company = "Google"
                    )
                ),
                courses = listOf(
                    Course(
                        thumbnail = R.drawable.play,
                        courseName = "Course"
                    ),
                    Course(
                        thumbnail = R.drawable.play,
                        courseName = "Course"
                    ),
                    Course(
                        thumbnail = R.drawable.play,
                        courseName = "Course"
                    )
                )
            )
        )
    }

    ITTHelperTheme {
        TrainingScreen(
            state = state,
            onBookClicked = {},
            onForwardClicked = {}
        )
    }
}