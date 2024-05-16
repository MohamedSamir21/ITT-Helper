package com.example.itthelper.career_guidance_hub.presentation.navigation

import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.itthelper.career_guidance_hub.presentation.career_path.CareerScreen
import com.example.itthelper.career_guidance_hub.presentation.career_path.CareerViewModel
import com.example.itthelper.career_guidance_hub.presentation.contact.ContactScreen
import com.example.itthelper.career_guidance_hub.presentation.contact.ContactViewModel
import com.example.itthelper.career_guidance_hub.presentation.courses.CoursesScreen
import com.example.itthelper.career_guidance_hub.presentation.courses.CoursesViewModel
import com.example.itthelper.career_guidance_hub.presentation.cvs_tips.CvTips
import com.example.itthelper.career_guidance_hub.presentation.cvs_tips.CvTipsViewModel
import com.example.itthelper.career_guidance_hub.presentation.event_workshops.EventsScreen
import com.example.itthelper.career_guidance_hub.presentation.event_workshops.EventsViewModel
import com.example.itthelper.career_guidance_hub.presentation.feedback.FeedbackScreen
import com.example.itthelper.career_guidance_hub.presentation.feedback.FeedbackViewModel
import com.example.itthelper.career_guidance_hub.presentation.home.HomeScreen
import com.example.itthelper.career_guidance_hub.presentation.home.HomeViewModel
import com.example.itthelper.career_guidance_hub.presentation.interviews_guide.InterviewGuideScreen
import com.example.itthelper.career_guidance_hub.presentation.interviews_guide.InterviewGuideViewModel
import com.example.itthelper.career_guidance_hub.presentation.profile.ProfileScreen
import com.example.itthelper.career_guidance_hub.presentation.profile.ProfileViewModel
import com.example.itthelper.career_guidance_hub.presentation.training.TrainingScreen
import com.example.itthelper.career_guidance_hub.presentation.training.TrainingViewModel
import com.example.itthelper.career_guidance_hub.presentation.util.TabContent
import com.example.itthelper.career_guidance_hub.presentation.util.UiText

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String,
    onUnauthorized: (UiText) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            expandIn {
                IntSize(0, 0)
            }
        },
        exitTransition = {
            shrinkOut {
                IntSize(0, 0)
            }
        }
    ) {
        composable(
            route = Screen.Home.route
        ) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel
            ) {
                SetupTabs(
                    navController = navController,
                    tabContent = it,
                    onUnauthorized = { uiText -> onUnauthorized(uiText) }
                )
            }
        }
        composable(
            route = Screen.Courses.route
        ) {
            val viewModel: CoursesViewModel = hiltViewModel()
            CoursesScreen(
                navController = navController,
                coursesViewModel = viewModel,
                onUnauthorized = { onUnauthorized(it) }
            )
        }
        composable(
            route = Screen.Training.route
        ) {
            val viewModel: TrainingViewModel = hiltViewModel()
            TrainingScreen(
                trainingViewModel = viewModel,
                onUnauthorized = { onUnauthorized(it) }
            )
        }
        composable(
            route = Screen.Profile.route
        ) {
            val viewModel: ProfileViewModel = hiltViewModel()
            ProfileScreen(
                navController = navController,
                profileViewModel = viewModel,
                onLogout = onUnauthorized
            )
        }
        composable(
            route = Screen.ContactUs.route
        ) {
            val viewModel: ContactViewModel = hiltViewModel()
            ContactScreen(
                navController = navController,
                contactViewModel = viewModel
            )
        }
        composable(
            route = Screen.Feedback.route
        ) {
            val viewModel: FeedbackViewModel = hiltViewModel()
            FeedbackScreen(
                navController = navController,
                feedbackViewModel = viewModel
            )
        }
    }
}

@Composable
fun SetupTabs(
    navController: NavHostController,
    tabContent: TabContent,
    onUnauthorized: (UiText) -> Unit
) {
    when (tabContent) {
        TabContent.CareerPath -> {
            val viewModel: CareerViewModel = hiltViewModel()
            CareerScreen(
                navController = navController,
                careerViewModel = viewModel,
                onUnauthorized = { onUnauthorized(it) }
            )
        }

        TabContent.EventsWorkshops -> {
            val viewModel: EventsViewModel = hiltViewModel()
            EventsScreen(
                eventsViewModel = viewModel,
                onUnauthorized = { onUnauthorized(it) }
            )
        }

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