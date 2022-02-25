package com.haikun.kundroid.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController


open class BaseViewModel : ViewModel() {

    lateinit var navHostController: NavHostController
    var navUiState by mutableStateOf(NavUiState())

    open fun initUiState(navHostController: NavHostController,vararg jsons: String?) {
        navUiState = navUiState.copy(navHostController = navHostController)
    }
}