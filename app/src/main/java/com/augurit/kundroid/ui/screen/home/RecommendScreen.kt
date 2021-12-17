package com.augurit.kundroid.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.augurit.kundroid.data.Resource
import com.augurit.kundroid.data.response.TabTitle
import com.augurit.kundroid.ui.commonCompose.LoadingDialog
import com.augurit.kundroid.ui.commonCompose.SwipeRefreshList


@Composable
fun RecommendScreen(homeViewModel: HomeViewModel= hiltViewModel()) {
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
            homeViewModel.getContentByTitle(it[selectedTabIndex].title).collectAsLazyPagingItems()

        SwipeRefreshList(collectAsLazyPagingItems) {
            items(collectAsLazyPagingItems) {
                it?.let {
                    Column {
                        Text(text = it.title, style = MaterialTheme.typography.h6)
                        Text(text = it.content)
                        Divider()
                    }
                }
            }
        }
    }
}

