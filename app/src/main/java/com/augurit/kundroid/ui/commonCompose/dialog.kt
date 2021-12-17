package com.augurit.kundroid.ui.commonCompose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * 包名: com.augurit.kundroid.ui.commonCompose
 * 文件描述: TODO
 * 创建人: haikun
 * 创建时间: 2021/12/16 11:59
 * 修改人: haikun
 * 修改时间: 2021/12/16 11:59
 * 修改备注: TODO
 */
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