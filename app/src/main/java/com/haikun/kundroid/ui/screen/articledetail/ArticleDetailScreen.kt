package com.haikun.kundroid.ui.screen.articledetail

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.haikun.kundroid.R
import com.haikun.kundroid.ui.commonCompose.CommonAppBar

/**
 * 描述:
 * 创建人: haikun
 * 创建时间: 2022/1/21 16:46
 */
@Composable
fun ArticleDetailScreen(scaffoldState: ScaffoldState = rememberScaffoldState()) {
    val viewModel: ArticleDetailViewModel = hiltViewModel()
    val uiState = viewModel.articleDetailUiState
    val navUiState = viewModel.navUiState
    Scaffold(scaffoldState = scaffoldState, topBar = {
        CommonAppBar(uiState.title, navUiState.navHostController)
    }) {
        Column {
            Box(modifier = Modifier.weight(1f)) {
                if (uiState.link.isNotEmpty()){
                    WebView()
                }
                Image(
                    modifier = Modifier.align(Alignment.CenterEnd).padding(end = 20.dp).width(40.dp).height(40.dp).clickable {
                        viewModel.delCollect()
                    },
                    contentScale= ContentScale.FillBounds,
                    painter = painterResource(
                        id = if (uiState.isCollect) {
                            R.drawable.collect_select
                        } else {
                            R.drawable.collect_normol
                        }
                    ),
                    contentDescription = null
                )
            }

        }
    }

}

@Composable
fun WebView() {
    val viewModel: ArticleDetailViewModel = hiltViewModel()
    val uiState = viewModel.articleDetailUiState
    AndroidView(
        factory = { context ->
            val webView = WebView(context)
            webView.settings.javaScriptEnabled = true
            webView.settings.javaScriptCanOpenWindowsAutomatically = true
            webView.settings.domStorageEnabled = true
            webView.settings.loadsImagesAutomatically = true
            webView.settings.mediaPlaybackRequiresUserGesture = false
            webView.webViewClient = WebViewClient()
            uiState.link.let {
                webView.loadUrl(it)
            }
            webView
        })
}