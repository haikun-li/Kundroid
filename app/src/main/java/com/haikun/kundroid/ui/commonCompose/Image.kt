package com.haikun.kundroid.ui.commonCompose

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import coil.transform.Transformation
import com.haikun.kundroid.R


@Composable
fun NetImage(url:String,modifier: Modifier = Modifier,previewPlaceholder:Int= R.drawable.icon_head){
    Image(
        painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
                placeholder(previewPlaceholder)
                error(previewPlaceholder)
                transformations(CircleCropTransformation())
                listener(onError = {_,e->
                    e.printStackTrace()
                })
            }
        ),
        contentDescription = null,
        modifier=modifier
    )
}


@Composable
fun CircleNetImage(url:String,modifier: Modifier = Modifier,previewPlaceholder:Int= R.drawable.icon_head){
    Image(
        painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
                placeholder(previewPlaceholder)
                error(previewPlaceholder)
                transformations(CircleCropTransformation())
                listener(onError = {_,e->
                    e.printStackTrace()
                })
            }
        ),
        contentDescription = null,
        modifier=modifier
    )
}