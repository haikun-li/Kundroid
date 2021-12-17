package com.augurit.kundroid.ui.screen.login

import androidx.lifecycle.viewModelScope
import com.augurit.kundroid.data.Resource
import com.augurit.kundroid.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import androidx.navigation.NavHostController
import com.augurit.kundroid.ui.NavHostName
import kotlinx.coroutines.flow.MutableSharedFlow

import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 包名: com.augurit.kundroid.ui.screen.login
 * 文件描述: TODO
 * 创建人: haikun
 * 创建时间: 2021/12/16 10:16
 * 修改人: haikun
 * 修改时间: 2021/12/16 10:16
 * 修改备注: TODO
 */
@HiltViewModel
class LoginViewModel @Inject constructor(): BaseViewModel() {
    val login = MutableSharedFlow<Resource<String>>()


    fun login(
        account: String,
        password: String,
        rememberPassword: Boolean,
        navController: NavHostController
    ){
        viewModelScope.launch {
            login.emit(Resource.LoadingResource(null))
            delay(2000)
            login.emit(Resource.SuccessResource(null))
            navController.navigate(NavHostName.HOME_SCREEN)
        }
    }
}