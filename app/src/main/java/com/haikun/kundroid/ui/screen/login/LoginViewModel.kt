package com.haikun.kundroid.ui.screen.login

import androidx.lifecycle.viewModelScope
import com.haikun.kundroid.data.Resource
import com.haikun.kundroid.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.navigation.NavHostController
import com.haikun.kundroid.data.UserConstant
import com.haikun.kundroid.data.response.LoginData
import com.haikun.kundroid.repository.UserRepository
import com.haikun.kundroid.ui.NavHostName
import com.haikun.kundroid.utils.LogUtil
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val mUserRepository: UserRepository) :
    BaseViewModel() {

    private var _login = MutableSharedFlow<Resource<out Any>>()
    val login: SharedFlow<Resource<out Any>> = _login


    fun login(
        username: String,
        password: String,
        rememberPassword: Boolean,
    ) {
        viewModelScope.launch {
            mUserRepository.login(username, password).collect {
                if (it is Resource.SuccessResource) {
                    it.data?.apply {
                        UserConstant.userId = id
                    }
                    mUserRepository.userInfo().collect { userInfo ->
                        _login.emit(userInfo)
                    }
                } else {
                    _login.emit(it)
                }
            }
        }
    }
}