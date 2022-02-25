package com.haikun.kundroid.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.haikun.kundroid.ui.NavHostName
import com.haikun.kundroid.ui.commonCompose.NetImage
import com.haikun.kundroid.ui.commonCompose.SwipeRefreshList
import com.haikun.kundroid.utils.toJson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun RecommendScreen(homeViewModel: HomeViewModel = hiltViewModel(), navController: NavHostController) {
    val tabTitle = homeViewModel.tabTitle

    Column {
        ScrollableTabRow(
            selectedTabIndex = homeViewModel.selectedTabIndex, edgePadding = 10.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            tabTitle.forEachIndexed { index, tabTitle ->
                Tab(
                    selected = index == homeViewModel.selectedTabIndex,
                    onClick = {
                        homeViewModel.selectedTabIndex = index
                    }) {
                    Text(tabTitle.title, modifier = Modifier.padding(8.dp))
                }
            }
        }

        when(homeViewModel.selectedTabIndex){
            0->{
                RecommendContent(navController)
            }
            1->{

            }else->{

            }
        }
    }

}


@Composable
fun RecommendContent(navController: NavHostController) {

    val homeViewModel: HomeViewModel = hiltViewModel()

    val collectAsLazyPagingItems =
        homeViewModel.getPagingDataFlow().collectAsLazyPagingItems()
    SwipeRefreshList(collectAsLazyPagingItems) {
        items(collectAsLazyPagingItems, key = {
            it.id
        }) {
            it?.let {
                Column(
                    Modifier
                        .clickable {
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
                        .padding(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NetImage(
                            url = it.envelopePic, modifier = Modifier
                                .size(
                                    80.dp, 80.dp
                                )
                                .padding(end = 8.dp)
                        )
                        Column(Modifier.fillMaxWidth()) {
                            Text(text = it.title, style = MaterialTheme.typography.h6, fontSize = 16.sp)
                            Text(text = it.desc, style = MaterialTheme.typography.body2, maxLines = 2,overflow= TextOverflow.Ellipsis)
                        }
                    }
                }
                Divider()
            }
        }
    }
}

