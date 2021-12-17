package com.augurit.kundroid.ui.commonCompose

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import com.augurit.kundroid.R


@Composable
fun NetImage(url:String,modifier: Modifier = Modifier,previewPlaceholder:Int= R.drawable.icon_head){
    Image(
        painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
                placeholder(previewPlaceholder)
                listener(onError = {_,e->
                    e.printStackTrace()
                })
            }
        ),
        contentDescription = null,
        modifier=modifier
    )
}