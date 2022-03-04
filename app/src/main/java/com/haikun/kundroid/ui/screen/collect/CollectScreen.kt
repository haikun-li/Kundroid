package com.haikun.kundroid.ui.screen.collect

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.haikun.kundroid.R
import com.haikun.kundroid.data.response.ArticleListData
import com.haikun.kundroid.ui.NavHostName
import com.haikun.kundroid.ui.commonCompose.CommonAppBar
import com.haikun.kundroid.ui.commonCompose.NetImage
import com.haikun.kundroid.ui.commonCompose.SwipeRefreshList
import com.haikun.kundroid.utils.toJson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun CollectScreen(scaffoldState: ScaffoldState = rememberScaffoldState(),viewModel: CollectViewModel = hiltViewModel()) {

    val navUiState = viewModel.navUiState
    val collectAsLazyPagingItems = viewModel.flow().collectAsLazyPagingItems()
    Scaffold(scaffoldState = scaffoldState, topBar = {
        CommonAppBar(stringResource(R.string.collect_list), navUiState.navHostController)
    }) {
        ArticleList(collectAsLazyPagingItems){
            it.collect = true
            val encodedUrl = URLEncoder.encode(
                "${
                    toJson(it)
                }", StandardCharsets.UTF_8.toString()
            )
            navUiState.navHostController?.navigate(
                "${NavHostName.DETAIL_SCREEN}/${
                    encodedUrl
                }?ifFromCollect=true"
            )
        }
    }
}

@Composable
fun ArticleList(collectAsLazyPagingItems: LazyPagingItems<ArticleListData>,itemClick:(ArticleListData)->Unit){
    SwipeRefreshList(collectAsLazyPagingItems) {
        items(collectAsLazyPagingItems, key = {
            it.id
        }) {
            it?.let {
                Column(
                    Modifier
                        .clickable {
                            itemClick(it)
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
                            Text(
                                text = it.desc,
                                style = MaterialTheme.typography.body2,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
                Divider()
            }
        }
    }
}