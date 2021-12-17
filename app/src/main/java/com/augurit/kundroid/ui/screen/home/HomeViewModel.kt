package com.augurit.kundroid.ui.screen.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.augurit.kundroid.data.Resource
import com.augurit.kundroid.data.response.Content
import com.augurit.kundroid.data.response.TabTitle
import com.augurit.kundroid.data.response.User
import com.augurit.kundroid.repository.HomeRepository
import com.augurit.kundroid.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : BaseViewModel() {

    val user = MutableStateFlow(User("","",""))

    val tabTitle = MutableStateFlow<Resource<List<TabTitle>>>(Resource.EmptyResource())


    init {
        initUser()
        initTabTitle()
    }

    private fun initUser() {
        user.value = User(
            "不会二连的嘉文",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fstatic.lolskin.cn%2Fdata%2Fskin%2FJarvanIV%2F0.jpg&refer=http%3A%2F%2Fstatic.lolskin.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1642322955&t=41dd3683230edd1c8535a8870455d609",
            "犯我德邦者，虽远必诛！"
        )
    }

    private fun initTabTitle() {
        viewModelScope.launch {
            tabTitle.emit(Resource.LoadingResource(null))
            delay(1000)
            tabTitle.emit(Resource.SuccessResource(mutableListOf(TabTitle("帖子"),TabTitle("官方"),TabTitle("赛事"),TabTitle("手游"),TabTitle("云顶"))))

        }
    }

    fun getContentByTitle(title: String): Flow<PagingData<Content>> {
        return homeRepository.getContentByTitle(title).flow.cachedIn(viewModelScope)
    }


}