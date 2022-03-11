package com.haikun.kundroid.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.haikun.kundroid.ui.NavHostName
import com.haikun.kundroid.ui.screen.collect.ArticleList
import com.haikun.kundroid.utils.LogUtil
import com.haikun.kundroid.utils.toJson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel(), navController: NavHostController) {
    LogUtil.e("到了在")
    Column {

        val collectAsLazyPagingItems =
            homeViewModel.getPagingDataFlow().collectAsLazyPagingItems()

        ArticleList(collectAsLazyPagingItems){
            val encodedUrl = URLEncoder.encode(
                "${
                    toJson(it)
                }", StandardCharsets.UTF_8.toString()
            )
            navController.navigate(
                "${NavHostName.DETAIL_SCREEN}/${
                    encodedUrl
                }"
            )
        }
    }

}


