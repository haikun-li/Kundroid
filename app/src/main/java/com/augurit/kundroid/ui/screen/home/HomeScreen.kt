package com.augurit.kundroid.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.augurit.kundroid.R
import com.augurit.kundroid.ui.NavHostName
import com.augurit.kundroid.ui.commonCompose.NetImage
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController,  userName: String?) {
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
                HomeContent(bottomState)
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
fun HomeContent(bottomState: MutableState<BottomState>) {
    when (bottomState.value) {
        BottomState.Recommend -> {
            RecommendScreen()
        }
        BottomState.Boutique -> {
            BoutiqueScreen()
        }
    }
}

@Composable
fun HomeDrawerContent(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val user by homeViewModel.user.collectAsState()
    Column(Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            user.apply {
                NetImage(
                    head,
                    modifier = Modifier
                        .size(
                            80.dp, 80.dp
                        ).background(color = MaterialTheme.colors.error)
                        .clip(CircleShape),
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(text = name, style = MaterialTheme.typography.h6)
                    Text(text = signature, Modifier.padding(vertical = 8.dp))

                }
            }
        }
        Text(text = "我的创作", modifier = Modifier
            .padding(top = 20.dp)
            .clickable { })
        Text(text = "浏览历史", modifier = Modifier
            .padding(top = 20.dp)
            .clickable { })
        Text(text = "设置中心", modifier = Modifier
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