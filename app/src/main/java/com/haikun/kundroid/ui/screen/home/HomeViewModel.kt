package com.haikun.kundroid.ui.screen.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.haikun.kundroid.data.Resource
import com.haikun.kundroid.data.response.ArticleListData
import com.haikun.kundroid.data.response.TabTitle
import com.haikun.kundroid.data.response.UserInfoBean
import com.haikun.kundroid.repository.HomeRepository
import com.haikun.kundroid.repository.UserRepository
import com.haikun.kundroid.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository,private val userRepository: UserRepository) : BaseViewModel() {

    val user = MutableStateFlow(UserInfoBean())

    val tabTitle = MutableStateFlow<Resource<List<TabTitle>>>(Resource.EmptyResource())


    init {
        initUser()
        initTabTitle()
    }

    private fun initUser() {
        viewModelScope.launch {
            user.emit(userRepository.userInfoFromDb())
        }
    }

    private fun initTabTitle() {
        viewModelScope.launch {
            tabTitle.emit(Resource.LoadingResource(null))
            delay(1000)
            tabTitle.emit(Resource.SuccessResource(mutableListOf(TabTitle("帖子"),TabTitle("官方"),TabTitle("赛事"),TabTitle("手游"),TabTitle("云顶"))))

        }
    }

    fun getArticleList(): Flow<PagingData<ArticleListData>> {
        return homeRepository.getArticleList().flow.cachedIn(viewModelScope)
    }


}