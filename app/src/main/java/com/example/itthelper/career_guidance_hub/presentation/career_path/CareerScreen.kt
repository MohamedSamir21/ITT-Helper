package com.example.itthelper.career_guidance_hub.presentation.career_path

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.presentation.navigation.Screen
import com.example.itthelper.core.ui.components.AppLogo
import com.example.itthelper.core.ui.theme.ITTHelperTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun CareerScreen(
    navController: NavController,
    careerViewModel: CareerViewModel
) {
    val viewModel = remember {
        careerViewModel
    }
    CareerContent(
        navController = navController,
        state = viewModel.state.value
    )
}

@Composable
fun CareerContent(
    navController: NavController,
    state: CareerScreenState
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        BannerAds(
            careerBannerItems = state.careerBannerItems,
            navController = navController
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        PathsText()
        GalleryHorizontalPager(paths = state.paths)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BannerAds(
    careerBannerItems: List<CareerBannerItem>,
    navController: NavController
) {
    val pagerState = rememberPagerState {
        careerBannerItems.size + 1
    }
    val animationScope = rememberCoroutineScope()

    LaunchedEffect(key1 = pagerState.currentPage) {
        launch {
            delay(5000)
            with(pagerState) {
                val target = if (currentPage < pageCount - 1) currentPage + 1 else 0

                animationScope.launch {
                    animateScrollToPage(
                        page = target,
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = FastOutSlowInEasing
                        )
                    )
                }
            }
        }
    }
    HorizontalPager(
        state = pagerState
    ) { page ->
        if (page == 0) {
            ContactCard {
                navController.navigate(Screen.ContactUs.route)
            }
        } else {
            CareerBannerCard(item = careerBannerItems[page - 1])
        }
    }
}

@Composable
private fun PathsText() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.paths),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun GalleryHorizontalPager(
    paths: List<String>
) {

    val pagerState = rememberPagerState(pageCount = {
        paths.size
    })
    LaunchedEffect(key1 = true) {
        pagerState.scrollToPage(paths.size / 2)
    }

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 60.dp),
    ) { page ->
        PathCard(
            pagerState = pagerState,
            pageIndex = page,
            pathName = paths[page]
        )
    }
}

@Composable
private fun ContactCard(
    onContactClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .height(160.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(10.dp)
            ) {
                AppLogo(fontSize = 20.sp)
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
                Text(
                    text = stringResource(R.string.we_help_you_to_start_your_career),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Button(
                    onClick = onContactClicked,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.contact_us)
                    )
                }
            }
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.course),
                    contentDescription = stringResource(id = R.string.contact_us)
                )
            }
        }
    }
}

@Composable
private fun CareerBannerCard(
    item: CareerBannerItem
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .height(160.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(5.dp)
                    .size(125.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(5.dp),
                painter = painterResource(id = item.imageResId),
                contentDescription = stringResource(id = item.titleResId),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .height(125.dp),
            ) {
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(5.dp),
                    text = stringResource(id = item.titleResId),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(
                    text = stringResource(id = item.descriptionResId),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PathCard(
    pagerState: PagerState,
    pageIndex: Int,
    pathName: String
) {
    Card(
        Modifier
            .height(300.dp)
            .graphicsLayer {
                // Calculate the absolute offset for the current page from the
                // scroll position. We use the absolute value which allows us to mirror
                // any effects for both directions
                val pageOffset = (
                        (pagerState.currentPage - pageIndex) + pagerState
                            .currentPageOffsetFraction
                        ).absoluteValue

                // We animate the alpha, between 50% and 100%
                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
                scaleX = lerp(
                    start = 0.8f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
                scaleY = lerp(
                    start = 0.8f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.progress),
                contentDescription = stringResource(R.string.path_image)
            )
            val progress by remember {
                mutableFloatStateOf(Math.random().toFloat())
            }
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 5.dp, end = 5.dp)
            ) {
                LinearProgressIndicator(
                    modifier = Modifier.height(10.dp),
                    progress = { progress },
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.onPrimary
                )
                Text(text = "${(progress * 100).toInt()}%")
            }
        }
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = pathName)
        }
    }
}

@Preview
@Composable
private fun CareerBannerCardPreview() {
    ITTHelperTheme {
        CareerBannerCard(item = CareerBanner.items[0])
    }
}

@Preview
@Composable
private fun GalleryHorizontalPagerPreview() {
    ITTHelperTheme {
        val state = CareerScreenState(
            paths = listOf(
                "path 1",
                "path 2",
                "path 3",
            ),
            careerBannerItems = CareerBanner.items
        )

        GalleryHorizontalPager(paths = state.paths)
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun CareerContentPreview() {
    ITTHelperTheme {
        val state = CareerScreenState(
            paths = listOf(
                "path 1",
                "path 2",
                "path 3",
            ),
            careerBannerItems = CareerBanner.items
        )

        CareerContent(
            navController = rememberNavController(),
            state = state
        )
    }
}