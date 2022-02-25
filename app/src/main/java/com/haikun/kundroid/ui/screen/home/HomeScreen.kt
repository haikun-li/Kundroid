package com.haikun.kundroid.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.haikun.kundroid.R
import com.haikun.kundroid.ui.NavHostName
import com.haikun.kundroid.ui.commonCompose.CircleNetImage
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, userName: String?) {
    val scaffoldState = rememberScaffoldState()
    val bottomState = rememberSaveable {
        mutableStateOf(BottomState.Recommend)
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { HomeAppBar(scaffoldState) },
        drawerContent = {
            HomeDrawerContent(navController)
        },
        drawerBackgroundColor = MaterialTheme.colors.primaryVariant,
    ) {
        Column {
            Box(modifier = Modifier.weight(1f)) {
                HomeContent(bottomState,navController)
            }
            HomeBottomBar(bottomState)
        }
    }

}


@Composable
fun HomeAppBar(scaffoldState: ScaffoldState) {
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
fun HomeContent(bottomState: MutableState<BottomState>, navController: NavHostController) {
    when (bottomState.value) {
        BottomState.Recommend -> {
            BoutiqueScreen()
        }
        BottomState.Boutique -> {
            RecommendScreen(navController = navController)

        }
    }
}

@Composable
fun HomeDrawerContent(
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
            .clickable { })
        Text(text = "浏览历史", style = MaterialTheme.typography.h6, modifier = Modifier
            .padding(top = 20.dp)
            .clickable { })
        Text(text = "设置中心", style = MaterialTheme.typography.h6, modifier = Modifier
            .padding(top = 20.dp)
            .clickable { navController.navigate(NavHostName.SETTINGS_SCREEN) })


    }
}

@Composable
fun HomeBottomBar(bottomState: MutableState<BottomState>) {
    BottomNavigation {
        BottomNavigationItem(selected = true, onClick = {
            bottomState.value = BottomState.Recommend
        }, label = {
            Text(text = "推荐")
        }, icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) })
        BottomNavigationItem(selected = true, onClick = {
            bottomState.value = BottomState.Boutique
        }, label = {
            Text(text = "精品")
        }, icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) })
    }
}