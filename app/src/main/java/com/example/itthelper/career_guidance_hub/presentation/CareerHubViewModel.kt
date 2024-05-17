package com.example.itthelper.career_guidance_hub.presentation

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itthelper.career_guidance_hub.presentation.navigation.Screen

class CareerHubViewModel : ViewModel() {
    private var _selectedNavigationItemIndex = mutableIntStateOf(0)
    private var _showUnauthorizedDialog = mutableStateOf(false)
    val showUnauthorizedDialogValue: Boolean
        get() = _showUnauthorizedDialog.value
    private var _unauthorizedMessage = mutableStateOf("")
    val unauthorizedMessageValue: String
        get() = _unauthorizedMessage.value
    private var _showTopAppBar = mutableStateOf(true)
    val showTopAppBarValue: Boolean
        get() = _showTopAppBar.value
    private var _showBottomAppBar = mutableStateOf(true)
    val showBottomAppBarValue: Boolean
        get() = _showBottomAppBar.value
    private var _showFAB = mutableStateOf(true)
    val showFABValue: Boolean
        get() = _showFAB.value
    private var _currentRoute = mutableStateOf<String?>("")


    fun onSelectedNavigationItemIndex(index: Int) {
        _selectedNavigationItemIndex.intValue = index
    }

    fun showUnauthorizedDialog() {
        _showUnauthorizedDialog.value = true
    }

    fun setUnauthorizedMessage(message: String) {
        _unauthorizedMessage.value = message
    }

    fun updateCurrentRoute(route: String?) {
        _currentRoute.value = route
        when (route) {
            Screen.Profile.route,
            Screen.ContactUs.route,
            Screen.Feedback.route -> hideAppBarsAndFAB()

            else -> showAppBarsAndFAB()
        }
    }

    private fun showTopAppBar() {
        _showTopAppBar.value = true
    }

    private fun hideTopAppBar() {
        _showTopAppBar.value = false
    }

    private fun showBottomAppBar() {
        _showBottomAppBar.value = true
    }

    private fun hideBottomAppBar() {
        _showBottomAppBar.value = false
    }

    private fun showFAB() {
        _showFAB.value = true
    }

    private fun hideFAB() {
        _showFAB.value = false
    }

    private fun showAppBarsAndFAB() {
        showTopAppBar()
        showBottomAppBar()
        showFAB()
    }

    private fun hideAppBarsAndFAB() {
        hideTopAppBar()
        hideBottomAppBar()
        hideFAB()
    }
}