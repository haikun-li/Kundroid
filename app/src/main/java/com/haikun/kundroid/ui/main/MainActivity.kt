package com.haikun.kundroid.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.rememberNavController
import com.haikun.kundroid.ui.theme.SystemUiController
import androidx.compose.runtime.*
import com.haikun.kundroid.ui.AppNavHost
import com.haikun.kundroid.ui.theme.AppTheme
import com.haikun.kundroid.ui.theme.AppThemeState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeState = remember {
                mutableStateOf(AppThemeState())
            }
            val systemUiController = remember { SystemUiController(window) }

            AppTheme(
                appTheme = themeState.value,
                systemUiController = systemUiController
            ) {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    AppNavHost(navController, themeState)
                }
            }
        }
    }
}