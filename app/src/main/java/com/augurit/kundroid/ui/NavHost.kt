package com.augurit.kundroid.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.augurit.kundroid.ui.screen.home.HomeScreen
import com.augurit.kundroid.ui.screen.login.LoginScreen
import com.augurit.kundroid.ui.screen.settings.SettingsScreen
import com.augurit.kundroid.ui.theme.AppThemeState

object NavHostName {
    const val HOME_SCREEN = "HOME_SCREEN"
    const val LOGIN_SCREEN = "LOGIN_SCREEN"
    const val SETTINGS_SCREEN = "SETTINGS_SCREEN"
}


@Composable
fun AppNavHost(navController: NavHostController, themeState: MutableState<AppThemeState>) {
    NavHost(navController, NavHostName.LOGIN_SCREEN) {
        composable(NavHostName.LOGIN_SCREEN) {
            LoginScreen(navController)
        }

        composable(
            "${NavHostName.HOME_SCREEN}?userName={userName}",
            arguments = listOf(navArgument("userName") { defaultValue = "蔡徐坤" })
        ) {
            HomeScreen(navController,it.arguments?.getString("userName"))
        }

        composable(
            NavHostName.SETTINGS_SCREEN,
        ) {
            SettingsScreen(navController,themeState)
        }

    }
}




