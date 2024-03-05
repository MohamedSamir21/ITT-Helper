package com.example.itthelper.authentication.presentation.welcome

import android.util.Log
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.itthelper.R
import com.example.itthelper.authentication.presentation.util.OnBoardingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen() {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    Log.i("WelcomeScreen", "created")
    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(.8f),
            verticalAlignment = Alignment.Bottom
        ) {
            PagerScreen(
                onBoardingPage = pages[it],
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .weight(.2f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SkipButton()
            CircularIndicator(pagerState)
            NextButton{
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
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
            modifier = Modifier.fillMaxHeight(.2f)
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
fun CircularIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.padding(10.dp)){
        Row {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.Green else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                )
            }
        }
    }
}