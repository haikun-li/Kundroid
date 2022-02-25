package com.haikun.kundroid.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.haikun.kundroid.data.response.ArticleListData
import com.haikun.kundroid.data.response.TabTitle
import com.haikun.kundroid.data.response.UserInfoBean
import com.haikun.kundroid.repository.ArticleRepository
import com.haikun.kundroid.repository.UserRepository
import com.haikun.kundroid.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {

    val userInfoStateFlow  = MutableStateFlow(UserInfoBean())
    var selectedTabIndex by mutableStateOf(0)
    val tabTitle by mutableStateOf(
        mutableListOf(
            TabTitle("推荐"),
            TabTitle("项目"),
            TabTitle("问答"),
            TabTitle("体系"),
        )
    )

    private val flowMap = hashMapOf<Int, Flow<PagingData<ArticleListData>>>()



    init {
        initUserInfo()
    }

    private fun initUserInfo() {
        viewModelScope.launch {
            userInfoStateFlow.emit(userRepository.userInfoFromDb())
        }
    }


    fun getPagingDataFlow(): Flow<PagingData<ArticleListData>> {
        val flow = flowMap[selectedTabIndex]
        return if (flow != null) {
            flow
        } else {
            val pagingDataFlow = articleRepository.getArticleList().flow.cachedIn(viewModelScope)
            flowMap[selectedTabIndex] = pagingDataFlow
            pagingDataFlow
        }
    }



}