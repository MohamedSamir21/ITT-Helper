package com.example.itthelper.authentication.presentation.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.itthelper.R
import com.example.itthelper.authentication.presentation.util.OnBoardingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen() {
    val vm: WelcomeViewModel = viewModel()
    val state = vm.state

    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { currentPageIndex ->
            vm.updateCurrentPageIndex(currentPageIndex)
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.2f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SkipButton()
            CircularPagerIndicator(
                pagerState = pagerState,
                indicatorCirclesColors = state.value.indicatorCirclesColors
            ) {
                vm.updateIndicatorCirclesColors(it)
            }
            NextButton {
                // Improve Coroutine creation here..
                scope.launch {
                    vm.increaseCurrentPageIndex(pagerState.currentPage)
                    pagerState.animateScrollToPage(state.value.currentPageIndex)
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
        Image(
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = onBoardingPage.title,
            modifier = Modifier
                .padding(bottom = 50.dp)
                .fillMaxWidth(.5f)
                .fillMaxHeight(.3f),
            contentScale = ContentScale.Fit
        )
        Text(
            text = onBoardingPage.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 25.dp)
        )
        Text(
            text = onBoardingPage.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxHeight(.5f)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
fun SkipButton(
    onClick: () -> Unit = {}
) {
    TextButton(onClick = { onClick() }) {
        Text(text = stringResource(R.string.skip))
    }
}

@Composable
fun NextButton(
    onClick: () -> Unit = {}
) {
    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = stringResource(R.string.next)
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