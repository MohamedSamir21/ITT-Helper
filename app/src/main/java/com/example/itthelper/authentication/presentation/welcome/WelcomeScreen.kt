package com.example.itthelper.authentication.presentation.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.itthelper.R
import com.example.itthelper.authentication.presentation.navigation.Screen
import com.example.itthelper.authentication.presentation.util.OnBoardingPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    viewModel: WelcomeViewModel
) {
    val state = viewModel.state
    viewModel.setSystemThemeState(isSystemInDarkTheme())

    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { currentPageIndex ->
            viewModel.updateCurrentPageIndex(currentPageIndex)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(.8f),
            verticalAlignment = Alignment.Bottom,
        ) {
            PagerScreen(
                onBoardingPage = state.value.onBoardingPages[it],
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.2f),
            contentAlignment = Alignment.Center
        ) {
            val isNotLastPage = !state.value.isLastPage
            if (isNotLastPage)
                SkipButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    textColor = MaterialTheme.colorScheme.tertiary
                ) {
                    scope.launch {
                        withContext(Dispatchers.IO) {
                            viewModel.saveOnWelcomeDone(true)
                        }
                    }
                    navController.popBackStack()
                    navController.navigate(
                        route = Screen.AUTH.route
                    )
                }
            CircularPagerIndicator(
                pagerState = pagerState,
                indicatorCirclesColors = state.value.indicatorCirclesColors
            ) {
                viewModel.updateIndicatorCirclesColors(it)
            }
            NextButton(
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                // Improve Coroutine creation here..
                scope.launch {
                    if (state.value.isLastPage) {
                        withContext(Dispatchers.IO) {
                            viewModel.saveOnWelcomeDone(true)
                        }
                        navController.popBackStack()
                        navController.navigate(Screen.AUTH.route)
                    } else {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        viewModel.updateCurrentPageIndex(pagerState.currentPage)
                    }
                }
            }
        }
    }
}

@Composable
fun PagerScreen(
    onBoardingPage: OnBoardingPage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 50.dp)
                .fillMaxWidth(.5f)
                .fillMaxHeight(.3f),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .requiredSize(200.dp)
                    .alpha(.1f)
            ) {}
            Image(
                painter = painterResource(id = onBoardingPage.image),
                contentDescription = onBoardingPage.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .requiredSize(150.dp)
            )
        }

        Text(
            text = onBoardingPage.title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 25.dp)
        )
        Text(
            text = onBoardingPage.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .fillMaxHeight(.5f)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
fun SkipButton(
    modifier: Modifier = Modifier,
    textColor: Color,
    onClick: () -> Unit = {}
) {
    TextButton(
        modifier = modifier,
        onClick = { onClick() }
    ) {
        Text(
            text = stringResource(R.string.skip),
            color = textColor
        )
    }
}

@Composable
fun NextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    IconButton(
        modifier = modifier,
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = stringResource(R.string.next),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CircularPagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    indicatorCirclesColors: List<IndicatorCircleColor>,
    onDrawCircles: (Int) -> Unit
) {
    Box(modifier = modifier.padding(10.dp)) {
        Row {
            val currentPageIndex = pagerState.currentPage
            LaunchedEffect(currentPageIndex) {
                onDrawCircles(currentPageIndex)
            }
            repeat(pagerState.pageCount) { iteration ->
                IndicatorCircle(
                    color = indicatorCirclesColors[iteration].color
                )
            }
        }
    }
}

@Composable
fun IndicatorCircle(
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(2.dp)
            .clip(CircleShape)
            .background(color)
            .size(10.dp)
    )
}