package com.augurit.kundroid.ui.screen.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.augurit.kundroid.ui.commonCompose.CommonAppBar
import com.augurit.kundroid.ui.commonCompose.SubTitleTextButton
import com.augurit.kundroid.ui.theme.AppThemeState
import com.augurit.kundroid.ui.theme.ColorPallet


@Composable
fun SettingsScreen(navController: NavHostController, appThemeState: MutableState<AppThemeState>) {
    var isExpansion: Boolean by rememberSaveable {
        mutableStateOf(false)
    }
    val value = getScreenTitle(appThemeState.value.pallet)
    Scaffold(topBar = {
        CommonAppBar("设置中心", navController)
    }) {
        Column {
            SubTitleTextButton(onClick = {
                isExpansion = !isExpansion
            }, text = "皮肤设置", value = value)
            if (isExpansion) {
                Divider()
                Row(horizontalArrangement = Arrangement.SpaceAround,modifier = Modifier.fillMaxWidth()) {
                    ColorPallet.values().forEach {
                        Column(
                            horizontalAlignment = CenterHorizontally,
                            modifier = Modifier
                                .clickable {
                                    appThemeState.value = appThemeState.value.copy(pallet = it)
                                }
                                .padding(16.dp)
                        ) {
                            Text(
                                text = getScreenTitle(it),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                            RadioButton(selected = it == appThemeState.value.pallet, onClick = null)
                        }
                    }

                }
            }
            Divider()

        }
    }

}

private fun getScreenTitle(pallet: ColorPallet): String {
    return when (pallet) {
        ColorPallet.GREEN -> {
            "绿野仙踪"
        }
        ColorPallet.PURPLE -> {
            "基佬紫"
        }
        ColorPallet.ORANGE -> {
            "复仇焰魂"
        }
        ColorPallet.BLUE -> {
            "蓝色忧郁"
        }
    }
}