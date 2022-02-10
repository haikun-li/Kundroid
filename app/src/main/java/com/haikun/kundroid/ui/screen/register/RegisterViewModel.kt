package com.haikun.kundroid.ui.screen.register

import androidx.lifecycle.viewModelScope
import com.haikun.kundroid.data.Resource
import com.haikun.kundroid.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.navigation.NavHostController
import com.haikun.kundroid.repository.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(private val mUserRepository: UserRepository): BaseViewModel() {

    private var _register = MutableSharedFlow<Resource<Any>>()
    val register:SharedFlow<Resource<Any>> = _register


    fun register(
        username: String,
        password: String,
        rePassword: String,
        navController: NavHostController
    ){
        viewModelScope.launch {
            mUserRepository.register(username,password,rePassword).collect {
                _register.emit(it)

            }
        }
    }
}