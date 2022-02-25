package com.haikun.kundroid.ui.screen.login

import androidx.lifecycle.viewModelScope
import com.haikun.kundroid.data.Resource
import com.haikun.kundroid.data.UserConstant
import com.haikun.kundroid.repository.UserRepository
import com.haikun.kundroid.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val mUserRepository: UserRepository) : BaseViewModel() {

    private var _login = MutableSharedFlow<Resource<out Any>>()
    val login: SharedFlow<Resource<out Any>> = _login.asSharedFlow()


    fun login(
        username: String,
        password: String,
        rememberPassword: Boolean,
    ) {
        viewModelScope.launch {
            mUserRepository.login(username, password).flatMapLatest {
                return@flatMapLatest if (it is Resource.SuccessResource) {
                    it.data?.apply {
                        UserConstant.userId = id
                    }
                    mUserRepository.userInfo()
                } else {
                    flow { emit(it) }
                }
            }.collect {
                _login.emit(it)
            }
        }
    }
}