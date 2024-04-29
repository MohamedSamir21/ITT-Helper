package com.example.itthelper.career_guidance_hub.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.itthelper.career_guidance_hub.presentation.util.TabContent
import com.example.itthelper.career_guidance_hub.presentation.util.Tabs
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onTabSelected: @Composable (TabContent) -> Unit
) {
    val state = viewModel.state.value
    val pagerState = rememberPagerState {
        state.tabs.size
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ScrollableTabRow(
            selectedTabIndex = state.selectedTabIndex
        ) {
            state.tabs.forEachIndexed { index, tabItem ->
                Tab(
                    selected = index == state.selectedTabIndex,
                    onClick = {
                        viewModel.updateSelectedTabIndex(index)
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = tabItem.title)
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = true,
            modifier = Modifier.padding(10.dp)
        ) {
            onTabSelected(Tabs.tabs[it].tabContent)
        }
    }
}