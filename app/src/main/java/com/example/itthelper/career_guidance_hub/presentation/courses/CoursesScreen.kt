package com.example.itthelper.career_guidance_hub.presentation.courses

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.domain.model.Course
import com.example.itthelper.career_guidance_hub.presentation.components.RetryComponent
import com.example.itthelper.career_guidance_hub.presentation.navigation.Screen
import com.example.itthelper.career_guidance_hub.presentation.util.LargePageSize
import com.example.itthelper.career_guidance_hub.presentation.util.UiText
import com.example.itthelper.core.ui.theme.ITTHelperTheme

@Composable
fun CoursesScreen(
    navController: NavController,
    coursesViewModel: CoursesViewModel,
    onUnauthorized: (UiText) -> Unit
) {
    val viewModel = remember {
        coursesViewModel
    }

    viewModel.unauthorizedResult.collectAsState(initial = null).value?.let {
        onUnauthorized(it.message)
    }
    CoursesContent(
        navController = navController,
        state = viewModel.state.value,
        onRetryClicked = {
            viewModel.onEvent(CoursesScreenEvent.Retry)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoursesContent(
    navController: NavController,
    state: CoursesScreenState,
    onRetryClicked: () -> Unit
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
        if (state.isLoading)
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        if (state.error != null) {
            RetryComponent(
                modifier = Modifier.padding(10.dp),
                message = state.error.asString(),
                horizontalAlignment = Alignment.CenterHorizontally,
                onRetryClicked = onRetryClicked
            )
        }
        if (state.courses.isNotEmpty()) {
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
                onClick = {
                    navController.navigate(Screen.ContactUs.route)
                },
                shape = RectangleShape
            ) {
                Text(text = stringResource(id = R.string.contact_us))
            }
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
            painter = painterResource(id = R.drawable.play),
            contentDescription = courses[pageIndex].name,
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
                modifier = Modifier.weight(.8f),
                text = courses[pageIndex].name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary,
                overflow = TextOverflow.Ellipsis
            )
            Button(
                modifier = Modifier.weight(.2f),
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
                name = "Course name"
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
private fun CoursesContentPreview() {
    val courses = listOf(
        Course(
            name = "Course name"
        ),
        Course(
            name = "Course name"
        ),
        Course(
            name = "Course name"
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
        CoursesContent(
            navController = rememberNavController(),
            state = state,
            onRetryClicked = {}
        )
    }
}

