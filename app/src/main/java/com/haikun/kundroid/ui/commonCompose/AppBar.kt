package com.haikun.kundroid.ui.commonCompose

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun CommonAppBar(text: String, navController: NavHostController?) {
    TopAppBar(navigationIcon = {
        IconButton(onClick = { navController?.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    }, title = {
        Text(text = text,style= MaterialTheme.typography.h6 )
    })
}