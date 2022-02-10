package com.haikun.kundroid.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.haikun.kundroid.data.Resource
import com.haikun.kundroid.data.response.TabTitle
import com.haikun.kundroid.ui.commonCompose.LoadingDialog
import com.haikun.kundroid.ui.commonCompose.NetImage
import com.haikun.kundroid.ui.commonCompose.SwipeRefreshList


@Composable
fun RecommendScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val tabTitle by homeViewModel.tabTitle.collectAsState(Resource.EmptyResource())

    tabTitle.let {
        when (it) {
            is Resource.SuccessResource -> {
                RecommendTab(it.data)
            }
            is Resource.ErrorResource -> {
            }
            is Resource.LoadingResource -> {
                LoadingDialog()
            }
            else -> {

            }
        }
    }

}

@Composable
fun RecommendTab(tabTitle: List<TabTitle>?) {

    var selectedTabIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Column {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex, edgePadding = 10.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            tabTitle?.forEachIndexed { index, tabTitle ->
                Tab(selected = index == selectedTabIndex, onClick = { selectedTabIndex = index }) {
                    Text(tabTitle.title, modifier = Modifier.padding(8.dp))
                }
            }
        }

        RecommendContent(selectedTabIndex, tabTitle)
    }

}

@Composable
fun RecommendContent(selectedTabIndex: Int, tabTitle: List<TabTitle>?) {

    tabTitle?.let { it ->

        val homeViewModel: HomeViewModel = hiltViewModel()

        val collectAsLazyPagingItems =
            homeViewModel.getArticleList().collectAsLazyPagingItems()

        SwipeRefreshList(collectAsLazyPagingItems) {
            items(collectAsLazyPagingItems) {
                it?.let {
                    Column(
                        Modifier
                            .clickable {

                            }
                            .padding(16.dp)) {
                        Row {
                            NetImage(
                                url = it.envelopePic, modifier = Modifier
                                    .size(
                                        100.dp, 100.dp
                                    )
                                    .padding(end = 16.dp)
                            )
                            Column(Modifier.fillMaxWidth()) {
                                Text(text = it.title, style = MaterialTheme.typography.h6)
                                Text(text = it.desc)
                            }
                        }
                    }
                    Divider()
                }
            }
        }
    }
}

