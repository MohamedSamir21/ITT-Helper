package com.example.itthelper.career_guidance_hub.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.itthelper.career_guidance_hub.presentation.cvs_tips.CvTips
import com.example.itthelper.career_guidance_hub.presentation.cvs_tips.CvTipsViewModel
import com.example.itthelper.career_guidance_hub.presentation.event_workshops.EventsScreen
import com.example.itthelper.career_guidance_hub.presentation.home.HomeScreen
import com.example.itthelper.career_guidance_hub.presentation.home.HomeViewModel
import com.example.itthelper.career_guidance_hub.presentation.interviews_guide.InterviewGuideScreen
import com.example.itthelper.career_guidance_hub.presentation.interviews_guide.InterviewGuideViewModel
import com.example.itthelper.career_guidance_hub.presentation.training.TrainingScreen
import com.example.itthelper.career_guidance_hub.presentation.util.TabContent

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Screen.Home.route
        ) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel
            ) {
                SetupTabs(tabContent = it)
            }
        }
        composable(
            route = Screen.Courses.route
        ) {

        }
        composable(
            route = Screen.Training.route
        ) {
            TrainingScreen()
        }
    }
}

@Composable
fun SetupTabs(
    tabContent: TabContent
) {
    when (tabContent) {
        TabContent.CareerPath -> Text(text = "Career Path")
        TabContent.EmploymentMarket -> Text(text = "Employment Market")
        TabContent.EventsWorkshops -> EventsScreen()
        TabContent.CvsTips -> {
            val viewModel: CvTipsViewModel = viewModel()
            CvTips(
                tips = viewModel.state.value.tips,
            ) {
                viewModel.updateTipItemVisibility(it)
            }
        }

        TabContent.GuideForInterviews -> {
            val viewModel: InterviewGuideViewModel = viewModel()
            InterviewGuideScreen(tips = viewModel.state.value.tips) {
                viewModel.updateTipItemVisibility(it)
            }
        }
    }
}