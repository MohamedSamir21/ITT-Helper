package com.example.itthelper.career_guidance_hub.presentation.courses

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.presentation.util.LargePageSize
import com.example.itthelper.core.ui.theme.ITTHelperTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoursesScreen(
    state: CoursesScreenState,
    onContactClicked: () -> Unit
) {
    val pagerState = rememberPagerState {
        state.courses.size
    }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(10.dp)
    ) {
        CourseBanner(
            modifier = Modifier
                .height(130.dp)
                .fillMaxWidth()
                .padding(10.dp),
            title = stringResource(R.string.what_would_you_like_to_learn_today)
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(
            text = stringResource(R.string.courses),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 20.dp)
        )
        CoursePager(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            pagerState = pagerState,
            courses = state.courses,
            onForwardClicked = {}
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(
            text = stringResource(R.string.did_not_find_the_courses_you_are_seeking),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 10.dp, bottom = 5.dp)
        )
        Button(
            modifier = Modifier.padding(start = 10.dp),
            onClick = onContactClicked,
            shape = RectangleShape
        ) {
            Text(text = "Contact Us")
        }
    }
}

@Composable
fun CourseBanner(
    modifier: Modifier = Modifier,
    title: String
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RectangleShape
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                softWrap = true
            )
            Image(
                painter = painterResource(id = R.drawable.course),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxHeight()
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoursePager(
    modifier: Modifier = Modifier,
    courses: List<Course>,
    pagerState: PagerState,
    customPageSize: PageSize = LargePageSize,
    imageSize: Dp = 200.dp,
    onForwardClicked: () -> Unit
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        pageSize = customPageSize
    ) { pageIndex ->
        CourseCard(
            modifier = modifier,
            courses = courses,
            pageIndex = pageIndex,
            imageSize = imageSize,
            onForwardClicked = onForwardClicked
        )
    }
}

@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    courses: List<Course>,
    pageIndex: Int,
    imageSize: Dp = 200.dp,
    onForwardClicked: () -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RectangleShape
    ) {
        Image(
            painter = painterResource(id = courses[pageIndex].thumbnail),
            contentDescription = courses[pageIndex].courseName,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(10.dp)
                .border(
                    width = 1.dp,
                    shape = RectangleShape,
                    color = MaterialTheme.colorScheme.secondary
                )
                .fillMaxWidth()
                .height(imageSize)
                .padding(5.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = courses[pageIndex].courseName,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Button(
                onClick = onForwardClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = stringResource(R.string.go_forward)
                )
            }
        }
    }
}

@Preview
@Composable
private fun CourseBannerPreview() {
    ITTHelperTheme {
        CourseBanner(
            title = stringResource(id = R.string.what_would_you_like_to_learn_today)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun CoursePagerPreview() {
    ITTHelperTheme {
        val courses = listOf(
            Course(
                thumbnail = R.drawable.play,
                courseName = "Course"
            )
        )
        CoursePager(
            pagerState = rememberPagerState {
                courses.size
            },
            courses = courses,
            onForwardClicked = {},
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun CoursesScreenPreview() {
    val courses = listOf(
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

    val state by remember {
        mutableStateOf(
            CoursesScreenState(
                courses = courses
            )
        )
    }
    ITTHelperTheme {
        CoursesScreen(
            state = state,
            onContactClicked = {}
        )
    }
}

