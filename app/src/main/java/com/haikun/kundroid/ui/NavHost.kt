package com.haikun.kundroid.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.haikun.kundroid.ui.screen.articledetail.ArticleDetailScreen
import com.haikun.kundroid.ui.screen.articledetail.ArticleDetailViewModel
import com.haikun.kundroid.ui.screen.collect.CollectScreen
import com.haikun.kundroid.ui.screen.collect.CollectViewModel
import com.haikun.kundroid.ui.screen.home.HomeScreen
import com.haikun.kundroid.ui.screen.login.LoginScreen
import com.haikun.kundroid.ui.screen.main.MainScreen
import com.haikun.kundroid.ui.screen.register.RegisterScreen
import com.haikun.kundroid.ui.screen.settings.SettingsScreen
import com.haikun.kundroid.ui.theme.AppThemeState

object NavHostName {
    const val MAIN_SCREEN = "MAIN_SCREEN"
    const val HOME_SCREEN = "HOME_SCREEN"
    const val LOGIN_SCREEN = "LOGIN_SCREEN"
    const val REGISTER_SCREEN = "REGISTER_SCREEN"
    const val SETTINGS_SCREEN = "SETTINGS_SCREEN"
    const val DETAIL_SCREEN = "DETAIL_SCREEN"
    const val COLLECT_LIST_SCREEN = "COLLECT_LIST_SCREEN"
}


@Composable
fun AppNavHost(navController: NavHostController, themeState: MutableState<AppThemeState>) {

    NavHost(navController, NavHostName.LOGIN_SCREEN) {

        composable(NavHostName.MAIN_SCREEN){
            MainScreen(navController)
        }

        composable(NavHostName.LOGIN_SCREEN) {
            LoginScreen(navController)
        }

        composable(NavHostName.REGISTER_SCREEN) {
            RegisterScreen(navController)
        }

        composable(
            NavHostName.HOME_SCREEN
        ) {
            HomeScreen(navController=navController)
        }

        composable(
            NavHostName.SETTINGS_SCREEN,
        ) {
            SettingsScreen(navController, themeState)
        }

        composable(
            NavHostName.COLLECT_LIST_SCREEN,
        ) {
            val viewModel: CollectViewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                viewModel.initUiState(navController)
            }
            CollectScreen()
        }

        composable(
            "${NavHostName.DETAIL_SCREEN}/{articleJson}?ifFromCollect={ifFromCollect}",
            arguments = listOf(navArgument("ifFromCollect") {
                defaultValue = false
                type = NavType.BoolType
            })
        ) {
            val viewModel: ArticleDetailViewModel = hiltViewModel()
            val json = it.arguments?.getString("articleJson")
            val ifFromCollect = it.arguments?.getBoolean("ifFromCollect")
            LaunchedEffect(json) {
                viewModel.initUiState(navController, json,ifFromCollect)
            }
            ArticleDetailScreen()
        }

    }
}




