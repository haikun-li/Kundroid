package com.haikun.kundroid.ui.commonCompose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun LoadingDialog() {
    AlertDialog(
        text = {
            CircularProgressIndicator(modifier = Modifier
                .padding(horizontal = 80.dp)
                .size(60.dp, 60.dp))
        },
        buttons = {

        },
        onDismissRequest = {  }
    )
    /*Box(
        modifier = Modifier
            .background(color = DialogBg)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator(modifier = Modifier.size(60.dp, 60.dp))
    }*/
}