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
import com.haikun.kundroid.ui.screen.project.ProjectViewModel
import com.haikun.kundroid.utils.toJson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ProjectScreen(viewModel: ProjectViewModel = hiltViewModel(), navController: NavHostController) {
    val classifyList = viewModel.classify

    Column {
        if (classifyList.size > 0) {
            ScrollableTabRow(
                selectedTabIndex = viewModel.selectedTabIndex, edgePadding = 10.dp,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                classifyList.forEachIndexed { index, classify ->
                    Tab(
                        selected = index == viewModel.selectedTabIndex,
                        onClick = {
                            viewModel.selectedTabIndex = index
                        }) {
                        Text(classify.name, modifier = Modifier.padding(8.dp))
                    }
                }
            }

            ProjectContent(navController)
        }
    }
}

@Composable
fun ProjectContent(navController: NavHostController) {
    val viewModel: ProjectViewModel = hiltViewModel()

    val collectAsLazyPagingItems =
        viewModel.getPagingDataFlow().collectAsLazyPagingItems()

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
