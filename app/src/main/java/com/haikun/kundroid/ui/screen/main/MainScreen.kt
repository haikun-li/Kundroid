package com.haikun.kundroid.ui.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.haikun.kundroid.R
import com.haikun.kundroid.ui.AppNavHost
import com.haikun.kundroid.ui.MainSubScreen
import com.haikun.kundroid.ui.NavHostName
import com.haikun.kundroid.ui.commonCompose.CircleNetImage
import com.haikun.kundroid.ui.screen.home.HomeViewModel
import com.haikun.kundroid.ui.theme.AppThemeState
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavHostController, themeState: MutableState<AppThemeState>) {
    val scaffoldState = rememberScaffoldState()
    val bottomState by remember {
        mutableStateOf(mutableStateListOf(MainSubScreen.Home,MainSubScreen.Project))
    }
    val selectBottom by remember {
        mutableStateOf(0)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MainAppBar(scaffoldState) },
        drawerContent = {
            MainDrawerContent(navController)
        },
        drawerBackgroundColor = MaterialTheme.colors.primaryVariant,
        bottomBar = {
            MainBottomBar(bottomState,selectBottom)
        }
    ) {
        AppNavHost(navController, themeState)
    }

}


@Composable
fun MainAppBar(scaffoldState: ScaffoldState) {
    val coroutineScope = rememberCoroutineScope()
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name))
    }, navigationIcon = {
        IconButton(onClick = {
            coroutineScope.launch {
                scaffoldState.drawerState.open()
            }
        }) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
        }
    })
}


@Composable
fun MainDrawerContent(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val user by homeViewModel.userInfoStateFlow.collectAsState()
    Column(Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            user.apply {
                CircleNetImage(
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fstatic.lolskin.cn%2Fdata%2Fskin%2FJarvanIV%2F0.jpg&refer=http%3A%2F%2Fstatic.lolskin.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1642322955&t=41dd3683230edd1c8535a8870455d609",
                    modifier = Modifier
                        .size(
                            80.dp, 80.dp
                        )
                        .clip(CircleShape),
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Row(horizontalArrangement = SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text(text = userInfo.nickname, style = MaterialTheme.typography.h6)
                        Text(text = "${coinInfo.level}级", Modifier.padding(vertical = 8.dp))
                    }

                    Row(
                        horizontalArrangement = SpaceBetween,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "积分:${coinInfo.coinCount}")
                        Text(text = "排名:${coinInfo.rank}")
                    }

                }
            }
        }
        Text(text = "我的收藏", style = MaterialTheme.typography.h6, modifier = Modifier
            .padding(top = 20.dp)
            .clickable {
                navController.navigate(NavHostName.COLLECT_LIST_SCREEN)
            })
        Text(text = "浏览历史", style = MaterialTheme.typography.h6, modifier = Modifier
            .padding(top = 20.dp)
            .clickable { })
        Text(text = "设置中心", style = MaterialTheme.typography.h6, modifier = Modifier
            .padding(top = 20.dp)
            .clickable { navController.navigate(NavHostName.SETTINGS_SCREEN) })


    }
}

@Composable
fun MainBottomBar(bottomState: SnapshotStateList<MainSubScreen>,selectBottom:Int) {
    BottomNavigation {
        bottomState.forEachIndexed { index, mainSubScreen ->
            BottomNavigationItem(selected = selectBottom==index, icon = {

            }, onClick = {

            }, label = {
                Text(mainSubScreen.tabText)
            })
        }
    }
}