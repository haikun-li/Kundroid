package com.haikun.kundroid.ui.screen.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.haikun.kundroid.data.response.ArticleListData
import com.haikun.kundroid.data.response.UserInfoBean
import com.haikun.kundroid.repository.ArticleRepository
import com.haikun.kundroid.repository.UserRepository
import com.haikun.kundroid.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {

    val userInfoStateFlow  = MutableStateFlow(UserInfoBean())

    init {
        initUserInfo()
    }

    private fun initUserInfo() {
        viewModelScope.launch {
            userInfoStateFlow.emit(userRepository.userInfoFromDb())
        }
    }


    fun getPagingDataFlow(): Flow<PagingData<ArticleListData>> {
        return  articleRepository.getArticleList().flow.cachedIn(viewModelScope)
    }



}